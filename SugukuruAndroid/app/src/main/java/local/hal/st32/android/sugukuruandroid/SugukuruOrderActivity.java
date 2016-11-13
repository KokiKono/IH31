package local.hal.st32.android.sugukuruandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Tester on 2016/11/10.
 */

public class SugukuruOrderActivity extends ListActivity {

    private ListView _list;
    static List<Map<String, String>> pickList;
    private static final String _URL = net.ipAddress;
    private String SQL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru_order);
        _list = getListView();
        TextView realTime = (TextView) findViewById(R.id.datetime);
        Calendar ca = new GregorianCalendar();
        String strRealTime = ca.get(Calendar.YEAR) + "/" + (ca.get(Calendar.MONTH)+1) + "/" + ca.get(Calendar.DAY_OF_MONTH) + "　" + ca.get(Calendar.HOUR_OF_DAY) + "時" +ca.get(Calendar.MINUTE)+"分現在";
        realTime.setText(strRealTime);
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
                URL url = new URL(urlStr);
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

            re.setRequestId("orderId");
            re.setRequestId("customreName");
            re.setRequestId("orderDate");
            re.setResponseId("orderID");
            re.setResponseId("customerName");
            re.setResponseId("orderDate");
            re.setTableName("Order");
            pickList = re.json(result);
            Log.e("", ""+pickList);
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
        int[] to = {R.id.clOrderId, R.id.clCustomerName, R.id.clCustomerNameKana, R.id.clOrderDate, R.id.clOrderState};
        SimpleAdapter adapter = new SimpleAdapter(SugukuruOrderActivity.this, pickList, R.layout.order_row, from, to);
        setListAdapter(adapter);
        numberOfCase();
    }

    public void numberOfCase(){
        TextView num = (TextView)findViewById(R.id.allNumber);
        num.setText("全"+(pickList.size())+"件");
    }

    public void intentOrder(View view){}

    public void intentPicking(View view){
        Intent intent = new Intent(SugukuruOrderActivity.this, SugukuruActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

    public void onListItemClick(ListView listView, View view, int position, long id){
        super.onListItemClick(listView, view, position, id);

        Map<String, String> item = pickList.get(position);
        String orderId = item.get("orderID");
        Intent intent = new Intent(SugukuruOrderActivity.this, SugukuruOrderDetailActivity.class);
        intent.putExtra("id", orderId);
        startActivity(intent);
        finish();
    }
}
