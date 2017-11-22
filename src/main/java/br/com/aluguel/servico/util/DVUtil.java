package br.com.aluguel.servico.util;
/**
 * 
 * Classe utilitaria responsavel por calcular digito verificador
 * 
 */
public class DVUtil {
	
	public static Integer montarDVBase10(String numeroBase) {
		int soma = 0;
		int restoDaDivisao = 0;
		int digitoVerificador = 0;
	    String[] numeros = new String[numeroBase.length()+1];
	    int multiplicador = 2;
	    String auxiliar;
	    String auxiliar2;
	    String auxiliar3;
	    
	    for (int i = numeroBase.length(); i > 0; i--) {  	    	
	    	//Multiplica da direita pra esquerda, entre 2 e 1
	    	if(multiplicador%2 == 0){
		    	// pega cada numero isoladamente  
	    		numeros[i] = String.valueOf(Integer.valueOf(numeroBase.substring(i-1,i))*2);
	    		multiplicador = 1;
	    	}else{
	    		numeros[i] = String.valueOf(Integer.valueOf(numeroBase.substring(i-1,i))*1);
	    		multiplicador = 2;
	    	}
	    }  
	    // Realiza a soma dos digitos
	    for(int i = (numeros.length-1); i > 0; i--){
	    	auxiliar = String.valueOf(Integer.valueOf(numeros[i]));
	    	if(auxiliar.length()>1){
	    		auxiliar2 = auxiliar.substring(0,auxiliar.length()-1);	    		
	    		auxiliar3 = auxiliar.substring(auxiliar.length()-1,auxiliar.length());
	    		numeros[i] = String.valueOf(Integer.valueOf(auxiliar2) + Integer.valueOf(auxiliar3));	    		
	    	}
	    	else{
	    		numeros[i] = auxiliar;    		
	    	}
	    }
	    //Realiza a soma de todos os digitos do array e calcula o digito verificador
	    //na base modulo de 10	    
	    for(int i = numeros.length; i > 0 ; i--){
	    	if(numeros[i-1] != null){
	    		soma += Integer.valueOf(numeros[i-1]);
	    	}
	    }
	    restoDaDivisao = soma%10;
	   
	    if(restoDaDivisao!=0){
	    	digitoVerificador = 10 - restoDaDivisao;
	    }else{
	    	digitoVerificador = 0;
	    }
	    if(digitoVerificador>10){
	    	//deve haver algo errado se o digitoVerificador for maior que 10 entao devolva so o primeiro numero
	    	char i = new Integer(digitoVerificador).toString().toCharArray()[0];
	    	digitoVerificador = new Integer(i);
	    }
    	//retorna o DV
    	
	    return digitoVerificador;
	}
	

}
