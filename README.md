This is a Grails 3.3.10 application named HelloWorld that uses the Keycloak Spring Security adapter.

# Getting Started

_This assumes you have Docker installed and the Docker host IP is 127.0.0.1._

Run `./keycloak-setup.sh` to run and provision a Keycloak server using Docker. Then, start the Grails application in its own terminal. (Or you can run Grails in IntelliJ).

```bash
# Terminal 1: Run Keycloak on http://localhost:8080/auth
./keycloak-setup.sh

# Terminal 2: Run Grails 3 based client on http://localhost:8082
cd helloworld
./gradlew bootRun

```

## Keycloak

To manage Keycloak via the Administration Console at http://localhost:8080/auth/admin, login using `admin`/`admin`.

The other users that are created automatically are:
`user`/`user`
`noroles`/`user`
`adminuser`/`user`

## Grails 3

Once the keycloak server is running, ensure that
http://localhost:8082/sso/login
is a valid redirect URI of the `ojt` client in the `hclabs-dev` realm.

Run the Grails application and open your browser to <http://localhost:8082>.

As is defined in [SecurityConfig.groovy](/helloworld/grails-app/init/helloworld/SecurityConfig.groovy),
all of the `CustomerController` methods are secured with `ROLE_OJT_USER` and all of the `AdminController` methods are secured with `ROLE_OJT_ADMIN`.
When you try to access one while not signed in to Keycloak, you will be redirected to sign in.
