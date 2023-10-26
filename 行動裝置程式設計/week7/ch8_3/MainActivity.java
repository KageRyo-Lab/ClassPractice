package com.coderyo.d20231026_4;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout myLayout;
    TextView txtShow1;
    TextView txtShow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myLayout=(ConstraintLayout)findViewById(R.id.myLayout);
        txtShow1=(TextView)findViewById(R.id.txtShow1);
        txtShow2=(TextView)findViewById(R.id.txtShow2);
        registerForContextMenu(txtShow1);
        registerForContextMenu(txtShow2);
    }

    protected static final int MENU_BLACKCOLOR = Menu.FIRST ;
    protected static final int MENU_WHITECOLOR = Menu.FIRST +1;
    protected static final int MENU_SMALLSIZE = Menu.FIRST +2;
    protected static final int MENU_LARGESIZE = Menu.FIRST +3;
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v==txtShow1){
            menu.add(0,MENU_BLACKCOLOR ,1,"黑色背景顏色");
            menu.add(0,MENU_WHITECOLOR ,2,"白色背景顏色");
        }
        else if (v==txtShow2){
            menu.add(0,MENU_SMALLSIZE ,1,"較小字體");
            menu.add(0,MENU_LARGESIZE ,2,"較大字體");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_BLACKCOLOR:
                myLayout.setBackgroundColor(Color.BLACK);
                break;
            case MENU_WHITECOLOR:
                myLayout.setBackgroundColor(Color.WHITE);
                break;
            case MENU_SMALLSIZE:
                txtShow1.setTextSize(14);
                txtShow2.setTextSize(14);
                break;
            case MENU_LARGESIZE:
                txtShow1.setTextSize(24);
                txtShow2.setTextSize(24);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
