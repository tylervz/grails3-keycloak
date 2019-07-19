package helloworld

import grails.web.mapping.LinkGenerator

class LogoutController {

    LinkGenerator grailsLinkGenerator

    def index() {
        signout()
    }

    // This is not working as expected. Maybe it's because the redirect and /logout are both unsecured pages.
    def logout() {
        // redirect the browser to the following URL to sign the user out of Keycloak
        String keycloakServer = "http://localhost:8080"
        String realm = "hclabs-dev"
        String redirectUri = grailsLinkGenerator.serverBaseURL
        println "redirectUri: $redirectUri"
        String encodedRedirectUri = java.net.URLEncoder.encode(redirectUri, "UTF-8")
        println "encodedRedirectUri: $encodedRedirectUri"
//        String url = "${keycloakServer}/auth/realms/${realm}/protocol/openid-connect/logout"
        String url = "${keycloakServer}/auth/realms/${realm}/protocol/openid-connect/logout?redirect_uri=${encodedRedirectUri}"
        redirect(url: url)
    }

    // Another way of signing out
    def signout() {
        println "about to logout"
        request.logout()
        println "DONE!"
        // Redirect back to the home page
        String redirectUri = grailsLinkGenerator.serverBaseURL
        redirect(url: redirectUri)
    }
}
