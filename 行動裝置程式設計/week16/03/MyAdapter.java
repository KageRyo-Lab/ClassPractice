package com.coderyo.d20231228;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private Cursor c ;
	public MyAdapter(Context context,Cursor c){
		this.context = context;
		this.c = c;
	}
	@Override
	public int getCount() {
		return c.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoller v;
		if(convertView==null){
			v = new ViewHoller();
			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
			v.mTvId = (TextView)convertView.findViewById(R.id.tvID);
			v.mTvName = (TextView)convertView.findViewById(R.id.tvName);
			v.mTvAmount = (TextView)convertView.findViewById(R.id.tvAmount);
			v.mTvState = (TextView)convertView.findViewById(R.id.tvState);
			v.mTvAddress = (TextView)convertView.findViewById(R.id.tvAddress);
			convertView.setTag(v);
		}else{
			v = (ViewHoller) convertView.getTag();
		}
		if(c.getCount()!=0){
			if(c.moveToPosition(position)){
				v.mTvId.setText(MyDb.getInstance(context).getID(c));
			    v.mTvName.setText(MyDb.getInstance(context).getName(c));
			    v.mTvAmount.setText(MyDb.getInstance(context).getMoney(c));
			    v.mTvState.setText(MyDb.getInstance(context).getState(c));
			    v.mTvAddress.setText(MyDb.getInstance(context).getAddress(c));	
			}
		}
		return convertView;
	}

	class ViewHoller{
		TextView mTvId,mTvAmount,mTvState,mTvAddress,mTvName;
	}
}
