This is a Grails 3.3.10 application that uses the Keycloak Spring Security adapter.

It assumes that you have a keycloak server running at localhost:8080

Once the keycloak server is running, ensure that
http://localhost:8082/sso/login
is a valid redirect URI of the `ojt` client.
