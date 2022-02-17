package com.lamnt.furniture.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lamnt.furniture.data.database.FurnitureDatabase
import com.lamnt.furniture.data.local.PreferenceRepository
import com.lamnt.furniture.data.remote.ApiService
import com.lamnt.furniture.data.remote.datasouce.CategoryDataSource
import com.lamnt.furniture.data.remote.datasouce.PreferenceDatasource
import com.lamnt.furniture.data.remote.datasouce.ProductionDatasource
import com.lamnt.furniture.data.remote.datasouce.UserDatasource
import com.lamnt.furniture.data.repository.impl.CategoryRepository
import com.lamnt.furniture.data.repository.impl.ProductionRepository
import com.lamnt.furniture.data.repository.impl.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gSon: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.102:8080/api/v1/")
        .addConverterFactory(GsonConverterFactory.create(gSon))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FurnitureDatabase =
        Room.databaseBuilder(context, FurnitureDatabase::class.java, "furniture")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideProductionDao(database: FurnitureDatabase) = database.getProductionDao()

    @Provides
    @Singleton
    fun providePreferenceDatasource(gSon: Gson, @ApplicationContext context: Context) =
        PreferenceDatasource(gSon, context)

    @Provides
    @Singleton
    fun providePreferenceRepository(dataSource: PreferenceDatasource) =
        PreferenceRepository(dataSource)

    @Provides
    @Singleton
    fun provideGSon(): Gson = GsonBuilder().setLenient().create()

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
    fun provideUserDataSource(apiService: ApiService) = UserDatasource(apiService)

    @Singleton
    @Provides
    fun provideCategoryRepository(dataSource: CategoryDataSource) = CategoryRepository(dataSource)

    @Singleton
    @Provides
    fun provideProductionRepository(dataSource: ProductionDatasource) =
        ProductionRepository(dataSource)

    @Singleton
    @Provides
    fun provideUserRepository(dataSource: UserDatasource) =
        UserRepository(dataSource)
}