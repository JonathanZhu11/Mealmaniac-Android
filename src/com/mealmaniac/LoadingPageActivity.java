package com.mealmaniac;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class LoadingPageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading_page);
		
		//Pause for 1 seconds then move on >_>
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(LoadingPageActivity.this, LoginActivity.class);
                LoadingPageActivity.this.startActivity(mainIntent);
                LoadingPageActivity.this.finish();
            }
        }, 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loading_page, menu);
		return true;
	}

}
