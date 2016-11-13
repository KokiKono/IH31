package local.hal.st32.android.sugukuruandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by Tester on 2016/11/10.
 */

public class SugukuruOrderActivity extends ListActivity {

    private ListView _list;
    static List<Map<String, String>> pickList;
    private static final String _URL = "";
    private String SQL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru_order);
        _list = getListView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        RestAccess access = new RestAccess(_list);
        access.execute(_URL, SQL);
    }

    private class RestAccess extends AsyncTask<String, Void, String> {
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
            re.setRequestId("orderId", "customerName", "customerNameKana", "orderDate", "state");
            re.setResponseId("orderID", "customerName", "customerNameKana", "orderDate", "state");
            re.setTableName("Order");
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
        String[] from = {"orderID", "customerName", "customerNameKana", "orderDate", "state"};
        int[] to = {R.id.clOrderId, R.id.clCustomerName, R.id.clCustomerNameKana, R.id.clOrderState};
        SimpleAdapter adapter = new SimpleAdapter(SugukuruOrderActivity.this, pickList, R.layout.order_row, from, to);
        setListAdapter(adapter);
    }

    public void intentOrder(View view){}

    public void intentPicking(View view){
        Intent intent = new Intent(SugukuruOrderActivity.this, SugukuruActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }
}
