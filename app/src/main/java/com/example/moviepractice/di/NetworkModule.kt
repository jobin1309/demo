package com.example.moviepractice.di

import com.example.moviepractice.utils.Constants.BASE_URL
import com.example.moviepractice.data.remote.MovieApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun providesGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun ProvideRetrofitInstance(gsonConverterFactory: GsonConverterFactory): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Singleton
    @Provides
    fun ProvidesApiServices(retrofit: Retrofit): MovieApiInterface {
        return retrofit.create(MovieApiInterface::class.java)
    }
}