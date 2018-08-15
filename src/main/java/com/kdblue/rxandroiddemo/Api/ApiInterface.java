package com.kdblue.rxandroiddemo.Api;

import com.kdblue.rxandroiddemo.model.Todo;
import com.kdblue.rxandroiddemo.model.TodoComment;
import com.kdblue.rxandroiddemo.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("comments")
    Observable<List<TodoComment>> getComments();

    @GET("users/{id}")
    Observable<User> getUserById(@Path("id") int Id);

    @GET("todos")
    Observable<List<Todo>> getTodoByUserId(@Query("userId") int userId);

}
