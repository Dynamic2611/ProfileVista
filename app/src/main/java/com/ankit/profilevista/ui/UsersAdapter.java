package com.ankit.profilevista.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ankit.profilevista.R;
import com.ankit.profilevista.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.VH> {

    private List<User> data = new ArrayList<>();
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClicked(User user, ImageView avatar);
    }

    public UsersAdapter(OnItemClickListener l) {
        this.listener = l;
    }

    public void setData(List<User> list) {
        this.data = list == null ? new ArrayList<>() : list;
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        User user = data.get(position);
        holder.tvName.setText(user.firstName + " " + user.lastName);
        holder.tvEmail.setText(user.email == null ? "" : user.email);
        Glide.with(holder.itemView.getContext())
                .load(user.image)
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.imgAvatar);

        holder.itemView.setOnClickListener(v -> listener.onItemClicked(user, holder.imgAvatar));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvName, tvEmail;
        VH(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
