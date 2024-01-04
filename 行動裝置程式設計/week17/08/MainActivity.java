package com.coderyo.d20240104;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "WiFi_1";

    private WifiManager wifiManager;
    private ListView listView;
    private String mSSID;
    private ImageView ivWiFiOnOff;
    private boolean isWiFiOn;
    private int itemClicked = -1;

    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<Integer> networkIdList = new ArrayList<>();
    private ArrayList<Integer> signalLevelList = new ArrayList<>();

    Runnable runReconnectWiFi = new Runnable(){
        @Override
        public void run() {
            if(itemClicked>-1) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(networkIdList.get(itemClicked), true);
                wifiManager.reconnect();
                Toast.makeText(MainActivity.this, "itemClicked = " + itemClicked
                                + "\nid = " + networkIdList.get(itemClicked) ,
                        Toast.LENGTH_SHORT).show();
            }
            scanWiFiAfter2s();
        }
    };

    private BaseAdapter adapter = new BaseAdapter(){

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.wifi_layout, null);
            TextView tv = (TextView) view.findViewById(R.id.tvWifi);
            ImageView select = view.findViewById(R.id.select);
            ImageView signal = view.findViewById(R.id.ivWifiSignal);

            if(wifiManager.isWifiEnabled()) {
                tv.setText(arrayList.get(i));
                tv.setHeight(findViewById(R.id.tvHint).getHeight());
                tv.setGravity(Gravity.CENTER_VERTICAL);

                if(mSSID!=null) {
                    if(mSSID.equals(arrayList.get(i))) {
                        select.setImageResource(R.drawable.select);
                    }
                    else {
                        select.setImageResource(R.drawable.not_select);
                    }
                }
                else {
                    Log.e(TAG, i + "getView: mSSID==null");
                }

                switch(signalLevelList.get(i)) {
                    case 0:
                        signal.setImageResource(R.drawable.wifi1);
                        break;
                    case 1:
                        signal.setImageResource(R.drawable.wifi2);
                        break;
                    case 2:
                        signal.setImageResource(R.drawable.wifi3);
                        break;
                }
            }
            return view;
        }
    };

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);

            if(results.size()==0) {
                arrayList.clear();
                return;
            }

            if(wifiManager.isWifiEnabled()) {
                for (ScanResult scanResult : results) {

                    int networkId = getNetworkId(scanResult);

                    int level = scanResult.level;
                    int signalLevel = wifiManager.calculateSignalLevel(level,3);
                    Log.e(TAG, "scanResult's SignalLevel => " + signalLevel);
                    Log.e(TAG, "scanResult.capabilities => " + scanResult.capabilities);

                    if(networkId!=-1) {
                        arrayList.add(scanResult.SSID);
                        networkIdList.add(networkId);
                        signalLevelList.add(signalLevel);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ivWiFiOnOff = (ImageView) findViewById(R.id.ivWiFiOnOff);
        ivWiFiOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isWiFiOn) {
                    wifiManager.setWifiEnabled(false);
                    ivWiFiOnOff.setImageResource(R.drawable.off);
                }
                else {
                    wifiManager.setWifiEnabled(true);
                    ivWiFiOnOff.setImageResource(R.drawable.on);
                }
                scanWiFiAfter2s();
                isWiFiOn = !isWiFiOn;
            }
        });

        listView = (ListView) findViewById(R.id.wifiList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(mSSID.equals(arrayList.get(i))) {
                    Toast.makeText(MainActivity.this,
                            mSSID + " is selected!", Toast.LENGTH_SHORT).show();
                }
                else {
                    itemClicked = i;
                    listView.post(runReconnectWiFi);
                }
            }
        });

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
            scanWiFiAfter2s();
        }
        else {
            scanWifi();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(wifiReceiver);
        }
        catch (IllegalArgumentException e) {
        }
    }

    private void scanWiFiAfter2s() {
        listView.postDelayed(new Runnable(){
            @Override
            public void run() {
                scanWifi();
            }}, 2000);
    }

    private void scanWifi() {
        itemClicked = -1;
        arrayList.clear();
        networkIdList.clear();
        signalLevelList.clear();
        adapter.notifyDataSetChanged();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
    }

    private int getNetworkId(ScanResult scanResult) {
        if (scanResult.BSSID == null || scanResult.SSID == null || scanResult.SSID.isEmpty() || scanResult.BSSID.isEmpty())
            return -1;
        String ssid = "\"" + scanResult.SSID + "\"";

        List<WifiConfiguration> configurations = wifiManager.getConfiguredNetworks();
        if (configurations == null) return -1;

        for (final WifiConfiguration config : configurations) {
            if (ssid.equals(config.SSID)) {
                if(config.status == 0) {
                    mSSID = config.SSID;
                    mSSID = mSSID.replaceAll("\"", "");
                    isWiFiOn = true;
                    ivWiFiOnOff.setImageResource(R.drawable.on);
                }
                return config.networkId;
            }
        }
        return -1;
    }
}
