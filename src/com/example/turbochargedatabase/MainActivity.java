package com.example.turbochargedatabase;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	TextView tv;
	Database db = new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv =(TextView)findViewById(R.id.tv);
        findViewById(R.id.normalinsert).setOnClickListener(this);
        findViewById(R.id.fastinsert).setOnClickListener(this);
        
    }

    private class Insert extends AsyncTask<Void, Void, Void>{
    	Date date1,date2;
    	ProgressDialog dg;
    	boolean isk;
    	public  Insert(boolean isk){
    		this.isk = isk;
    	}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dg.dismiss();
			Calendar cal2 = Calendar.getInstance();
	        cal2.set(ManageDate.Year(), ManageDate.Month(), ManageDate.Date(),ManageDate.Hour() , ManageDate.Minute(), ManageDate.Second());
	        date2 = cal2.getTime();
	        long milliSec1 = date1.getTime();
	        long milliSec2 = date2.getTime();
	 
	        long timeDifInMilliSec;
	        if(milliSec1 >= milliSec2)
	        {
	            timeDifInMilliSec = milliSec1 - milliSec2;
	        }
	        else
	        {
	            timeDifInMilliSec = milliSec2 - milliSec1;
	        }
	        long timeDifSeconds = timeDifInMilliSec / 1000;
	        
			 tv.setText("It takes "+timeDifSeconds+" seconds to insert");
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Calendar cal1 = Calendar.getInstance();
	        cal1.set(ManageDate.Year(), ManageDate.Month(), ManageDate.Date(),ManageDate.Hour() , ManageDate.Minute(), ManageDate.Second());
	         date1 = cal1.getTime();
			  dg = new ProgressDialog(MainActivity.this);
		    dg.setMessage("Loading...");
		    dg.setCancelable(false);
		    dg.show();
		    
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			db.open();
			if(isk)
		    db.NormalInsert();
			else db.FastInsert();
		    db.close();
			return null;
		}
    	
    }

   
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.normalinsert:
			new Insert(true).execute();
			break;
		case R.id.fastinsert:
			new Insert(false).execute();
			break;
		}
	}
}
