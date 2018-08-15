package com.kdblue.rxandroiddemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kdblue.rxandroiddemo.R;
import com.kdblue.rxandroiddemo.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyTodo> {

    private List<Todo> todoList;

    public TodoAdapter(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public MyTodo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_todo, parent, false);

        return new MyTodo(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTodo holder, int position) {
        holder.tvTitle.setText(todoList.get(position).getTitle());
        holder.tvStatus.setText("Completed : "+todoList.get(position).getCompleted());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyTodo extends RecyclerView.ViewHolder {

        private TextView tvTitle,tvStatus;

        public MyTodo(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.TvTitle);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
