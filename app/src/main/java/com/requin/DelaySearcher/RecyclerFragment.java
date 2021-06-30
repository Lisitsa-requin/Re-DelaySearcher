package com.requin.DelaySearcher;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment implements OnRecyclerListener{
    public interface FragmentListener{
        void onRecycleEvent();
    }

    private FragmentListener fragmentListener;
    private View fragmentView;
    private RecyclerView recyclerView;
    private RailRouteEntity[] entity;

    @Override
    public void onAttach(@NonNull @org.jetbrains.annotations.NotNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentListener){
            fragmentListener = (FragmentListener)context;
        }

    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.item_fragment, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String api_url = "https://tetsudo.rti-giken.jp/free/delay.json";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, api_url, null,
                response -> {
                    JSONDataParse parse = new JSONDataParse();
                    entity = parse.parse(response);
                    if(entity != null){
                        recyclerView = (RecyclerView)fragmentView.findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        RecyclerAdapter adapter = new RecyclerAdapter(requireContext(), entity, this);
                        recyclerView.setAdapter(adapter);
                    }
                },
                error -> {

                });

        queue.add(request);
        queue.start();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Override
    public void onRecyclerClicked(View v, int position) {
        fragmentListener.onRecycleEvent();
    }
}
