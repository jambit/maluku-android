package com.jambit.maluku.android.maluku.android.malukuandroidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.R;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.EmployeeViewHolder> {

    private ArrayList<Person> people;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyListAdapter(ArrayList<Person> people) {
        this.people = people;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_person, parent, false);
        return new EmployeeViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        holder.txtEmpName.setText(people.get(position).getName());
        holder.txtEmpEmail.setText(people.get(position).getFloor());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return people.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtEmpName, txtEmpEmail;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtEmpName = (TextView) itemView.findViewById(R.id.txt_person_name);
            txtEmpEmail = (TextView) itemView.findViewById(R.id.txt_person_floor);
        }
    }
}