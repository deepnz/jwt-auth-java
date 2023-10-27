package org.example.Main;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.io.FileOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        System.out.println("Pub and private key-generated --- " + privateKey + "\n\n\n " + publicKey);

        System.out.println("JWT \n\n"+createJWT((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey));

        try (FileOutputStream fos = new FileOutputStream("public.key")) {
            fos.write(publicKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String createJWT(RSAPublicKey rsaPublicKey, RSAPrivateKey rsaPrivateKey){
        String token = "";
        try {
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
             token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (
                JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }
}
