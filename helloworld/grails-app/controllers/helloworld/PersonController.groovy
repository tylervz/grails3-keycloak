package helloworld

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class PersonController {

    def index() {
        testUser()
    }

    def open() {
        render([message: "everyone should be able to access this method"] as JSON)
    }

    @Secured(['OJT_USER'])
    def testUser() {
        render([message: "able to access user secured method"] as JSON)
    }

    @Secured(['OJT_ADMIN'])
    def testAdmin() {
        render([message: "able to access admin secured method"] as JSON)
    }

    @Secured(['OJT_ADMIN', 'OJT_USER'])
    def one() {
        render([message: "hey hey"] as JSON)
    }
}
