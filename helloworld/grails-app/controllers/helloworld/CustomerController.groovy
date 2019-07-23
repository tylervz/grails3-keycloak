package helloworld

import grails.converters.JSON
import org.keycloak.adapters.springsecurity.account.KeycloakRole
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class CustomerController {

    UserService userService

    def index() {
        checkDetails()
    }

    def checkDetails() {
        // TODO: research more about client scopes so that we can potentially access the user's name and email from here
        Authentication auth = SecurityContextHolder.getContext().authentication
        println auth
        Collection<KeycloakRole> authorities = auth.authorities as Collection<KeycloakRole>
        Boolean ojtUser = authorities.find { it.authority.equals("OJT_USER") } != null
        Boolean ojtAdmin = authorities.find { it.authority.equals("OJT_ADMIN") } != null
        Boolean normalAdmin = authorities.find { it.authority.equals("ROLE_ADMIN") } != null
        // Another way to access the roles the current user has
        println "Roles: ${auth?.details?.roles}"

        render([message: "able to access customers", isOjtUser: ojtUser,
                isOjtAdmin: ojtAdmin, authorities: authorities] as JSON)
    }

    def testUserService() {
        Boolean ojtAdmin = userService.isAdmin()
        render([message: "userService is working!", isOjtAdmin: ojtAdmin] as JSON)
    }
}
