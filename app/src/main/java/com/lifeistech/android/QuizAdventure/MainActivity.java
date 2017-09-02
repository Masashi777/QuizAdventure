package com.lifeistech.android.QuizAdventure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    // TODO 変数
    // 問題を管理するリスト
    private ArrayList<Question> question_list = new ArrayList<>();
    // 描画更新用Handler
    private Handler handler;
    // 1問あたりの制限時間(sec)
    private int time = 10;
    // 問題数
    private int questionAmount;
    // 現在の問題
    private Question current_question = null;
    // 残りの制限時間(sec*10)
    private int restTime;
    // 現在の問題番号
    private int current = 0;
    // 正解を選んだ数
    private int correct_num;

    private Timer timer;

    private boolean result;


    // TODO Layout
    // ImageView
    private ImageView monsterImage, backImage;
    // TextView
    private TextView monsterNameText, monsterHpText, attackNumText, questionText, myHpText, statusText;
    // AnswerButton
    private Button ansBtn1, ansBtn2, ansBtn3;
    // ProgressBar
    private ProgressBar progressTime, progressMonsterNum, progressMyNum;

    int attack;

    int myhp, monsterHP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        handler = new Handler();
        monsterHP = 100;
        myhp = 100;
        attack = 30;
        questionAmount = question_list.size();
        Log.d("onCreate", questionAmount +"");


        // ひもづけ
        monsterNameText = (TextView) findViewById(R.id.monsterNameText);
        monsterHpText = (TextView) findViewById(R.id.monsterHpText);
        attackNumText = (TextView) findViewById(R.id.attackNumText);
        questionText = (TextView) findViewById(R.id.questionText);
        myHpText = (TextView) findViewById(R.id.myHpText);
        statusText = (TextView) findViewById(R.id.statusText);

        monsterImage = (ImageView) findViewById(R.id.monster_image);
        backImage = (ImageView) findViewById(R.id.backImage);

        backImage.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        ansBtn1 = (Button) findViewById(R.id.ansBtn1);
        ansBtn2 = (Button) findViewById(R.id.ansBtn2);
        ansBtn3 = (Button) findViewById(R.id.ansBtn3);

        progressTime = (ProgressBar) findViewById(R.id.progress_time);
        progressMonsterNum = (ProgressBar) findViewById(R.id.progress_monster);
        progressMyNum = (ProgressBar) findViewById(R.id.progress_my);


        // 数値の設定
        progressTime.setMax(time * 100);
        progressMonsterNum.setMax(monsterHP * 100);
        progressMyNum.setMax(myhp * 100);


        // 問題の作成
        question_list.add(new Question("日本の首都は？", "東京", "京都", "大阪"));
        question_list.add(new Question("一番大きな都道府県は？", "北海道", "長野", "岡山"));
        question_list.add(new Question("昨日オイルマネーをばらまいていたのはだれ？", "石油王", "石炭王", "石灰王"));
        question_list.add(new Question("昨日までいたカメラマンさんのニックネームは？", "んちょ", "うちょ", "んにょ"));
        question_list.add(new Question("一番権力があるメンターは？", "キムテツ", "石油王", "おれ"));
        question_list.add(new Question("100年に一度のイケメンは？", "タルト", "ぬっきー", "タナカ"));
        question_list.add(new Question("みーたんの好きな食べ物は？", "とり肉", "レバー", "だて巻き"));
        question_list.add(new Question("メンターの中で3番目に年齢がが高いのは？", "きさき", "ぬっきー", "麒麟"));
        question_list.add(new Question("昨日オイルマネーをばらまいていたのはだれ？", "石油王", "石炭王", "石灰王"));
        question_list.add(new Question("最南端の島がある都道府県は？", "東京", "沖縄", "鹿児島"));


        questionAmount = question_list.size();

        nextQuestion();

    }


    // メニュー画面の作成
    /**
     * // コマンドの内容をセット
     * question.setText("コマンドを選べ！");
     * ansBtn1.setText("たたかう");
     * ansBtn2.setText("きあいだめ");
     * ansBtn3.setText("にげる");
     */

    // 問題の表示を始める

    private void nextQuestion() {

        // テキストの表示
        monsterHpText.setText(String.valueOf(monsterHP));
        myHpText.setText(String.valueOf(myhp));
        attackNumText.setText(String.valueOf(attack));

        // プログレスバーの設定
        progressMonsterNum.setProgress(monsterHP * 100);
        progressMyNum.setProgress(myhp * 100);

        // 問題数カウント
        Log.d("nextQuestion", "monsterHP =" + monsterHP);
        Log.d("nextQuestion", "myhp =" + myhp);
        Log.d("nextQuestion", "question num =" + questionAmount);

        /**
         * 次の問題がもう無い時
         * モンスターのHPが0になったら
         * 結果画面に移動
         *
         * i.putExtra("QUESTION", questionAmount);
         * i.putExtra("CORRECT", correct_num);
         */


        // 問題を表示する

        // 表示する問題
        current_question = question_list.get(current);
        // 問題文と画像を表示
        questionText.setText(current_question.question_text);
        //問題文をボタンにセット
        String[] choices_text = current_question.getChoices();
        ansBtn1.setText(choices_text[0]);
        ansBtn2.setText(choices_text[1]);
        ansBtn3.setText(choices_text[2]);


        // ステータスの表示
        statusText.setText(current + "問中" + correct_num + "問正解"+ " 残り" + questionAmount + "問");

        // 問題番号を次の番号へ
        current++;

        // 残り時間のカウントを始める
        start();
    }

    // ボタンがタップされた時に呼ばれるイベントリスナー
    public void click(View v) {

        stop();

        // 残り問題数のカウント
        questionAmount--;

        // ボタンが押されたときの処理
        String buttonText = ((Button) v).getText().toString();

        if (buttonText.equals(current_question.answer)) {
            // 正解
            correct_num++;
            monsterHP = monsterHP - attack;
//            question.setText("効果は抜群だ！");
            if (monsterHP <= 0) {
                monsterHP = 0;
            }
            monsterHpText.setText(String.valueOf(monsterHP));
            progressMonsterNum.setProgress(monsterHP * 100);
        } else {
            // 不正解
            myhp = myhp - 5;
            myHpText.setText(String.valueOf(myhp));
            progressMyNum.setProgress(myhp * 100);
        }



        // 残りHPの確認
        if (monsterHP <= 0) {
            // モンスターのHPが0になったら
            nextAct(true);
        } else if (myhp <= 0) {
            // プレイヤーのHPが0になったら
            nextAct(false);
        } else if (questionAmount <= 1) {
            // 問題がなくなったら
            nextAct(false);
        }


        // 次の問題へ
        nextQuestion();
    }

    /**
     * 1問ごとの制限時間を管理するスレッドを起動する
     */
    public void start() {

        // タイマーの起動
        if (timer == null) {
            restTime = time;
            progressTime.setProgress(restTime * 100);

            final int local_current = current + 1;
            Log.d("TAG", "local_current = " + local_current + " culent = " + current);

            timer = new Timer(false);
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            restTime--;
                            progressTime.setProgress(restTime * 100);
                            if (restTime == 0) {
                                Log.d("TAG", "restTime = " + restTime + " local_current = " + local_current + " current = " + current);
                                stop();

                                // 残り問題数のカウント
                                questionAmount--;

                                // 残りHPの確認
                                if (monsterHP <= 0) {
                                    // モンスターのHPが0になったら
                                    nextAct(true);
                                } else if (myhp <= 0) {
                                    // プレイヤーのHPが0になったら
                                    nextAct(false);
                                } else if (questionAmount <= 1) {
                                    // 問題がなくなったら
                                    nextAct(false);
                                }
                                //timer.cancel();
                                if (local_current-1 == current) {
                                    myhp = myhp - 5;
                                    nextQuestion();

                                }
                            }
                            Log.d("timeの数字=", String.valueOf(restTime));


                        }
                    });


                }

            }, 0, 1000);


        } else {


        }

    }

    public void stop() {

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

    }

    // TODO 問題のオリジナルクラス
    public class Question {

        // 問題文として表示する文字列
        private final String question_text;
        // 正解の答え
        private final String answer;
        // 不正解の答え①
        private final String wrong_1;
        // 不正解の答え②
        private final String wrong_2;


        public Question(String question_text, String answer, String wrong_1, String wrong_2) {
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

    public void nextAct(boolean result) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //t.stop();
        timer.cancel();

        finishAndRemoveTask();
        handler.removeCallbacksAndMessages(null);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //t.stop();
        timer.cancel();

        finishAndRemoveTask();
        handler.removeCallbacksAndMessages(null);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        t.stop();
        timer.cancel();

        finishAndRemoveTask();
        handler.removeCallbacksAndMessages(null);
        finish();
    }

}