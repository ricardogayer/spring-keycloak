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

### @RestController
Proteção dos endpoints via roles:

    @RolesAllowed({"ADMIN"})

    and

    @PostAuthorize("returnObject.responsavel == authentication.name")
    @GetMapping("/produto/{id}")

### @Service
Proteção baseado no usuário logado

    @PreAuthorize("#id==authentication.name")
    public String consultaProduto(String id) {

### @Repository

Proteção do acesso ao banco de dados:
    
    @Query("SELECT p FROM Produto p WHERE p.responsavel = ?#{principal.name}")
    List<Produto> getProdutoByResponsavel();


### application.properties

    keycloak.realm = realm
    keycloak.auth-server-url = http://127.0.0.1:8080/auth
    keycloak.ssl-required = external
    keycloak.resource = client
    keycloak.credentials.secret = 6f7c35d0-51ca-488c-ac34-30a8ffea4d95
    keycloak.use-resource-role-mappings = true
    keycloak.bearer-only = true
    keycloak.principal-attribute=preferred_username
