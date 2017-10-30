package br.com.servico.repository.suporte;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entity.suporte.AcessoUsuario;

public interface AcessoUsuarioRepository extends JpaRepository<AcessoUsuario, Integer>{
	
	AcessoUsuario findByDeLogin(String login);
}
