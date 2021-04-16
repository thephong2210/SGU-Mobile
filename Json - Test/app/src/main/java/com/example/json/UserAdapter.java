package com.example.json;

import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.moshi.Json;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserItemViewHolder> {
//    public static String nameCountry, urlImage;

    private List<User> users;
    private Context context;

    public UserAdapter(List<User> users, Context c) {
        this.users = users;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new UserItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserItemViewHolder holder, int position) {
        User u = users.get(position);
        Picasso.with(context);
//                .load(u.flag)
//                .into(holder.ivAvatar);
        holder.tvLoginName.setText(u.name);
    }

    public class UserItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLoginName;
//        public ImageView ivAvatar;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            tvLoginName = (TextView) itemView.findViewById(R.id.tv_login_name);
//            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);



            tvLoginName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(view.getContext(),
//                            tvLoginName.getText() +" | "
//                                    + " Demo function", Toast.LENGTH_SHORT)
//                            .show();
//                    MainActivity Man = new MainActivity();
//                    Man.ChuyenClass2();

//                    nameCountry = tvLoginName.getText().toString();
//                    idCountry = tvIdCountry.getText().toString();

                    Intent i = new Intent();
                    i.setClass(context, ThongTinQuocGia.class);
                    context.startActivity(i);

                }
            });

        }

    }

}