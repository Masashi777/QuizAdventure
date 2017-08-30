package com.lifeistech.android.QuizAdventure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends Activity {
    /**
     * 結果表示用TextView
     */
    /**
     * メッセージ表示用TextView
     */

    private TextView textView, textView2;
    private ImageView imageView;
    private ImageButton imageButton;

    private boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_result);

        // ひもずけ
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (ImageButton) findViewById(R.id.imageButton);

        // 結果を受け取る
        Intent intent = getIntent();
        result = intent.getBooleanExtra("result", true);

        if (result) {
            // クリア
            imageView.setImageResource(R.drawable.congratulation);
            textView.setText("げーむクリア");
            textView2.setText("おめでとう！");
        } else {
            // 失敗
            imageView.setImageResource(R.drawable.congratulation);
            textView.setText("ざんねん");
            textView2.setText("もういちどちゃれんじ！");
        }

        textView.setTypeface(Typeface.createFromAsset(getAssets(), "DragonQuestFC.ttf"));
        textView2.setTypeface(Typeface.createFromAsset(getAssets(), "DragonQuestFC.ttf"));

        // ボタンの設定
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * バックボタンが押された時にタイトル画面へ戻る
     */
    @Override
    public void onBackPressed() {
        finish();
    }


}