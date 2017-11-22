package br.com.aluguel.servico.repository.suporte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.suporte.AcessoGrupo;

public interface AcessoGrupoRepository extends JpaRepository<AcessoGrupo, Integer>{
	
	public List<AcessoGrupo> findByNuGrupoPai(Integer id);
	
}
