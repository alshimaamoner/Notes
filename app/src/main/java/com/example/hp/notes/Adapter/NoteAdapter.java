package com.example.hp.notes.Adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hp.notes.DataBase.Todo;
import com.example.hp.notes.HomeActivity;
import com.example.hp.notes.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
public List<Todo> todos;
OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public NoteAdapter(List<Todo> todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item,viewGroup,false);

       return new ViewHolder(view);
    }


    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Todo todo = todos.get(i);
        viewHolder.title.setText(todo.getTitle());
        viewHolder.date.setText(todo.getDate());
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(todo.getId(), todo.getTitle(), todo.getDate(), todo.getContent());
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if(todos==null)
            return  0;
        return todos.size();
    }
public void  ChangeData(List<Todo> todos){
        this.todos=todos;
        notifyDataSetChanged();
}



    public class ViewHolder extends RecyclerView.ViewHolder{

      TextView title;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            date=itemView.findViewById(R.id.date);
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(int id,String Title,String Date,String Content);
    }
}
