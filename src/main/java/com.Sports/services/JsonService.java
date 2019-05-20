package com.Sports.services;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonService {
    public static JSONObject getFromRequest(HttpServletRequest request){
        StringBuffer jb = new StringBuffer();
        JSONObject jsonObject = null;
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        try {
            jsonObject =  new JSONObject(jb.toString());
        } catch (Exception e) {
            // crash and burn
            System.out.println(e.getMessage() + "parsing json from request");
        }

        return jsonObject;
    }




}
