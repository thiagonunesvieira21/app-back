package br.com.aluguel.servico.repository.suporte;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.suporte.AcessoGrupoFuncionalidade;
import br.com.aluguel.entity.suporte.AcessoGrupoFuncionalidadeId;

public interface AcessoGrupoFuncionalidadeRepository extends JpaRepository<AcessoGrupoFuncionalidade, AcessoGrupoFuncionalidadeId> {
	
}
