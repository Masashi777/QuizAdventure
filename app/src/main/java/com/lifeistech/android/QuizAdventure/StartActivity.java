package com.lifeistech.android.QuizAdventure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class StartActivity extends Activity {

    private ImageButton startBtn, ruleBtn;
    private ImageView backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_start);


        startBtn = (ImageButton) findViewById(R.id.startBtn);
        ruleBtn = (ImageButton) findViewById(R.id.ruleBtn);
        backImage = (ImageView) findViewById(R.id.backImage);

        startBtn.setOnClickListener(MyClickListener);
        ruleBtn.setOnClickListener(MyClickListener);
    }

    private View.OnClickListener MyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startBtn:
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);

                    break;

                case R.id.ruleBtn:
                    Intent intent2 = new Intent(getApplicationContext(), RuleActivity.class);
                    startActivity(intent2);

                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        Snackbar.make(backImage, "アプリを終わりにしますか？", BaseTransientBottomBar.LENGTH_SHORT).setAction("YES", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).show();
    }
}