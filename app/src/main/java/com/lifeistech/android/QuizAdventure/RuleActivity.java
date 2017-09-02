package com.lifeistech.android.QuizAdventure;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class RuleActivity extends Activity {

    private ImageButton backBtn;
    private ImageView backImage;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_rule);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backImage = (ImageView) findViewById(R.id.backImage);
        textView = (TextView) findViewById(R.id.textView);

        backImage.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "DQ.TTF"));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * バックボタンが押された時にタイトル画面へ戻る
     */

    /**
     * タイトル画面へ戻ります
     */

}