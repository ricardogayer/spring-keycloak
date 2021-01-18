package com.mwave.SpringKeycloak;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProdutoController {

    ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Autowired
    ProdutoService produtoService;

    @RolesAllowed({"ADMIN"})
    @GetMapping("/produto1")
    public String getProduto1() {
        return "Hello Produto - ADMIN";
    }

    @RolesAllowed({"USER"})
    @GetMapping("/produto2")
    public String getProduto2(Authentication authentication) {
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getName());
        System.out.println(authentication.getDetails());
        SimpleKeycloakAccount account = (SimpleKeycloakAccount)authentication.getDetails();
        System.out.println(account.getPrincipal().getName());
        System.out.println(account.getKeycloakSecurityContext().getTokenString());

        KeycloakPrincipal principal = (KeycloakPrincipal) ((SimpleKeycloakAccount) authentication.getDetails()).getPrincipal();

        System.out.println(principal.getName());

        System.out.println(principal.getKeycloakSecurityContext().getToken().getPreferredUsername());

        AccessToken accessToken = ((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken();
        System.out.println(accessToken.getPreferredUsername());

        return "Hello Produto - USER";
    }

    @GetMapping("/produto3")
    public String getProduto3(Principal principal) {
        System.out.println("Authentication = " + principal.getName());
        return "Hello Produto - PUBLIC";
    }

    // @RolesAllowed({"USER"})
    @GetMapping("/produto4/{userId}")
    public String getProduto4(@PathVariable("userId") String userId) {
        System.out.println("@PreAuthorize");
        String resultado = produtoService.consultaProduto(userId);
        return resultado;
    }

    // @RolesAllowed("USER")
    @GetMapping("/produtos")
    public List<Produto> getProdutos() {
        List<Produto> produtos = produtoRepository.getProdutoByResponsavel();
        return produtos;
    }

    @PostAuthorize("returnObject.responsavel == authentication.name")
    @GetMapping("/produto/{id}")
    public Produto getProdutos(@PathVariable("id") Long id) {

        Optional<Produto> optProduto = produtoRepository.findById(id);

        if (optProduto.isPresent()) {
            Produto p = optProduto.get();
            return p;
        }

        return null;
    }

    @GetMapping("/public/produtos")
    public ResponseEntity<List<Produto>> getPublicProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return ResponseEntity.ok(produtos);
    }


}
