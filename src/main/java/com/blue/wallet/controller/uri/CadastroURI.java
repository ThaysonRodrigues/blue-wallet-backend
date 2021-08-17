package com.blue.wallet.controller.uri;

public final class CadastroURI {

    public static final String CONTROLLER = "/api/conta";
    public static final String CADASTAR = "/cadastrar";

    private static final String URI_CADASTRAR_USUARIO = CONTROLLER + CADASTAR;

    public static final String[] PUBLIC_HTTP_PSOT_REQUESTS = {
        URI_CADASTRAR_USUARIO
    };
}
