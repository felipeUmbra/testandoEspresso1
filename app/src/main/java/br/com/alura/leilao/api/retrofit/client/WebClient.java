package br.com.alura.leilao.api.retrofit.client;

import android.os.Handler;
import android.os.Looper;

import br.com.alura.leilao.api.idlingresource.AppIdlingResource;
import retrofit2.Response;

abstract class WebClient {

    protected <T> boolean temDados(Response<T> response) {
        return response.isSuccessful() && response.body() != null;
    }

    protected void decrementaIdlingResource() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                AppIdlingResource.decrement();
            }
        });
    }
}
