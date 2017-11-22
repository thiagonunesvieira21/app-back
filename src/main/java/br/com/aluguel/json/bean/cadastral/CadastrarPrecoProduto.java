package br.com.aluguel.json.bean.cadastral;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by thiago on 11/07/17.
 */
public class CadastrarPrecoProduto {

    @NotNull
    private Integer produtoId;

    @NotNull
    private Date dtInicio;

    @NotNull
    private Date dtFim;

    public CadastrarPrecoProduto() {
    }

    public CadastrarPrecoProduto(Integer produtoId, Date dtInicio, Date dtFim) {
        this.produtoId = produtoId;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }
}
