package com.coderyo.d20231221;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private TextView mTextTempState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextTempState = (TextView) findViewById(R.id.textTempState);

        printTempfile();
    }

    private void printTempfile() {
        // ���o�����x�s�C�骺�֨���Ƨ����|
        File internalCachedir = getCacheDir();
        // ���o�~���x�s�C�骺�֨���Ƨ����|
        File externalCachedir = getExternalCacheDir();

        StringBuilder buf = new StringBuilder();
        buf.append("internal Cache Dire:\n").append(internalCachedir.getPath())
                .append("\n\n");
        buf.append("external Cache Dire:\n").append(externalCachedir.getPath())
                .append("\n");

        mTextTempState.setText(buf.toString());
    }
}
