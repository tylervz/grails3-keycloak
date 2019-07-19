package helloworld

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.context.annotation.ComponentScan

// Include the helloworld package in scanning for Spring components such as @Configuration classes
// so that SecurityConfig is used
@ComponentScan(basePackages = "helloworld")
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}
