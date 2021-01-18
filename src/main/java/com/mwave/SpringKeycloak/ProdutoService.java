package com.mwave.SpringKeycloak;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    String usuario;

    @PreAuthorize("#id==authentication.name")
    public String consultaProduto(String id) {
        usuario = id;
        System.out.println("Produto ID = " + id);
        return usuario;
    }

}
