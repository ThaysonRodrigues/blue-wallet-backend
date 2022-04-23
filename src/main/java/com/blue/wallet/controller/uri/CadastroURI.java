package com.blue.wallet.controller.uri;

public final class CadastroURI {

    public static final String CONTROLLER = "/api/conta";
    public static final String CADASTAR = "/cadastrar";
    public static final String VERIFICAR = "/verificar";
    public static final String RECUPERAR_SENHA = "/recuperar-senha/{email}";
    public static final String DADOS_CADASTRAIS = "/dados-cadastrais";
    public static final String ATUALIZAR_DADOS_CADASTRAIS = "/atualizar-dados-cadastrais";

    private static final String URI_CADASTRAR_USUARIO = CONTROLLER + CADASTAR;
    private static final String URI_VERIFICAR_USUARIO = CONTROLLER + VERIFICAR;
    private static final String URI_RECUPERAR_SENHA = CONTROLLER + RECUPERAR_SENHA;

    public static final String[] PUBLIC_HTTP_POST_REQUESTS = {
        URI_CADASTRAR_USUARIO, URI_VERIFICAR_USUARIO, URI_RECUPERAR_SENHA
    };
}
