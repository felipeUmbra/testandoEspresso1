package br.com.alura.leilao.api.retrofit.client;

import java.io.IOException;

import br.com.alura.leilao.api.idlingresource.AppIdlingResource;
import br.com.alura.leilao.api.retrofit.TesteRetrofitInicializador;
import br.com.alura.leilao.api.retrofit.service.TesteService;
import br.com.alura.leilao.model.Leilao;
import retrofit2.Call;
import retrofit2.Response;

public class TesteWebClient extends WebClient {

    public TesteWebClient() {
        this.service = new TesteRetrofitInicializador().getTesteService();
    }
    private final TesteService service;
    public Leilao salva(Leilao leilao) throws IOException {
        AppIdlingResource.increment();
        Call<Leilao> call = service.salva(leilao);
        Response<Leilao> response = call.execute();
        decrementaIdlingResource();

        if(temDados(response)){
            return response.body();
        }
        return null;
    }

    public boolean cleanDB() throws IOException {
        Call<Void> call = service.cleanDB();
        Response<Void> response = call.execute();
        return response.isSuccessful();
    }
}
