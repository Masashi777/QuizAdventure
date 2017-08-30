package com.lifeistech.android.myapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    // 問題を管理するリスト
    private ArrayList<Question> question_list = new ArrayList<>();
    // 描画更新用Handler
    private Handler handler;
    // 1問あたりの制限時間(sec)
    private int time = 10;
    // 問題数
    private int question_num;
    // 現在の問題
    private Question current_question = null;
    // 残り時間を表示するプログレスバー
    private ProgressBar progress;
    // 残りの制限時間(sec*10)
    private int rest_time;
    // 現在の問題番号
    private int current = 0;
    // 正解を選んだ数
    private int correct_num;

    //↑変数↓

    // TODO [01] ここから
    // 問題表示TextView
    private TextView question;
    // ステータス表示
    private TextView status;
    // 問題の画像表示Image
    private ImageView image;
    // 上段のボタン
    private Button button1;
    // 中段のボタン
    private Button button2;
    // 下段のボタン
    private Button button3;


    // オリジナル要素

    // モンスターの名前
    private TextView monster_name;
    // モンスターのHP
    private TextView monsterhp_num;
    int monsterhp;
    //攻撃力
    private TextView attack_num;
    int attack;
    // 自分のHP
    private TextView myhp_num;
    int myhp;

    // 相手のHP progress
    private ProgressBar progress_monsternum;
    // 自分のHP progress
    private ProgressBar progress_mynum;


    // TODO [01] ここまで

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        monsterhp = 100;
        myhp = 100;
        attack = 30;
        question_num = question_list.size();
        Log.d("onCreate",question_num+"");


        // TODO [02]ここから
        question = (TextView) findViewById(R.id.question_text);
        // status = (TextView) findViewById(R.id.status);
        image = (ImageView) findViewById(R.id.question_image);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        monsterhp_num = (TextView) findViewById(R.id.monsterhptext);
        myhp_num = (TextView) findViewById(R.id.myhptext);
        attack_num = (TextView) findViewById(R.id.attacktext);
        progress_monsternum = (ProgressBar) findViewById(R.id.progress_monster);
        progress_mynum = (ProgressBar) findViewById(R.id.progress_my);

        progress.setMax(time * 100);
        progress_monsternum.setMax(monsterhp * 100);
        progress_mynum.setMax(myhp * 100);

        makeQuestions();
        startQuestion();

        // TODO [02] ここまで
    }

    // 問題を作成する
    private void makeQuestions() {
        // TODO [03] ここから
        Question q1 = new Question(R.drawable.slime, "日本の首都は？", "東京", "京都", "大阪");
        Question q2 = new Question(R.drawable.slime, "一番大きな都道府県は？", "北海道", "長野", "岡山");
        Question q3 = new Question(R.drawable.slime, "昨日オイルマネーをばらまいていたのはだれ？", "石油王", "石炭王", "石灰王");
        Question q4 = new Question(R.drawable.slime, "昨日までいたカメラマンさんのニックネームは？", "んちょ", "うちょ", "んにょ");
        Question q5 = new Question(R.drawable.slime, "一番権力があるメンターは？", "キムテツ", "石油王", "おれ");
        Question q6 = new Question(R.drawable.slime, "100年に一度のイケメンは？", "タルト", "ぬっきー", "タナカ");
        Question q7 = new Question(R.drawable.slime, "みーたんの好きな食べ物は？", "とり肉", "レバー", "だて巻き");
        Question q8 = new Question(R.drawable.slime, "メンターの中で3番目に年齢がが高いのは？", "きさき", "ぬっきー", "麒麟");
        Question q9 = new Question(R.drawable.slime, "昨日オイルマネーをばらまいていたのはだれ？", "石油王", "石炭王", "石灰王");
        Question q10 = new Question(R.drawable.slime, "最南端の島がある都道府県は？", "東京", "沖縄", "鹿児島");
        Question q11 = new Question(R.drawable.slime, "", "", "", "");
        question_list.add(q1);
        question_list.add(q2);
        question_list.add(q3);
        question_list.add(q4);
        question_list.add(q5);
        question_list.add(q6);
        question_list.add(q7);
        question_list.add(q8);
        question_list.add(q9);
        question_list.add(q10);
        question_list.add(q11);

        // TODO [03] ここまで
    }

    // メニュー画面の作成
    /**private void menu() {
        // コマンドの内容をセット
        question.setText("コマンドを選べ！");
        button1.setText("たたかう");
        button2.setText("きあいだめ");
        button3.setText("にげる");

        nextQuestion();
    }**/

    /** private void click(View v) {

        // ボタンが押されたとき
        if (v.getId() == R.id.button1) {
            nextQuestion();
        }else if (v.getId() == R.id.button2 ){
            if (attack <= 20) {
                attack = 40;
                myhp = myhp - 10;
            }
        }else if (v.getId() == R.id.button3){
            Intent i = new Intent(this,StartActivity.class);
            startActivity(i);
            finish();

        }

        nextQuestion();
    }**/

    // 問題の表示を始める
    private void startQuestion() {
        question_num = question_list.size();
        Log.d("startQuestion", "question num1 =" + question_num);
        Log.d("startQuestion", "question num2 =" + question_num);

        nextQuestion();


    }


    private void nextQuestion() {
        monsterhp_num.setText(String.valueOf(monsterhp));
        myhp_num.setText(String.valueOf(myhp));
        attack_num.setText(String.valueOf(attack));
        progress_monsternum.setProgress(monsterhp * 100);
        progress_mynum.setProgress(myhp * 100);
        question_num = question_num - 1;
        Log.d("nextQuestion", "monsterhp =" + monsterhp);
        Log.d("nextQuestion", "myhp =" + myhp);
        Log.d("nextQuestion", "question num =" + question_num);


        if (monsterhp <= 0) {
            // Toast.makeText(this, "Result画面へ", Toast.LENGTH_SHORT).show();
            Log.d("nextQuestion", "question num1 =" + question_num);

            // モンスターのHPが0になったら
            // 結果画面に移動
            Intent i = new Intent(this, ResultActivity.class);
            startActivity(i);
            // 次のActivityに値を渡す
            // i.putExtra("QUESTION", question_num);
            // i.putExtra("CORRECT", correct_num);
            // そのままにしておくと画面が積み重なっていくので終了させる
            finish();


        } else if (myhp <= 0) {     // プレイヤーのHPが0になったら
            Intent i = new Intent(this, GameoverActivity.class);
            startActivity(i);
            // そのままにしておくと画面が積み重なっていくので終了させる
            finish();
            //return;
        } else if (question_num <= 0) {
            Intent i = new Intent(this, GameoverActivity.class);
            startActivity(i);
            // そのままにしておくと画面が積み重なっていくので終了させる
            finish();
        }


        /**
         (current >= question_num) {
         current = -1;
         // 次の問題がもう無い時
         // モンスターのHPが0になったら
         // 結果画面に移動
         Intent i = new Intent(this, ResultActivity.class);
         i.putExtra("QUESTION", question_num);
         i.putExtra("CORRECT", correct_num);
         startActivity(i);
         // そのままにしておくと画面が積み重なっていくので終了させる
         finish();
         return;
         **/


        // 問題の表示
        // TODO [04] ここから

        // ステータスの表示
        //  status.setText(current + "問中" + correct_num + "問正解"+ "残り" + (question_num - current) + "問");

        // 表示する問題
        current_question = question_list.get(current);

        // 問題文と画像を表示
        question.setText(current_question.question_text);

        // image.setImageResource(current_question.image_id);

        //問題文をボタンにセット
        String[] choices_text = current_question.getChoices();
        button1.setText(choices_text[0]);
        button2.setText(choices_text[1]);
        button3.setText(choices_text[2]);

        // 問題番号を次の番号へ
        current = current + 1;
        // 残り時間のカウントを始める
        startTimeLimitThread();

        // TODO [04] ここまで
    }

    // ボタンがタップされた時に呼ばれるイベントリスナー
    public void click(View v) {
        // TODO [05] ここから
        String buttonText = ((Button) v).getText().toString();
        // 正解ボタンがタップされたとき       if (条件) { //処理 }
        if (buttonText.equals(current_question.answer)) {
            //correct_num = correct_num + 1;
            monsterhp = monsterhp - attack;
            question.setText("効果は抜群だ！");
            monsterhp_num.setText(String.valueOf(monsterhp));
            progress_monsternum.setProgress(monsterhp * 100);
        } else {     // 不正解
            myhp = myhp - 5;
            myhp_num.setText(String.valueOf(myhp));
            progress_mynum.setProgress(myhp * 100);
        }

        // 次の問題へ
        nextQuestion();

        // TODO [05] ここまで
    }

    /**
     * 1問ごとの制限時間を管理するスレッドを起動する
     */
    private void startTimeLimitThread() {
        rest_time = time * 100;
        progress.setProgress(rest_time);
        // このThreadが担当する問題番号
        final int local_current = current;
        Thread t = new Thread() {
            @Override
            public void run() {
                while (rest_time >= 0) {
                    if (local_current != current) {
                        // すでにボタンをタップして次の問題に進んでいる
                        return;
                    }
                    try {
                        // 10ミリ秒待機
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            rest_time -= 1;
                            progress.setProgress(rest_time);
                        }
                    });
                }
                // まだ問題に解答していない場合
                if (local_current == current) {
                    myhp = myhp - 5;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();
                        }
                    });
                }
            }
        };
        t.start();
    }

    /**
     * 問題を管理するクラス
     */
    class Question {
        /**
         * 画面に表示する画像のリソースID
         */
        private final int image_id;
        /**
         * 問題文として表示する文字列
         */
        private final String question_text;
        /**
         * 正解の答え
         */
        private final String answer;
        /**
         * 不正解の答え①
         */
        private final String wrong_1;
        /**
         * 不正解の答え②
         */
        private final String wrong_2;

        public Question(int image_id, String question_text, String answer, String wrong_1, String wrong_2) {
            this.image_id = image_id;
            this.question_text = question_text;
            this.answer = answer;
            this.wrong_1 = wrong_1;
            this.wrong_2 = wrong_2;
        }

        /**
         * シャッフルした問題の選択肢を返すメソッド
         */
        public String[] getChoices() {
            // ボタンの位置をランダムにする
            String choices_tmp[] = {answer, wrong_1, wrong_2};
            // 配列からリストへ
            List<String> list = Arrays.asList(choices_tmp);
            // リストをシャッフル
            Collections.shuffle(list);
            // リストをStringの配列にする
            return list.toArray(new String[3]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

