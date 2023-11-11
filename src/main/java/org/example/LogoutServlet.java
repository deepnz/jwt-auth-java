package org.example;

public class LogoutServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }

        String returnUrl = String.format("%s://%s", request.getScheme(), request.getServerName());
        if ((request.getScheme().equals("http") && request.getServerPort() != 80) || (request.getScheme().equals("https") && request.getServerPort() != 443)) {
            returnUrl += ":" + request.getServerPort();
        }
        returnUrl += "/login";

        // Build logout URL like:
        // https://{YOUR-DOMAIN}/v2/logout?client_id={YOUR-CLIENT-ID}&returnTo=http://localhost:3000/login
        String logoutUrl = String.format(
                "https://%s/v2/logout?client_id=%s&returnTo=%s",
                domain,
                clientId,
                returnUrl
        );
        response.sendRedirect(logoutUrl);
    }
}
