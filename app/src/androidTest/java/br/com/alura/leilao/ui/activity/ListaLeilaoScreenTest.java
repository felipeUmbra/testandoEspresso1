package br.com.alura.leilao.ui.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static br.com.alura.leilao.matchers.ViewMatcher.displaysLeilaoOnPosition;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import net.datafaker.Faker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Locale;

import br.com.alura.leilao.R;
import br.com.alura.leilao.api.idlingresource.AppIdlingResource;
import br.com.alura.leilao.api.retrofit.client.TesteWebClient;
import br.com.alura.leilao.model.Leilao;

@RunWith(AndroidJUnit4.class)
public class ListaLeilaoScreenTest {

    private ActivityScenario<ListaLeilaoActivity> scenario;

    final TesteWebClient webClient = new TesteWebClient();
    final Faker faker = new Faker(new Locale("pt-BR"));

    private void cleanDB() throws IOException {
        boolean cdb = webClient.cleanDB();
        if (!cdb) {
            Assert.fail("Fail to cliean DB");
        }
    }

    @Before
    public void setup() throws IOException {
        IdlingRegistry.getInstance().register(AppIdlingResource.getIdlingResource());
        cleanDB();
    }

    @Test
    public void displayLeilao_afterLoadAPI() throws IOException {

        String produto1 = faker.commerce().productName();
        setupDataAndLaunch(
                new Leilao(produto1)
        );

        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(displaysLeilaoOnPosition(0, produto1, "R$ 0,00")));
    }

    @Test
    public void displayTwoLeiloes_afterLoadAP() throws IOException {

        String produto1 = faker.commerce().productName();
        String produto2 = faker.commerce().productName();

        setupDataAndLaunch(
                new Leilao(produto1),
                new Leilao(produto2)
        );

//        onView(withText(produto1))
//                .check(matches(isDisplayed()));
//        onView(withText(produto2))
//                .check(matches(isDisplayed()));
//        onView(allOf(withText(produto2),
//                withId(R.id.item_leilao_descricao)))
//                .check(matches(isDisplayed()));
        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(displaysLeilaoOnPosition(0, produto1, "R$ 0,00")));
        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(displaysLeilaoOnPosition(1, produto2, "R$ 0,00")));
    }

    @Test
    public void verificaMaiorlanceComoZero() throws IOException {
        String produto1 = faker.commerce().productName();

        setupDataAndLaunch(
                new Leilao(produto1)
        );
        onView(allOf(withText("R$ 0,00"),
                withId(R.id.item_leilao_maior_lance)))
                .check(matches(isDisplayed()));
    }
    @After
    public void tearDown() throws IOException {
        IdlingRegistry.getInstance().unregister(AppIdlingResource.getIdlingResource());
        cleanDB();
        if (scenario != null) {
            scenario.close();
        }
    }


    private void salvarLeilao(Leilao... leiloes) throws IOException {
        for(Leilao leilao : leiloes){
            Leilao criarLeilao = webClient.salva(leilao);
            if(criarLeilao == null){
                Assert.fail("Falha ao salvar o leilão na API do leilão:" + leilao.getDescricao());
            }
        }
    }

    private void setupDataAndLaunch(Leilao... leiloes) throws IOException {
        salvarLeilao(leiloes);
        scenario = ActivityScenario.launch(ListaLeilaoActivity.class);
    }
}
