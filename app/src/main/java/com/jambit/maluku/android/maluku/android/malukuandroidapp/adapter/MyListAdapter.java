package com.jambit.maluku.android.maluku.android.malukuandroidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.R;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private List<String> values;

    /**
     * Provide a reference to the views for each data item.
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Provide a suitable constructor (depends on the kind of dataset)
     */
    public MyListAdapter(List<String> myDataSet) {
        values = myDataSet;
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);

        return new ViewHolder(v);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder
     * @param position Get element from your dataset at this position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = values.get(position);
        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(v -> remove(position));

        holder.txtFooter.setText("Footer: " + name);
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     *
     * @return Size of the dataset
     */
    @Override
    public int getItemCount() {
        return values.size();
    }
}