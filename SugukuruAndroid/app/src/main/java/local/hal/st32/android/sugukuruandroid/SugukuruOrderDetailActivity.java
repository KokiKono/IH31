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
 * Created by Tester on 2016/11/14.
 */

public class SugukuruOrderDetailActivity extends ListActivity {

    private ListView _list;
    private List<Map<String, String>> pickList;
    private static final String _URL = net.ipAddress;
    private String orderId;
    private static final String method = "orderDetail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru_order_detail);

        Intent intent = getIntent();
        orderId = intent.getStringExtra("id");
        Log.e("orderId",orderId);
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
        access.execute(_URL);
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
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                Log.e("",orderId);
                URL url = new URL(urlStr+"?method=" + method + "&value=" + orderId);
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
            con.disconnect();
            return result;

        }

        @Override
        public void onPostExecute(String result) {
            Replace re = new Replace();

            re.setRequestId("num");
            re.setRequestId("productNumber");
            re.setRequestId("productName");
            re.setRequestId("amount");
            re.setRequestId("step");
            pickList = re.json(result);
            Log.e("", ""+pickList);
            preview();
        }

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
        String[] from = {"num", "productNumber", "productName", "amount", "step"};
        int[] to = {R.id.clNum, R.id.clProductNumber, R.id.clProductName, R.id.clAmount, R.id.clStep};
        SimpleAdapter adapter = new SimpleAdapter(SugukuruOrderDetailActivity.this, pickList, R.layout.order_detail_row, from, to);
        setListAdapter(adapter);
        numberOfCase();
    }

    public void numberOfCase(){
        TextView num = (TextView)findViewById(R.id.allNumber);
        num.setText("全"+(pickList.size())+"件");
    }

    public void intentOrder(View view){
        Intent intent = new Intent(SugukuruOrderDetailActivity.this, SugukuruOrderActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

    public void intentPicking(View view){
        Intent intent = new Intent(SugukuruOrderDetailActivity.this, SugukuruActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

}
