package ie.miao.yichong.wavy.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ie.miao.yichong.wavy.models.User;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Author:   yichongmiao
 * CreateAt: 05/06/2018.
 * */
public class RetrofitInterface {


    private final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    //create Retrofit instance
    private final static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GlobalVars.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

            .build();
    private final static RESTApiInterface RESTAPIINTERFACE = retrofit.create(RetrofitInterface.RESTApiInterface.class);


    public static RESTApiInterface getRESTApiInterface() {

        //Get interface
        return RESTAPIINTERFACE;

    }


    public interface RESTApiInterface {


        // load user data
        @NonNull
        @GET("user/all")
        Observable<User> getAllUserInformation();


        // delete user
        @NonNull
        @DELETE("user/{id}")
        Observable<ResponseBody> deleteUser(@Path("id") String id);


    }


}
