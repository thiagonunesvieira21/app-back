package br.com.servico.util;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.bancos.Bancos;

public class BancosBoletoFactory {
	
	
	private BancosBoletoFactory(){
		
	}
	public static Banco getBancoByCodigo(String codigo){
		return Bancos.getPorNumero(codigo);
	}
	public static Bancos[] getBancos(){
		return Bancos.values();
	}
	
	

}