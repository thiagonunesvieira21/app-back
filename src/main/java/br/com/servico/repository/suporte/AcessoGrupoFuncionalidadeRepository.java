package br.com.servico.repository.suporte;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entity.suporte.AcessoGrupoFuncionalidade;
import br.com.entity.suporte.AcessoGrupoFuncionalidadeId;

public interface AcessoGrupoFuncionalidadeRepository extends JpaRepository<AcessoGrupoFuncionalidade, AcessoGrupoFuncionalidadeId> {
	
}
