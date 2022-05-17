package com.blue.wallet.controller.uri;

public final class RecuperarSenhaURI {

    public static final String CONTROLLER = "/api/recuperar-conta";
    public static final String ENVIAR_EMAIL = "/enviar-email/{email}";
    public static final String VERIFICAR = "/verificar";

    public static final String ATUALIZAR_SENHA = "/atualizar-senha";

    private static final String URI_ENVIAR_EMAIL_CODIGO_RECUPERACAO = CONTROLLER + ENVIAR_EMAIL;
    private static final String URI_VERIFICAR_CODIGO = CONTROLLER + VERIFICAR;

    private static final String URI_ATUALIZAR_SENHA = CONTROLLER + ATUALIZAR_SENHA;

    public static final String[] PUBLIC_HTTP_POST_REQUESTS = {
            URI_ENVIAR_EMAIL_CODIGO_RECUPERACAO, URI_ATUALIZAR_SENHA
    };

    public static final String[] PUBLIC_HTTP_GET_REQUESTS = {
            URI_VERIFICAR_CODIGO
    };
}