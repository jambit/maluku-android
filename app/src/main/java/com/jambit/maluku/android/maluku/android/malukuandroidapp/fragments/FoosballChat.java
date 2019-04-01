package com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.R;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.adapter.MyListAdapter;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.User;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.network.MalukuOkHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoosballChat.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoosballChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoosballChat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // This ArrayList stores the current users
    private ArrayList<User> currentUsers = new ArrayList<>();

    // This timer is used to create a thread to send periodically REST requests
    private Timer timer;

    private MalukuOkHttpClient malukuOkHttpClient = new MalukuOkHttpClient();
    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private MyListAdapter adapter;

    public FoosballChat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoosballChat.
     */
    // TODO: Rename and change types and number of parameters
    public static FoosballChat newInstance(String param1, String param2) {
        FoosballChat fragment = new FoosballChat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foosball_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                try {

                    ArrayList<User> users = malukuOkHttpClient.getUsers();

                    currentUsers.clear();
                    currentUsers.addAll(users);

                    notifyDataSetChangedOnUiThread();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 1500);

        recyclerView = getActivity().findViewById(R.id.recycler_view);
        adapter = new MyListAdapter(currentUsers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button button = getActivity().findViewById(R.id.add_button);
        button.setOnClickListener(v -> buttonClicked());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // This method is used to tell the adapter that the data set has been changed
    private void notifyDataSetChangedOnUiThread() {
        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
    }

    // This method is used to open a AlertDialog. The user puts in his name and room number
    private void buttonClicked() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.add_dialog, null);
        final EditText editTextUserName = alertLayout.findViewById(R.id.et_username);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Add");

        // Set the view from XML inside AlertDialog
        alert.setView(alertLayout);

        // Disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userNameInput = editTextUserName.getText().toString();
                new Thread() {
                    public void run() {
                        try {
                            User user = malukuOkHttpClient.postPerson(userNameInput);
                            currentUsers.add(user);
                            notifyDataSetChangedOnUiThread();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                Toast.makeText(getContext(), "Name: " + userNameInput, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
