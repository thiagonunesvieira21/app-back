package br.com.servico.util;

/**
 * Created by thiago on 30/06/17.
 */
public class CodigoProduto {

    private Long numero;

    public CodigoProduto(Long n){
        numero = n;
    }
    public Long getNumeroAleatorio(){
        long i = (long)(1 + Math.random() * numero);
        return i;
    }

    public static String getCodigo(){
        CodigoProduto cp = new CodigoProduto(System.currentTimeMillis());
        return Integer.toHexString(cp.getNumeroAleatorio().intValue()).toString().toUpperCase();
    }

}
