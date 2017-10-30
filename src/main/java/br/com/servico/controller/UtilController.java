package br.com.servico.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import br.com.servico.security.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.util.DigestUtils;

public class UtilController {
	
	private static final int SHA_256 = 256;
	public static final String AUTH_HEADER_NAME = "Authorization";

	public SecurityUser getAthenticatedUser(HttpSession session) {
		Object obj = session.getAttribute("sec-user");
		SecurityUser user = null;
		if (obj instanceof SecurityUser) {
			user = (SecurityUser) obj;
		}
		return user;
	}

	public static void copyNonNullProperties(Object src, Object target) {
	    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
	
	public static String hashFile(String path){
		FileInputStream fis;
		String md5 = null;
		
		try {
		
			fis = new FileInputStream(new File(path));
			md5 = DigestUtils.md5DigestAsHex(fis);
			
		} catch (FileNotFoundException e) {
			NoResultException noResultException = new NoResultException("Arquivo não encontrado");
			noResultException.initCause(e);
			throw noResultException;
		} catch (IOException e) {
			NoResultException noResultException = new NoResultException("Arquivo não pode ser encontrado");
			noResultException.initCause(e);
			throw noResultException;
		}
		
		return md5;
	}
	public String encodeSenhaSHA256(String senha){
		ShaPasswordEncoder shaPasswordEncoder= new ShaPasswordEncoder(SHA_256);
		return shaPasswordEncoder.encodePassword(senha, null);
	}
	
}
