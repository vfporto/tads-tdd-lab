package ifpr.services;

import ifpr.models.Filme;
import ifpr.models.Locacao;
import ifpr.models.Usuario;
import ifpr.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static ifpr.utils.DataUtils.adicionarDias;
import static ifpr.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class LocacaoServiceTest {
    Usuario usuario;
    LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expected;

    @Before
    public void setup(){
        /*é executado antes de cada método de teste*/
        System.out.println("before");
        usuario = new Usuario("Usuario1");
        service = new LocacaoService();
    }

    @After
    public void tearDown(){
        /*é executado após de cada método de teste*/
        System.out.println("after");
    }

    @Test
    public void testeLocacao(){
        //cenario
        ArrayList<Filme> filmes = new ArrayList<>();

        filmes.add(new Filme("Filme 1", 5.0, 2));
        filmes.add(new Filme("Filme 2", 5.0, 1));


        //acao
        Locacao locacao = null;
        try {
            locacao = service.alugarFilme(usuario, filmes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //verificação
        error.checkThat(locacao.getValor(), is(10.0));
        error.checkThat(locacao.getValor(), is(not(99.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataDevolucao(), adicionarDias(new Date(), 1)), is(true));
    }

    @Test
    public void naoPodeAlugarFilmeSemEstoque(){

        //cenario
        Usuario usuario = new Usuario("usuario 1");
        ArrayList<Filme> filmes = new ArrayList<>();

        filmes.add(new Filme("Filme 1", 5.0, 2));
        filmes.add(new Filme("Filme 2", 5.0, 0));

        //acao
        try {
            service.alugarFilme(usuario, filmes);
            fail("deveria ter lançado uma excecao");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("filme sem estoque"));
        }
    }

    @Test(expected = Exception.class)
    public void naoPodeAlugarFilmeSemEstoque2() throws Exception {

        //cenario
        Usuario usuario = new Usuario("usuario 1");
        ArrayList<Filme> filmes = new ArrayList<>();

        filmes.add(new Filme("Filme 1", 5.0, 2));
        filmes.add(new Filme("Filme 2", 5.0, 0));

        //acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void testeDescontoLocacao() throws Exception {
        //cenario
        ArrayList<Filme> filmes = new ArrayList<>();

        filmes.add(new Filme("Filme 1", 10.0, 2));
        filmes.add(new Filme("Filme 2", 10.0, 1));
        filmes.add(new Filme("Filme 3", 10.0, 1));
        filmes.add(new Filme("Filme 4", 10.0, 1));
        filmes.add(new Filme("Filme 5", 10.0, 1));
        filmes.add(new Filme("Filme 6", 10.0, 1));


        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);


        //verificação
        //Teste de mesa(10 + 10 + 7.5 + 5 + 2.5 + 0 = 35)
        error.checkThat(locacao.getValor(), is(35.0));
    }

    @Test
    public void naoPodeDevolverLocacaoNoDomingo() throws Exception {
        //cenario
        ArrayList<Filme> filmes = new ArrayList<>();

        filmes.add(new Filme("Filme 1", 10.0, 2));
        filmes.add(new Filme("Filme 2", 10.0, 1));


        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);


        //verificação
        assertFalse(DataUtils.verificarDiaSemana(locacao.getDataDevolucao(), Calendar.SUNDAY));
    }




//    @Test
//    public void naoPodeAlugarFilmeSemEstoque3() throws Exception {
//
//        //cenario
//        Usuario usuario = new Usuario("usuario 1");
//        Filme filme = new Filme("Filme 1", 5.0, 0);
//
//        //No meu computador esse teste sempre dá NullPointerException, porque expected sempre é Null...
//        System.out.println(expected);
//        expected.expect(Exception.class);
//        expected.expectMessage("filme sem estoque");
//
//        //acao
//        service.alugarFilme(usuario, filme);
//    }
}
