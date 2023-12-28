package com.coderyo.d20231228;


import com.coderyo.d20231228.R;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class SearchActivity extends Activity {

	private ListView mListView;
	private MyAdapter mAdapter;
	private Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mListView = (ListView)findViewById(R.id.listView1);
        //我們不需要條件 和 排序 所以兩個值傳入 null
        c = MyDb.getInstance(getApplicationContext()).select(null, null);
        mAdapter = new MyAdapter(getApplicationContext(),c);
        mListView.setAdapter(mAdapter);
    }

}
