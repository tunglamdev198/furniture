package com.lamnt.furniture.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lamnt.furniture.data.remote.ApiService
import com.lamnt.furniture.data.remote.datasouce.CategoryDataSource
import com.lamnt.furniture.data.remote.datasouce.ProductionDatasource
import com.lamnt.furniture.data.repository.impl.CategoryRepository
import com.lamnt.furniture.data.repository.impl.ProductionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gSon: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gSon))
        .build()

    @Provides
    fun provideGSon(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideCategoryRemoteDataSource(apiService: ApiService) = CategoryDataSource(apiService)

    @Singleton
    @Provides
    fun provideProductionRemoteDataSource() = ProductionDatasource()

    @Singleton
    @Provides
    fun provideCategoryRepository(dataSource: CategoryDataSource) = CategoryRepository(dataSource)

    @Singleton
    @Provides
    fun provideProductionRepository(dataSource: ProductionDatasource) =
        ProductionRepository(dataSource)
}