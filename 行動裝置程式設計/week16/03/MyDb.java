package com.coderyo.d20231228;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDb extends SQLiteOpenHelper {

	private static int VERSION = 1;//資料庫版本
	private static String DB_NAME = "MyDb.db";//資料庫名稱
	private static MyDb busDB ;
	public MyDb(Context context) {
		super(context, DB_NAME, null, VERSION);
	}
	/**
	 *  獲取資料庫產生實體
	 * @param context 上下文對象
	 * @return BusDB對象
	 */
	public static MyDb getInstance(Context context) {
		if(busDB==null){
			busDB = new MyDb(context);
			return busDB;
		}else{
			return busDB;	
		}
		
	}
	/**
	 * 創建表，定義表字段
	 * */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE Info (_id INTEGER PRIMARY KEY AUTOINCREMENT,id TEXT, name TEXT,phone TEXT,address TEXT,money TEXT,state INTEGER)");
	} 

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion == 1 && newVersion == 2) {// 升級判斷,如果再升級就要再加兩個判斷,從1到3,從2到3
			db.execSQL("ALTER TABLE register ADD phone TEXT;");
		}
	}
	/**
	 *   查詢表
	 * @param table 表名 有 BusCard 和 BusCardInfo
	 * @param where 條件
	 * @param orderBy 排序 沒有則傳入null
	 * @return 返回郵標
	 */
	public Cursor select(String where, String orderBy) {// 返回表中的資料,where是調用時候傳進來的搜索內容,orderby是設置中傳進來的列表排序類型
		StringBuilder buf = new StringBuilder("SELECT * FROM "+"Info");

		if (where != null) {
			buf.append(" WHERE ");
			buf.append(where);
		}

		if (orderBy != null) {
			buf.append(" ORDER BY ");
			buf.append(orderBy);
		}
       Log.e("buf", buf.toString());
		return (getWritableDatabase().rawQuery(buf.toString(), null));
	}
	/**
	 *  插入記錄
	 * @param id 訂單號
	 * @param name 姓名
	 * @param phone 電話
	 * @param address 地址
	 * @param money 金額
	 */
	public void insert(String id,String name,String phone,String address,String money,int state){
		ContentValues cv = new ContentValues();
		cv.put("id", id);
		cv.put("name", name);
		cv.put("phone", phone);
		cv.put("address", address);
		cv.put("money", money);
		cv.put("state", state);
		getReadableDatabase().insert("Info", "name", cv);
		
	}
	/**
	 * 獲取訂單號
	 * @param c 查詢後的游標
	 * @return 訂單號
	 */
	public String getID(Cursor c){
		return c.getString(1);
	}

	/**
	 * 獲取姓名
	 * @param c 查詢後的游標
	 * @return 姓名
	 */
	public String getName(Cursor c){
		return c.getString(2);
	}

	/**
	 * 獲取電話
	 * @param c 查詢後的游標
	 * @return 電話
	 */
	public String gePhone(Cursor c){
		return c.getString(3);
	}

	/**
	 * 獲取地址
	 * @param c 查詢後的游標
	 * @return 地址
	 */
	public String getAddress(Cursor c){
		return c.getString(4);
	}
	/**
	 * 獲取金額
	 * @param c 查詢後的游標
	 * @return 姓名
	 */
	public String getMoney(Cursor c){
		return c.getString(5);
	}
	/**
	 * 獲取狀態
	 * @param c 查詢後的游標
	 * @return 狀態
	 */
	public String getState(Cursor c){
		int state = c.getInt(6);
		if(state == 0 ){
			return "未出貨";
		}
		return "已出貨";
	}
}
