package br.com.aluguel.servico.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Abreviador {
	
	private static final double METADE = 2.0;
	private static final int AVANCA = 1;
	private static final int RETROCEDE = -1;
	private static final int MAX_CARACTERES = 24;
	
	
	/**
	 * Abrevia um nome completo
	 * @param nome
	 * @return nome abreviado
	 */
	public static String abreviarNome(String nome) {
		
		nome = nome.toUpperCase();
		
		if(nome.length() > MAX_CARACTERES) {
			nome = retiraConjuncoes(nome);
			
			while(nome.length() > MAX_CARACTERES && podeAbreviarNomesMeio(nome)) {
				nome = abreviaNomeMeio(nome, AVANCA);
				nome = abreviaNomeMeio(nome, RETROCEDE);
			}
			
			if(nome.length() > MAX_CARACTERES) {
				
				nome = abreviaUltimoNome(nome);
				
				if(nome.length() > MAX_CARACTERES) {
					// Se após todo o procedimento ainda passar do limite, truncamos o nome
					nome = nome.substring(0, MAX_CARACTERES);
				}
			}
		}

		return nome.trim();
	}
	
	/**
	 * Retira as conjunções de um nome.
	 * Após a execução, "José da Silva" torna-se "José Silva"
	 * @param nome
	 * @return nome sem conjunções
	 */
	private static String retiraConjuncoes(String nome) {
		String[] nomeSplit = nome.split(" ");
		
	    final List<String> list =  new ArrayList<String>();
	    Collections.addAll(list, nomeSplit); 
		
		for(int i=0; i < list.size(); i++) {
			if(list.get(i).equalsIgnoreCase("do") || list.get(i).equalsIgnoreCase("da") || list.get(i).equalsIgnoreCase("de") ||
			   list.get(i).equalsIgnoreCase("dos") ||list.get(i).equalsIgnoreCase("das") || list.get(i).equalsIgnoreCase("e")) {
				list.remove(i);
			}
		}
		
		return reorganizarNome(list.toArray(new String[list.size()]));
	}
	
	/**
	 * Abrevia os nomes do meio
	 * @param nome, sinal indicando se a abreviacao avança ou retorcede a partir do meio
	 * @return nome com nomes do meio abreviados
	 */
	private static String abreviaNomeMeio(String nome, int sinal) {
		String[] nomeSplit = nome.split(" ");
		
		int meio = (int) Math.floor(nomeSplit.length/METADE);
		
		for(int i=meio;i < nomeSplit.length-1; i++){
			if(nomeSplit[meio].length() == 1) {
				meio+=sinal;
			}
		}
		
		if(meio > 0 && meio < nomeSplit.length-1){
			nomeSplit[meio] = abreviaNome(nomeSplit[meio]);
		}

		return reorganizarNome(nomeSplit);
		
	}
	
	/**
	 * Verifica a possibilidade de abreviar os nomes do meio
	 * @param nome
	 * @return true or false
	 */
	private static boolean podeAbreviarNomesMeio(String nome) {
		String[] nomeSplit = nome.split(" ");
		
		for(int i=1; i < nomeSplit.length-1; i++) {
			if(nomeSplit[i].length() != 1) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Abrevia o último nome
	 * @param nome
	 * @return nome abreviado
	 */
	private static String abreviaUltimoNome(String nome) {
		String[] nomeSplit = nome.split(" ");

		nomeSplit[nomeSplit.length-1] = abreviaNome(nomeSplit[nomeSplit.length-1]);
		return reorganizarNome(nomeSplit);
	}
	
	/**
	 * Reorganiza todos os nomes em uma única string
	 * @param nomes
	 * @return string única com todos os nomes
	 */
	private static String reorganizarNome(String[] nomes) {
		StringBuilder nomeTemp = new StringBuilder();
		
		for(int i=0; i < nomes.length; i++) {
			nomeTemp.append(nomes[i]+" ");
		}
		
		return nomeTemp.toString();
	}
	
	/**
	 * Abrevia um nome
	 * @param nome
	 * @return nome abreviado
	 */
	private static String abreviaNome(String nome) {
		nome = String.valueOf(nome.charAt(0));
		return nome;
	}

}
