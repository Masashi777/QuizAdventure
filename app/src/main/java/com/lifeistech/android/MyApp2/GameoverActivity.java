package com.lifeistech.android.myapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GameoverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
    }

    /**
     * バックボタンが押された時にタイトル画面へ戻る
     */
    @Override
    public void onBackPressed() {
        backToTitle();
    }

    public void click(View v) {
        backToTitle();
    }

    /**
     * タイトル画面へ戻ります
     */
    private void backToTitle() {
        Intent i = new Intent(this, StartActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        finish();
    }
}
