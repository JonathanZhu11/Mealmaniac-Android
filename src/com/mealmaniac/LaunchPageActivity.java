package com.mealmaniac;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class LaunchPageActivity extends Activity {

	public static Client client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch_page);
		
		new GetClientTask().execute();
		
		//Pause for 1 seconds then move on >_>
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(LaunchPageActivity.this, LoginActivity.class);
                LaunchPageActivity.this.startActivity(mainIntent);
                LaunchPageActivity.this.finish();
            }
        }, 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launch_page, menu);
		return true;
	}
	
	private class GetClientTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			client = Client.getInstance();
			return null;
		}
	}
}
