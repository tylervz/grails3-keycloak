package helloworld

import grails.gorm.transactions.Transactional
import org.keycloak.adapters.springsecurity.account.KeycloakRole
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

@Transactional
class UserService {

    def getCurrentUser() {
        // springSecurityService is null when I try to inject it
//        return springSecurityService.currentUser
    }

    Boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().authentication
        Collection<KeycloakRole> authorities = auth.authorities as Collection<KeycloakRole>
        return authorities.find { it.authority.equals("OJT_ADMIN") } != null
    }

    Boolean isUser() {
        Authentication auth = SecurityContextHolder.getContext().authentication
        Collection<KeycloakRole> authorities = auth.authorities as Collection<KeycloakRole>
        return authorities.find { it.authority.equals("OJT_USER") } != null
    }
}
