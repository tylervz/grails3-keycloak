package helloworld

import grails.converters.JSON

class AdminController {

    def index() {
        render([message: "success"] as JSON)
    }
}
