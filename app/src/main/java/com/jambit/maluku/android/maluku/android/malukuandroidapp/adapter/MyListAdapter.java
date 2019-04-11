package com.jambit.maluku.android.maluku.android.malukuandroidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.R;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.User;

import java.util.ArrayList;
import java.util.Iterator;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.EmployeeViewHolder> {

    private ArrayList<User> users;

    public MyListAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_person, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        holder.txtEmpName.setText(users.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public void addItem(User user) {
        users.add(user);
    }

    public User getUser(String userId) {
        for(User user : users) {
            if(user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public void removeItem(String userId) {
        for(Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.getId().equals(userId)) {
                iterator.remove();
            }
        }
    }

    public boolean containsUser(String userId) {
        for(User user : users) {
            if(user.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtEmpName;
        ImageButton imageButton;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtEmpName = itemView.findViewById(R.id.txt_person_name);
        }
    }
}