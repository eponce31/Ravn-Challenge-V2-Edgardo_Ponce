package com.challenge.kotlin.ravn

import android.app.Application
import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory
import com.apollographql.apollo.cache.normalized.sql.ApolloSqlHelper
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import com.facebook.stetho.Stetho
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MainApplication : Application() {
    private var apolloClient: ApolloClient? = null

    private var appContext: Context? = null
    private val BASE_URL = "https://api.graph.cool/simple/v1/swapi"

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Stetho.initializeWithDefaults(this)
        //Create the ApolloSqlHelper. Please note that if null is passed in as the name, you will get an in-memory SqlLite database that
        // will not persist across restarts of the app.
        val apolloSqlHelper = ApolloSqlHelper.create(this, "db_name")
        //Create NormalizedCacheFactory
        val cacheFactory: NormalizedCacheFactory<*> = SqlNormalizedCacheFactory(apolloSqlHelper)
        //Create the cache key resolver, this example works well when all types have globally unique ids.
        val resolver: CacheKeyResolver = object : CacheKeyResolver() {
            override fun fromFieldRecordSet(
                field: ResponseField,
                recordSet: Map<String, Any>
            ): CacheKey {
                return formatCacheKey(recordSet["id"] as String?)
            }

            override fun fromFieldArguments(
                field: ResponseField,
                variables: Operation.Variables
            ): CacheKey {
                return formatCacheKey(field.resolveArgument("id", variables) as String?)
            }

            private fun formatCacheKey(id: String?): CacheKey {
                return if (id == null || id.isEmpty()) {
                    CacheKey.NO_KEY
                } else {
                    CacheKey.from(id)
                }
            }
        }
        //Build the Apollo Client
        apolloClient = ApolloClient.builder()
            .serverUrl(BASE_URL)
            .normalizedCache(cacheFactory, resolver)
            .okHttpClient(okHttpClient)
            .build()
    }

    fun apolloClient(): ApolloClient? {
        return apolloClient
    }

    fun getAppContext(): Context? {
        return appContext
    }
}