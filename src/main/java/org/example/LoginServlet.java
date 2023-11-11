package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        String redirectUri = req.getScheme() + "://" + req.getServerName();
        if ((req.getScheme().equals("http") && req.getServerPort() != 80) || (req.getScheme().equals("https") && req.getServerPort() != 443)) {
            redirectUri += ":" + req.getServerPort();
        }
        redirectUri += "/callback";

        String authorizeUrl = authenticationController.buildAuthorizeUrl(req, res, redirectUri)
                .build();
        res.sendRedirect(authorizeUrl);
    }
}
