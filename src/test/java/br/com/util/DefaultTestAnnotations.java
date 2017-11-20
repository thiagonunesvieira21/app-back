package br.com.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.transaction.Transactional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.servico.IssuerApplication;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest(classes = IssuerApplication.class)
@ActiveProfiles(inheritProfiles=false, profiles="test")
@Transactional
public @interface DefaultTestAnnotations { }