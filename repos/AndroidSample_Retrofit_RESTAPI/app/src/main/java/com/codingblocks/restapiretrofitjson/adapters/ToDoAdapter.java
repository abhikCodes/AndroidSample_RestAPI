package com.codingblocks.restapiretrofitjson.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.models.ToDo;

import java.util.ArrayList;

/**
 * Created by abhik on 30/06/17.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>
{

    private Context context;
    private ArrayList<ToDo> todos;
    public ToDo toDo = new ToDo(1,1,"et porro tempora",true);
    public boolean m = toDo.getCompleted();

    public ToDoAdapter(Context context, ArrayList<ToDo> todos) {
        this.context = context;
        this.todos = todos;
    }

    public void updateToDos(ArrayList<ToDo> todos)
    {
        this.todos = todos;
        notifyDataSetChanged();
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_todo , parent , false);
        return new ToDoViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {

        ToDo thisToDo = todos.get(position);
        holder.tvToDo.setText(thisToDo.getTitle());
        holder.cbtf.setChecked(thisToDo.getCompleted());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder{
        TextView tvToDo;
        CheckBox cbtf;

        public ToDoViewHolder(View itemView) {
            super(itemView);
            tvToDo = (TextView) itemView.findViewById(R.id.tvCheck);
            cbtf = (CheckBox) itemView.findViewById(R.id.checkBox);
        }

    }
}
