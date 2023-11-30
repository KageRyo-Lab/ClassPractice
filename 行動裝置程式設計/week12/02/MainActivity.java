package com.coderyo.d20231130;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Activity01";
    private MediaPlayer mMediaPlayer01;
    private TextView mTextView01;
    private Button start, pasume, stop;
    protected void onDestroy() {
        if (mMediaPlayer01 != null)
            mMediaPlayer01.release();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "onCreate");
        mTextView01 = (TextView) findViewById(R.id.mp1);
        start = (Button) findViewById(R.id.start1);
        pasume = (Button) findViewById(R.id.pasume1);
        stop = (Button) findViewById(R.id.stop1);
        // 創建播放機MediaPlayer對象
        mMediaPlayer01 = new MediaPlayer();
        // 將音樂以Import的方式存儲在res/raw/always.mp3 */
        mMediaPlayer01 = MediaPlayer.create(MainActivity.this, R.raw.intro);
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                /****** 播放背景音樂 *******/
                try {
                    if (mMediaPlayer01 != null) {
                        mMediaPlayer01.stop();
                    }
                    // 在MediaPlayer取得播放資源與stop()之後
                    // 要準備Playback的狀態前一定要使用MediaPlayer.prepare()
                    mMediaPlayer01.prepare();
                    // 開始或回復播放
                    mMediaPlayer01.start();
                    Log.i(TAG, "開始播放音樂");
                    // 改變TextView為開始播放狀態
                    mTextView01.setText("正在播放");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    mTextView01.setText(e.toString());
                    e.printStackTrace();
                }
            }});
        pasume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    mMediaPlayer01.pause();
                    mTextView01.setText("暫停播放音樂");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    mTextView01.setText(e.toString());
                    e.printStackTrace();
                }
            }});
        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    mMediaPlayer01.stop();
                    // 改變TextView為停止播放狀態
                    Log.v(TAG, "停止播放音樂");
                    mTextView01.setText("停止播放音樂");
                    mMediaPlayer01.release();
                } catch (Exception e) {
                    mTextView01.setText(e.toString());
                    e.printStackTrace();
                }
            }});
    }
}
