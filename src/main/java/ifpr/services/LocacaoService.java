package ifpr.services;

import ifpr.exceptions.NaoPodeTerEstoqueVazioException;
import ifpr.models.Filme;
import ifpr.models.Locacao;
import ifpr.models.Usuario;
import ifpr.utils.DataUtils;

import java.util.Date;
import java.util.List;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, Filme filme) throws Exception {

        if(filme.getEstoque() == 0){
            throw new Exception("filme sem estoque");
        }

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setFilme(filme);
        locacao.setDataLocacao(new Date());
        locacao.setDataDevolucao(DataUtils.adicionarDias(new Date(), 1));

        //persistir

        return locacao;
    }

}
