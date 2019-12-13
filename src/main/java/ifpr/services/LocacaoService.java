package ifpr.services;

import ifpr.exceptions.NaoPodeTerEstoqueVazioException;
import ifpr.models.Filme;
import ifpr.models.Locacao;
import ifpr.models.Usuario;
import ifpr.utils.DataUtils;

import java.util.Date;
import java.util.List;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception {

        for(Filme filme : filmes){
            if(filme.getEstoque() == 0){
                throw new Exception("filme sem estoque");
            }
        }

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setFilmes(filmes);
        locacao.setDataLocacao(new Date());
        locacao.setDataDevolucao(DataUtils.adicionarDias(new Date(), 1));

        //TODO: preco ainda sem desconto, corrigir
        Double valor = 0.0;
        for(Filme filme: filmes){
            filme.setEstoque(filme.getEstoque()-1);
            valor += filme.getPreco();
        }
        locacao.setValor(valor);


        //persistir

        return locacao;
    }

}
