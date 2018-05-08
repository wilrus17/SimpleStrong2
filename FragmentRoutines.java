package com.will.simplestrong;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentRoutines extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Routine> lstRoutine;
    Context context;

    public FragmentRoutines() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_routines,container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.routineRecyclerView);
        RecyclerViewAdapterHome recyclerAdapter = new RecyclerViewAdapterHome(getContext(), lstRoutine);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHandler db = new DBHandler(getContext());
        lstRoutine = new ArrayList<>();
        lstRoutine.addAll(db.getRoutines());
    }


}
