package local.hal.st32.android.sugukuruandroid;

import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ListView;
import android.util.Log;
import android.widget.SimpleAdapter;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SugukuruActivity extends ListActivity {

    private ListView _list;
    static List<Map<String, String>> pickList;
    private static final String _URL = "";
    private String SQL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru);

        _list = getListView();

    }

    @Override
    protected void onResume(){
        super.onResume();
        RestAccess access = new RestAccess(_list);
        access.execute(_URL, SQL);
    }

    private class RestAccess extends AsyncTask<String, Void, String>{
        private static final String DEBUG_TAG = "RestAccess";
        String result;

        ListView _list;
        public RestAccess(ListView list) {
            _list = list;
        }

        @Override
        public String doInBackground(String... params) {
            String urlStr = params[0];
            String strSQL = params[1];
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                URL url = new URL(urlStr + "?sql=" + strSQL);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                is = con.getInputStream();
                result = is2String(is);
            } catch (MalformedURLException ex) {
                Log.e(DEBUG_TAG, "URL変換失敗", ex);
            } catch (IOException ex) {
                Log.e(DEBUG_TAG, "通信失敗", ex);
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        Log.e(DEBUG_TAG, "InputStream解放失敗", ex);
                    }
                }
            }
            return result;

        }

        @Override
        public void onPostExecute(String result) {
            pickList = pauseJson(result);
            preview();
        }

        /**
         * InputStreamオブジェクトを文字列に変換するメソッド
         * 変換文字コードはUTF-8
         *
         * @param is 変換された文字列
         * @return 変換された文字列
         * @throws IOException 変換に失敗したときに発生
         */
        private String is2String(InputStream is) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            char[] b = new char[1024];
            int line;
            while (0 <= (line = reader.read(b))) {
                sb.append(b, 0, line);
            }
            return sb.toString();
        }

        private List<Map<String, String>> pauseJson(String result) {
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            try {

                Map<String, String> map = new HashMap<String, String>();
                JSONObject rootJSON = new JSONObject(result);
                JSONArray arrayJson = rootJSON.getJSONArray("pickingList");
                for (int i = 0; i < arrayJson.length(); i++) {
                    JSONObject data = arrayJson.getJSONObject(i);
                    map.put("pickID", data.getString("pickId"));
                    map.put("productID", data.getString("productId"));
                    map.put("productName", data.getString("productName"));
                    map.put("rackNumber", data.getString("rackNumber"));
                    map.put("needs", data.getString("needs"));
                    map.put("pickNum", data.getString("pickNum"));
                    map.put("state", data.getString("pickState"));
                    list.add(map);
                }


            } catch (JSONException ex) {
                Log.e(DEBUG_TAG, "JSON解析失敗", ex);
            }
            return list;
        }
    }


    public void preview(){
        String[] from = {"title", "pubDateStr"};
        int[] to = {android.R.id.text1,android.R.id.text2};
        SimpleAdapter adapter = new SimpleAdapter(SugukuruActivity.this, SugukuruActivity.pickList, R.layout.row, from, to);
        setListAdapter(adapter);
    }



}
