package com.codingblocks.restapiretrofitjson.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.activities.PostsActivity;
import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.activities.ToDoActivity;
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener;
import com.codingblocks.restapiretrofitjson.models.User;

import java.util.ArrayList;

/**
 * Created by championswimmer on 29/06/17.
 */

public class UserAdapter
        extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public static final String TAG = "UA";

    private Context context;
    private ArrayList<User> users;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    public void updateUsers(ArrayList<User> newUserList) {
        this.users = newUserList;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_user, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final User thisUser = users.get(position);

        holder.tvUserUsername.setText(thisUser.getUsername());
        holder.tvUserName.setText(thisUser.getName());
        holder.tvUserPhone.setText(thisUser.getPhone());
        holder.tvUserEmail.setText(thisUser.getEmail());

//        holder.thisView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(thisUser.getId(), PostsActivity.class);
//                }
//            }
//        });
        holder.btnshowPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(thisUser.getId(), PostsActivity.class);
                }
            }
        });
        holder.btnshowToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(thisUser.getId(), ToDoActivity.class);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserUsername, tvUserName, tvUserPhone, tvUserEmail;
        Button btnshowPost, btnshowToDo;

        public UserViewHolder(View itemView) {
            super(itemView);
            //thisView = itemView;
            tvUserEmail = (TextView) itemView.findViewById(R.id.tvUserEmail);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvUserPhone = (TextView) itemView.findViewById(R.id.tvUserPhone);
            tvUserUsername = (TextView) itemView.findViewById(R.id.tvUserUsername);
            btnshowPost = (Button) itemView.findViewById(R.id.btnShowPost);
            btnshowToDo = (Button) itemView.findViewById(R.id.btnShowToDo);
        }
    }
}
