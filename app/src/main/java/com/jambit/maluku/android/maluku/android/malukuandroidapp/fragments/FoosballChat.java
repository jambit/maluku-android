package com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.R;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.adapter.MyListAdapter;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.User;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.network.MalukuOkHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private MyListAdapter adapter;
    private ArrayList<User> currentUsers;

    private Timer timer;

    private MalukuOkHttpClient malukuOkHttpClient = new MalukuOkHttpClient();

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foosball_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentUsers = new ArrayList<>();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    List<User> users = malukuOkHttpClient.getUsers();

                    currentUsers.addAll(users);

                    /*getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }); */
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

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
}
