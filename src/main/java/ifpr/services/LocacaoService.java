package ifpr.services;

import ifpr.exceptions.NaoPodeTerEstoqueVazioException;
import ifpr.models.Filme;
import ifpr.models.Locacao;
import ifpr.models.Usuario;
import ifpr.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception {

        for(Filme filme : filmes){
            if(filme.getEstoque() == 0){
                throw new Exception("filme sem estoque");
            }
        }

        Double[] descontos = {0.0, 0.0, 0.25, 0.50, 0.75, 1.0}; //Equivalente a 0%, 0%, 25%, 50%, 75% e 100%

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setFilmes(filmes);
        locacao.setDataLocacao(new Date());

        Date dataDevolucao = DataUtils.adicionarDias(new Date(), 1);
        if (DataUtils.verificarDiaSemana(dataDevolucao, Calendar.SUNDAY)) //verifica se a data de devolucao é Domingo
            dataDevolucao = DataUtils.adicionarDias(dataDevolucao, 1); //Caso positivo adiciona 1 dia de prazo.

        locacao.setDataDevolucao(dataDevolucao);

        //Retira cada filme do estoque
        for(Filme filme: filmes){
            filme.setEstoque(filme.getEstoque()-1);
        }

        //Calcula valor da locacao
        Double valor = 0.0;
        for (int i = 0; i < filmes.size(); i++) {
            Double precoFilme = filmes.get(i).getPreco();
            if (i < descontos.length){ //aplica desconto de acordo com a tabela
                precoFilme *= (1-descontos[i]);
            }
            valor += precoFilme;
        }

        locacao.setValor(valor);


        //persistir

        return locacao;
    }

}
