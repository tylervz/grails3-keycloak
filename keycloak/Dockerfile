FROM centos:7
MAINTAINER Roy Willemse <roy.willemse@zimzam.nl>

RUN yum -y install java-1.8.0-openjdk.x86_64 && \
    curl -L -s -S https://github.com/stedolan/jq/releases/download/jq-1.5/jq-linux64 -o /usr/local/sbin/jq && \
    chmod +x /usr/local/sbin/jq && \
    groupadd -r keycloak -g 1000 && \
    useradd -u 1000 -r -g keycloak -m -d /opt/keycloak -s /sbin/nologin -c "Keycloak user" keycloak && \
    chmod 755 /opt/keycloak
    
USER keycloak

ENV KEYCLOAK_VERSION 6.0.1
ENV KEYCLOAK_HOME /opt/keycloak/keycloak-$KEYCLOAK_VERSION

RUN curl https://downloads.jboss.org/keycloak/$KEYCLOAK_VERSION/keycloak-$KEYCLOAK_VERSION.tar.gz | tar -C $HOME -zx 
RUN $KEYCLOAK_HOME/bin/add-user-keycloak.sh -r master -u admin -p admin

ADD . /opt/keycloak

RUN /opt/keycloak/setup.sh

CMD ["/opt/keycloak/startup.sh"]
