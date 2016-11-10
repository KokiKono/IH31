package local.hal.st32.android.sugukuruandroid;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tester on 2016/11/10.
 *
 */

public class Replace {

    private static ArrayList<String> requestId;

    private static ArrayList<String> responseId;

    private static String tableName;

    private static final String DEBUG_TAG = "replaseJson";

    public void setRequestId(String... id){
        for(String date:id){
            requestId.add(date);
        }
    }

    public void setResponseId(String... id){
        for(String date: id){
            responseId.add(date);
        }
    }

    public void setTableName(String name){
        tableName = name;
    }

    public List<Map<String , String >> json(String result) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {

            Map<String, String> map = new HashMap<String, String>();
            JSONObject rootJSON = new JSONObject(result);
            JSONArray arrayJson = rootJSON.getJSONArray(tableName);
            for (int i = 0; i < arrayJson.length(); i++) {
                JSONObject data = arrayJson.getJSONObject(i);

                for(int x = 0; x < requestId.size(); x++) {
                    map.put(responseId.get(x), data.getString(requestId.get(x)));
                }

            }
            list.add(map);
        } catch (JSONException ex) {
            Log.e(DEBUG_TAG, "JSON解析失敗", ex);
        }
        return list;
    }
}
