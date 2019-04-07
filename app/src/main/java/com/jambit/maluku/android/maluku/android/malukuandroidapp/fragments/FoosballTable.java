package com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.R;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.Foosball;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.network.MalukuOkHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoosballTable.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoosballTable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoosballTable extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Timer timer;
    private MalukuOkHttpClient malukuOkHttpClient = new MalukuOkHttpClient();

    private RadioButton radioButtonFoosballOne;
    private RadioButton radioButtonFoosballTwo;
    private RadioButton radioButtonFoosballThree;

    private ImageView imageViewFoosballOne;
    private ImageView imageViewFoosballTwo;
    private ImageView imageViewFoosballThree;

    public FoosballTable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoosballTable.
     */
    // TODO: Rename and change types and number of parameters
    public static FoosballTable newInstance(String param1, String param2) {
        FoosballTable fragment = new FoosballTable();
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
        return inflater.inflate(R.layout.fragment_foosball_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioButtonFoosballOne = getView().findViewById(R.id.radioButton);
        radioButtonFoosballTwo = getView().findViewById(R.id.radioButton2);
        radioButtonFoosballThree = getView().findViewById(R.id.radioButton3);

        imageViewFoosballOne = getView().findViewById(R.id.image_view_foosball_one);
        imageViewFoosballTwo = getView().findViewById(R.id.image_view_foosball_two);
        imageViewFoosballThree = getView().findViewById(R.id.image_view_foosball_three);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    List<Foosball> foosballList = malukuOkHttpClient.getSonicSensorData();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (Foosball foosball : foosballList) {
                                switch (foosball.getId()) {
                                    case 1:
                                        if (foosballList.get(0).isOccupied()) {
                                            radioButtonFoosballOne.setChecked(true);
                                            imageViewFoosballOne.setImageResource(R.drawable.kicker_table1_occupied);

                                        } else {
                                            radioButtonFoosballOne.setChecked(false);
                                            imageViewFoosballOne.setImageResource(R.drawable.kicker_table1_free);
                                        }
                                        break;
                                    case 2:
                                        if (foosballList.get(1).isOccupied()) {
                                            radioButtonFoosballTwo.setChecked(true);
                                            imageViewFoosballTwo.setImageResource(R.drawable.kicker_table2_occupied);

                                        } else {
                                            radioButtonFoosballTwo.setChecked(false);
                                            imageViewFoosballTwo.setImageResource(R.drawable.kicker_table2_free);
                                        }
                                        break;
                                    case 3:
                                        if (foosballList.get(2).isOccupied()) {
                                            radioButtonFoosballThree.setChecked(true);
                                            imageViewFoosballThree.setImageResource(R.drawable.kicker_table3_occupied);

                                        } else {
                                            radioButtonFoosballThree.setChecked(false);
                                            imageViewFoosballThree.setImageResource(R.drawable.kicker_table3_free);
                                        }
                                        break;
                                    default:
                                }
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 1500);
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
