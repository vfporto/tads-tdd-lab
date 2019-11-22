package ifpr.models;

import java.util.Date;
import java.util.List;

public class Locacao {

    private Filme filme;
    private Usuario usuario;
    private Date dataLocacao;
    private Date dataDevolucao;
    private Double valor;

    public Locacao(){}

    public Locacao(Filme filme, Usuario usuario, Date dataLocacao, Date dataDevolucao, Double valor) {
        this.filme = filme;
        this.usuario = usuario;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Double getValor() {
        return filme.getPreco();
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
