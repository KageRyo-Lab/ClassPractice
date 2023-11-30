package com.coderyo.d20231130;

import android.app.Activity;
import android.os.Bundle;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ListView lvMus;
	private ImageButton ibPlay,ibPause,ibStop;
    private String msg,title;
    private int musicRes=0;
	private MediaPlayer mp; 
	private boolean isStoped = true; //true代表處於完全停止播放狀態
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buildViews();  //user define
    }

    private void buildViews(){
    	lvMus = (ListView)findViewById(R.id.lvIdMus);
    	ArrayAdapter <CharSequence> adMsuList = 
    			ArrayAdapter.createFromResource(
				this, R.array.lvMusList, 
				android.R.layout.simple_list_item_1);
    	lvMus.setAdapter(adMsuList);
    	lvMus.setOnItemClickListener(lvListener);

    	ibPlay = (ImageButton)findViewById(R.id.ibIdPlay);    
    	ibPause = (ImageButton)findViewById(R.id.ibIdPause);    
    	ibStop = (ImageButton)findViewById(R.id.ibIdStop);    

    	ibPlay.setOnClickListener(ibPlayListener);         	
    	ibPause.setOnClickListener(ibPauseListener);         	
    	ibStop.setOnClickListener(ibStopListener);         	
    }
	
	private OnItemClickListener lvListener = 
			new OnItemClickListener() {    
		public void onItemClick(AdapterView<?> parent, 
				View view, int position, long id) {
			msg="您選擇欲播放的音樂：\n";
			title = ((TextView) view).getText().toString();
			if (title.equals("天空之城(skycity.mid)")){
				musicRes=R.raw.skycity;
			} else if (title.equals("灌籃高手(basket.mid)")){
				musicRes=R.raw.basket;
			} if (title.equals("龍貓(toto.mid)")){
				musicRes=R.raw.toto;
			}
			msg=msg+title;
			Toast.makeText(MainActivity.this,
    				msg,
    				Toast.LENGTH_SHORT)
    		     .show();			
		}
	};
    private OnClickListener ibPlayListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {  
			if(mp == null || isStoped){
				mp =create(MainActivity.this,musicRes);
				isStoped = false;
			}
			  //設定左右聲道音量.數值在0~1之間
            mp.setVolume(1.0f,1.0f);
			mp.start();				
		}
	};
    private OnClickListener ibPauseListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {  
			if(mp == null || isStoped)	return;
			mp.pause();
		}
	};
    private OnClickListener ibStopListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {  
			if(mp == null || isStoped) 	return;
			mp.stop();
			isStoped = true;
		}
	};
	@Override
	protected void onPause() {
		super.onPause();
		if (mp != null) {
			mp.release();
			mp = null;
		}
	}
    public static MediaPlayer create(Context context, int resid)
    {
      AssetFileDescriptor afd;
      afd = context.getResources().openRawResourceFd(resid);
      if(afd == null)
        return null;
      try {
        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(afd.getFileDescriptor(), 
        		afd.getStartOffset(), afd.getLength());
        afd.close();
        mp.prepare();
        return mp;
      }catch (Exception e) {
		Log.e("播放音樂錯誤 !", e.toString());
		return null;
	  }		
   }
}
