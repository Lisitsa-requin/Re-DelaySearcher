package com.requin.DelaySearcher;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONDataParse {
    public RailRouteEntity[] parse(JSONArray array){
        RailRouteEntity[] entities = new RailRouteEntity[array.length()];
        try{
            for(int i = 0; i < entities.length; i++){
                JSONObject object = array.getJSONObject(i);
                String companies = object.getString("company");
                String names = object.getString("name");
                String source = object.getString("source");
                entities[i] = new RailRouteEntity(companies, names, source);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return entities;
    }

}
