package com.ruheng.suiyue.network

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.Callback
import java.io.File
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


/**
 * Created by lvruheng on 2018/3/1.
 */
class OkhttpUtil private constructor(context: Context) {
    private var okhttpClient: OkHttpClient
    private lateinit var httpCacheDirectory: File
    private lateinit var cache: Cache
    private val mContext: Context = context

    companion object {
        @JvmStatic
        private lateinit var mInstance: OkhttpUtil

        /**
         * 单例获取OkhttpUtil
         * @return
         */
        fun getInstance(context: Context): OkhttpUtil {
            if (mInstance == null) {
                synchronized(OkhttpUtil::class) {
                    if (mInstance == null) {
                        mInstance = OkhttpUtil(context)
                    }
                }
            }
            return mInstance
        }
    }

    init {
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Log.e("OKHttp", "Could not create http cache", e)
        }
        val builder = OkHttpClient.Builder()
        builder.readTimeout(20, TimeUnit.SECONDS)//读取超时
        builder.connectTimeout(6, TimeUnit.SECONDS)//连接超时
        builder.writeTimeout(60, TimeUnit.SECONDS)//写入超时
        builder.cache(cache)
        builder.addInterceptor(CacheInterceptor(mContext))
        //支持HTTPS请求，跳过证书验证
        builder.sslSocketFactory(createSSLSocketFactory())
        builder.hostnameVerifier { _, _ -> true }
        okhttpClient = builder.build()
    }

    /**
     * get请求，同步方式，获取网络数据
     * @param url
     * @return
     */
    fun getDataSync(url: String): Response {
        val builder = Request.Builder()
        val request = builder.get().url(url).build()
        val call = okhttpClient.newCall(request)
        var response = call.execute()
        return response
    }

    /**
     * post请求，同步方式，提交数据
     * @param url
     * @param bodyParams
     * @return
     */
    fun postDataSync(url: String, bodyParams: Map<String, String>): Response {
        val requestBody = setRequestBody(bodyParams)
        val builder = Request.Builder()
        val request = builder.post(requestBody).url(url).build()
        val call = okhttpClient.newCall(request)
        val response = call.execute()
        return response
    }

    /**
     * get请求，异步方式，获取网络数据
     * @param url
     * @param networkCallback
     * @return
     */
    fun getDataAsync(url: String, networkCallback: Callback) {
        val builder = Request.Builder()
        val request = builder.get().url(url).build()
        val call = okhttpClient.newCall(request)
        call.enqueue(networkCallback)
    }

    fun postDataAsync(url: String, networkCallback: Callback, bodyParams: Map<String, String>) {
        val requestBody = setRequestBody(bodyParams)
        val builder = Request.Builder()
        val request = builder.post(requestBody).url(url).build()
        val call = okhttpClient.newCall(request)
        call.enqueue(networkCallback)
    }

    private fun setRequestBody(bodyParams: Map<String, String>): RequestBody {
        var requestBody: RequestBody?
        var formEncodingBuilder = FormBody.Builder()
        bodyParams?.entries?.forEach {
            val key = it.key
            val value = it.value
            formEncodingBuilder.add(key, value)
        }
        requestBody = formEncodingBuilder.build()
        return requestBody
    }

    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     * @return
     */
    private fun createSSLSocketFactory(): SSLSocketFactory? {
        var ssfFactory: SSLSocketFactory? = null
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, arrayOf<TrustManager>(TrustAllCerts()), SecureRandom())
            ssfFactory = sc.getSocketFactory()
        } catch (e: Exception) {
        }

        return ssfFactory
    }

    /**
     * 用于信任所有证书
     */
    inner class TrustAllCerts : X509TrustManager {
        override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
        }

        override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }

    }

}