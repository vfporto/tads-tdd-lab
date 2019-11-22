package ifpr.services;

import ifpr.models.Filme;
import ifpr.models.Locacao;
import ifpr.models.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Date;

import static ifpr.utils.DataUtils.adicionarDias;
import static ifpr.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class LocacaoServiceTest {
    Usuario usuario;
    LocacaoService service;

    @Before
    public void setup(){
        usuario = new Usuario("Usuario1");
        service = new LocacaoService();
        System.out.println("before");
    }

    @Test
    public void testeLocacao(){
        //cenario
        Filme filme = new Filme("Filme 1", 5.0, 2);

        //acao
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificação
        Assert.assertThat(locacao.getValor(), is(5.0));
        Assert.assertThat(locacao.getValor(), is(not(99.0)));
        Assert.assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        Assert.assertThat(isMesmaData(locacao.getDataDevolucao(), adicionarDias(new Date(), 1)), is(true));
    }

}
