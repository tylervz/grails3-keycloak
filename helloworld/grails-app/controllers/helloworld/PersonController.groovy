package helloworld

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured

// Changing this to permitAll doesn't make the testAdmin method secured either
@Secured(['OJT_ADMIN', 'OJT_USER'])
class PersonController {

    def index() {
        testUser()
    }

    def open() {
        render([message: "everyone should be able to access this method"] as JSON)
    }

    // This is allowing users who don't have OJT_USER to access it
    @Secured(['OJT_USER'])
    def testUser() {
        render([message: "able to access user secured method"] as JSON)
    }

    // This is allowing users who don't have OJT_ADMIN to access it
    @Secured(['OJT_ADMIN'])
    def testAdmin() {
        render([message: "able to access admin secured method"] as JSON)
    }

    @Secured(['OJT_ADMIN', 'OJT_USER'])
    def one() {
        render([message: "hey hey"] as JSON)
    }
}
