package com.ojh.testproject.manager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkManager {
    // where my server lives.
    private static final String SERVER ="https://apis.daum.net/";
    Retrofit client;

    private NetworkManager(){
        //Retrofit Enviroment setting.

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
//                .addInterceptor(new RequestInterceptor())
//                .addInterceptor(new ResponseInterceptor())
                .build();

        client = new Retrofit.Builder()
                .baseUrl(SERVER) // where your server lives
                .client(okHttpClient) // what your http environment is
                .addConverterFactory(GsonConverterFactory.create()) // with what data format you want to transmit, in my case JSON
                .build();
    }

    // singleton holder pattern : thread safe, lazy class initialization, memory saving.
    public static class InstanceHolder{ public static final NetworkManager INSTANCE = new NetworkManager();}
    public static NetworkManager getInstance(){ return InstanceHolder.INSTANCE; }

    //API Return
    public <T> T getApi(Class<T> serviceClass){
        // connecting my API and my Retrofit environment and return.
        // then I'm able to call my API and make use of it
        return client.create(serviceClass);
    }

//    // custom req, res interceptors
//    public class ResponseInterceptor implements Interceptor{
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Response response = chain.proceed(chain.request());
//            if(!response.headers("Set-Cookie").isEmpty()){
//                HashSet cookies = new HashSet();
//                for (String header : response.headers("Set-Cookie")) {
//                    cookies.add(header);
//                }
//                PropertyManager.getInstance().setCookie(cookies);
//            }
//            return response;
//        }
//    }
//    public class RequestInterceptor implements  Interceptor{
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request.Builder builder = chain.request().newBuilder();
//            HashSet<String> preferences = PropertyManager.getInstance().getCookie();
//            for (String cookie : preferences) {
//                builder.addHeader("Cookie", cookie);
//                Log.v("NetworkManager", "Cookie : " + cookie);
//            }
//            return chain.proceed(builder.build());
//        }
//    }


}