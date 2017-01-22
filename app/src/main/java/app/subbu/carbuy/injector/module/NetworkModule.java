package app.subbu.carbuy.injector.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import app.subbu.carbuy.BuildConfig;
import app.subbu.carbuy.injector.scope.PerApplication;
import app.subbu.carbuy.repository.network.RetrofitRestRepository;
import app.subbu.repository.Repository;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */
@Module
public class NetworkModule {

    public static final int CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB

    @Provides
    @PerApplication
    Repository provideRespository(Retrofit retrofit) {
        return new RetrofitRestRepository(retrofit);
    }

    @Provides
    @PerApplication
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @PerApplication
    // Application reference must come from ApplicationModule.class
    Cache provideOkHttpCache(Application application) {
        Cache cache = new Cache(application.getCacheDir(), CACHE_SIZE);
        return cache;
    }

    @Provides
    @PerApplication
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    @Provides
    @PerApplication
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        String endpointUrl = BuildConfig.apiEndpointUrl;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpointUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }


}
