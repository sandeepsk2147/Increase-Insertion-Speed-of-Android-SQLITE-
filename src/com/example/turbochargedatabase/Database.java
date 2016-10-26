package com.example.turbochargedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {

	private static final String dbName="TURBOCHARGE";
	Connect c;
	Context context;
	SQLiteDatabase sld;
	public Database(Context context){
		this.context=context;
	}
	private class Connect extends SQLiteOpenHelper
	{

		public Connect(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			arg0.execSQL("CREATE TABLE INFO (ID INTEGER PRIMARY KEY AUTOINCREMENT,INCC TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void NormalInsert(){
		ContentValues cv;
		for(int i=0;i<2500;i++){
			cv =new ContentValues();
			cv.put("INCC", String.valueOf(i));
			sld.insert("INFO", null, cv);
		}
	}
	public void FastInsert(){
		 try{
		String sql = "INSERT INTO INFO(INCC) VALUES(?)";
		 SQLiteStatement statement = sld.compileStatement(sql);
		
         sld.beginTransaction();
		for(int i=0;i<2500;i++){
			  statement.clearBindings();
              statement.bindString(1, String.valueOf(i));
              statement.execute();
	        }
			
			sld.setTransactionSuccessful();
			}catch(Exception e){}
			finally{sld.endTransaction();}
	}
	public void open(){
		c = new Connect(context, dbName, null, 1);
		sld = c.getWritableDatabase();
	}
	public void close(){
		sld.close();
		c.close();
	}
}
