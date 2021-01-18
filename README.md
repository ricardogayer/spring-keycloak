# spring-keycloak

## Docker Keycloak

---

       version: "3.7"
          services:
             keycloak:
                container_name: keycloak 
                image: jboss/keycloak:12.0.1
             environment:
                KEYCLOAK_USER: admin
                KEYCLOAK_PASSWORD: admin
             ports:
                - "8080:8080"
--- 



