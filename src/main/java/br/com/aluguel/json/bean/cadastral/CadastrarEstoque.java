package br.com.aluguel.json.bean.cadastral;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by thiago on 01/07/17.
 */
@ApiModel(value = "estoque")
public class CadastrarEstoque {

    @NotNull
    private BigDecimal quantidade;

    @NotNull
    private Integer produtoId;

    @NotNull
    private Integer depositoId;

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 20)
    private String coLote;

    @NotNull
    private Date dtVencimento;

    public CadastrarEstoque(String coLote) {
        this.coLote = coLote;
    }

    public CadastrarEstoque(BigDecimal quantidade, Integer produtoId, Integer depositoId, String coLote, Date dtVencimento) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.depositoId = depositoId;
        this.coLote = coLote;
        this.dtVencimento = dtVencimento;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Integer depositoId) {
        this.depositoId = depositoId;
    }

    public String getCoLote() {
        return coLote;
    }

    public void setCoLote(String coLote) {
        this.coLote = coLote;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }
}
