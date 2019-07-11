package ru.ftc.android.shifttemple.features.login.data.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.ftc.android.shifttemple.features.login.domain.model.User;

public interface UserApi {

    @GET("/users/getAllUsers")
    Call<List<User>> getAllUsers();

    @GET("users/getUser/{id}")
    Call<User> getUser(@Path("id") String id);
}
