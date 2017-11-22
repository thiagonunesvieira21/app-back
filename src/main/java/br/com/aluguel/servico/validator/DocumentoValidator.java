package br.com.aluguel.servico.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import br.com.aluguel.servico.annotation.Documento;
/**
 * 
 * classe responsavel por validar documentos, sendo eles cpf ou cnpj
 * @author dev
 *
 */
public class DocumentoValidator implements ConstraintValidator<Documento, String> {

	private static final int TAMANHO_CNPJ = 14;
	private static final int TAMANHO_CPF = 11;
	
	private static final Pattern padraoCNPJ = Pattern.compile("([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})");
	private static final List<Pattern> padroesCpf = new ArrayList<Pattern>();
	
	static{
		padroesCpf.add(Pattern.compile("([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})"));
		padroesCpf.add(Pattern.compile("^(?:(?!000\\.?000\\.?000-?00).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!111\\.?111\\.?111-?11).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!222\\.?222\\.?222-?22).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!333\\.?333\\.?333-?33).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!444\\.?444\\.?444-?44).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!555\\.?555\\.?555-?55).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!666\\.?666\\.?666-?66).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!777\\.?777\\.?777-?77).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!888\\.?888\\.?888-?88).)*$"));
		padroesCpf.add(Pattern.compile("^(?:(?!999\\.?999\\.?999-?99).)*$"));
		
	}
	

	@Override
	public void initialize(Documento arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String documento, ConstraintValidatorContext ctx) {
		
		if(documento!=null && !documento.isEmpty()){
			if(documento.length()==TAMANHO_CPF){
				return isCPFValid(documento,ctx);
			}else if(documento.length()==TAMANHO_CNPJ){
				return validarCnpj(documento,ctx);
			}
		}
		
		return false;
	}

	private boolean validarCnpj(String documento, ConstraintValidatorContext ctx) {
			
		if(!padraoCNPJ.matcher(documento).matches()){
			return false;
		}
		
		CNPJValidator cv = new CNPJValidator();
		cv.initialize(null);
		return cv.isValid(documento, ctx);
	}

	private boolean isCPFValid(String documento, ConstraintValidatorContext ctx) {
		
		for (Pattern pattern : padroesCpf) {
			
			if(!pattern.matcher(documento).matches()){
				return false;
			}
		}
		CPFValidator cv = new CPFValidator();
		cv.initialize(null);
		return cv.isValid(documento, ctx);
		
	}

}
