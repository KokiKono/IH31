package local.hal.st32.android.sugukuruandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ListActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private String[] mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru);

        _list = getListView();

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

//        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
//        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
//        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
//        // Set the list's click listener
//        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

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
            Replace re = new Replace();
            re.setRequestId("pickId", "productId", "productName", "rackNumber", "needs", "pickNum", "pickState");
            re.setResponseId("pickID", "productID", "productName", "rackNumber", "needs", "pickNum", "state");
            re.setTableName("PickingList");
            pickList = re.json(result);
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


    }

    public void preview(){
        String[] from = {"pickID", "productID", "productName", "rackNumber", "needs", "pickNum", "state"};
        int[] to = {R.id.clShape, R.id.clProductNumber, R.id.clProductName, R.id.clRackNumber, R.id.clNeeds, R.id.clPicking, R.id.clState};
        SimpleAdapter adapter = new SimpleAdapter(SugukuruActivity.this, SugukuruActivity.pickList, R.layout.row, from, to);
        setListAdapter(adapter);
    }

    public void intentOrder(View view){
        Intent intent = new Intent(SugukuruActivity.this, SugukuruOrderActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

    public void intentPicking(View view){}
}
