package com.lifeistech.android.myapp2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // フォント指定
       // TextView title = (TextView) findViewById(R.id.title);
//        title.setTypeface(Typeface.createFromAsset(getAssets(), "dq.ttf"));
    }

    public void click(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void ruleclick(View v) {
        Intent i = new Intent(this, rule.class);
        startActivity(i);
        finish();
    }
}
