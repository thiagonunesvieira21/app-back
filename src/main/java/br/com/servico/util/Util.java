package br.com.servico.util;

import java.util.Collection;

public class Util {
	
	public static boolean isEmpty(Object objeto){
		if(objeto instanceof String){
			if(objeto.equals(""))
				return true;
		}else if(objeto instanceof Collection){
			Collection vr = (Collection)objeto;
			if(vr.isEmpty())
				return true;
		}else if(objeto == null){
			return true;
		}
		return false;
	}
	
	public static boolean isNumberNullOrZero(Object objeto){
		if(objeto instanceof Number){
			if(((Number)objeto).intValue()==0){
				return true;
			}
		}else if(objeto == null){
			return true;
		}
		return false;
	}
}
