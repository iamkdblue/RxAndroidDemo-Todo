package com.kdblue.rxandroiddemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ViewSwitcher;

import com.kdblue.rxandroiddemo.Api.ApiClient;
import com.kdblue.rxandroiddemo.Api.ApiInterface;
import com.kdblue.rxandroiddemo.adapter.TodoAdapter;
import com.kdblue.rxandroiddemo.model.Todo;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiService;
    private RecyclerView rvTodo;
    private TodoAdapter todoAdapter;
    private ProgressBar progressBar;
    private ViewSwitcher vSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvTodo = findViewById(R.id.rvTodo);
        progressBar = findViewById(R.id.progressBar);
        vSwitcher = findViewById(R.id.vSwitcher);

        rvTodo.setLayoutManager(new LinearLayoutManager(this));

        vSwitcher.setDisplayedChild(0);

        apiService = ApiClient.getClient().create(ApiInterface.class);


        /**
         * pass userId
         */
        apiService.getUserById(1)
                //.doOnSubscribe()
                .subscribeOn(Schedulers.io())

                //here i am passing userid for retriving user todos lists
                .flatMap(user -> apiService.getTodoByUserId(user.getId()))
                /*{
           "postId": 1,
               "id": 1,
               "name": "id labore ex et quam laborum",
               "email": "Eliseo@gardner.biz",
               "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
       }*/
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        vSwitcher.setDisplayedChild(1);
                    }
                })
                .subscribe(new Observer<List<Todo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Todo> todoList) {

                        Log.d("kkkk","onNext");

                       /* for (int i = 0; i < todos.size(); i++) {
                            Log.d("kkkk", "" + todos.get(i).getTitle());
                        }*/

                       todoAdapter = new TodoAdapter(todoList);
                       rvTodo.setAdapter(todoAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("kkkk", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("kkkk", "Done");
                        //rvTodo.setAdapter(new TodoAdapter());
                    }
                });
    }
}
