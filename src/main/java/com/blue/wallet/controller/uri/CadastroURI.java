package com.blue.wallet.controller.uri;

public final class CadastroURI {

    public static final String CONTROLLER = "/api/conta";
    public static final String CADASTAR = "/cadastrar";
    public static final String VERIFICAR = "/verificar";
    public static final String USER_NAME = "/user-name";

    private static final String URI_CADASTRAR_USUARIO = CONTROLLER + CADASTAR;
    private static final String URI_VERIFICAR_USUARIO = CONTROLLER + VERIFICAR;

    public static final String[] PUBLIC_HTTP_POST_REQUESTS = {
        URI_CADASTRAR_USUARIO, URI_VERIFICAR_USUARIO
    };
}
