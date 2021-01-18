package com.mwave.SpringKeycloak;

public class KeycloakUtils {
/*
    public static UsuarioKey getUserKeysFromJWT (Authentication authentication) {

        UsuarioKey key = new UsuarioKey();

        SimpleKeycloakAccount account = (SimpleKeycloakAccount)authentication.getDetails();

        key.setIdUsuario(UUID.fromString(account.getPrincipal().getName()));

        AccessToken accessToken = account.getKeycloakSecurityContext().getToken();

        Map<String,Object> map = accessToken.getOtherClaims();

        key.setIdGrupoUsuario(UUID.fromString(map.get("user-group-id").toString()));

        return key;
    }
    */

}