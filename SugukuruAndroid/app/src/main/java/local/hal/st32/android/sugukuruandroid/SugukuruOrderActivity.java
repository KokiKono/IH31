package local.hal.st32.android.sugukuruandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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
    private List<Map<String, String>> pickList;
    private static final String _URL = net.ipAddress;
    private String SQL = "";
    private static final String method = "order";
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru_order);

        _list = getListView();

        TextView realTime = (TextView) findViewById(R.id.datetime);
        Calendar ca = new GregorianCalendar();
        String strRealTime = ca.get(Calendar.YEAR) + "/" + (ca.get(Calendar.MONTH)+1) + "/" + ca.get(Calendar.DAY_OF_MONTH) + "　" + ca.get(Calendar.HOUR_OF_DAY) + "時" +ca.get(Calendar.MINUTE)+"分現在";
        realTime.setText(strRealTime);
        setSpinner();
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
            result = "";
        }

        @Override
        public String doInBackground(String... params) {
            String urlStr = params[0];
            String strSQL = params[1];
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                URL url = new URL(urlStr+"?method=" + method);
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

            re.setRequestId("orderId");
            re.setRequestId("customreName");
            re.setRequestId("orderDate");
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

    /**
     * 検索項目をListViewに表示するメソッド
     */
    public void preview(){
        String[] from = {"orderId", "customreName", "customerNameKana", "orderDate", "state"};
        int[] to = {R.id.clOrderId, R.id.clCustomerName, R.id.clCustomerNameKana, R.id.clOrderDate, R.id.clOrderState};
        SimpleAdapter adapter = new SimpleAdapter(SugukuruOrderActivity.this, pickList, R.layout.order_row, from, to);
        setListAdapter(adapter);
        numberOfCase();
    }

    /**
     * 検索して取得した件数表示用メソッド
     */
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

    /**
     * ListViewのアイテムが押された時のメソッド
     * @param listView
     * @param view
     * @param position
     * @param id
     */
    public void onListItemClick(ListView listView, View view, int position, long id){
        super.onListItemClick(listView, view, position, id);

        Map<String, String> item = pickList.get(position);
        String orderId = item.get("orderId");
        Intent intent = new Intent(SugukuruOrderActivity.this, SugukuruOrderDetailActivity.class);
        intent.putExtra("id", orderId);
        startActivity(intent);
        finish();
    }

    /**
     * スピナーに値を入れるメソッド
     */
    private void setSpinner(){
        String[] labels = getResources().getStringArray(R.array.arr_order);
        spinner = (Spinner)findViewById(R.id.spRefinement);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner.setFocusable(false);
    }

    /**
     * スピナーを押された時のリスナーメソッド
     */
    public class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent, View view, int position, long id) {
            if (spinner.isFocusable() == false) {
                spinner.setFocusable(true);
                return;
            }
            // Spinner を取得
            Spinner spinner1 = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            String str = spinner1.getSelectedItem().toString();
            Log.e("spinner", str);
        }

        // 何も選択されなかった時の動作
        public void onNothingSelected(AdapterView parent) {}
    }
}
