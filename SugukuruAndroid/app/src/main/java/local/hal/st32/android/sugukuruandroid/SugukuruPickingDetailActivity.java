package local.hal.st32.android.sugukuruandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Tester on 2016/11/14.
 */

public class SugukuruPickingDetailActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru_detail);


    }

    public void intentOrder(View view){
        Intent intent = new Intent(SugukuruPickingDetailActivity.this, SugukuruOrderActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

    public void intentPicking(View view){
        Intent intent = new Intent(SugukuruPickingDetailActivity.this, SugukuruActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }
}
