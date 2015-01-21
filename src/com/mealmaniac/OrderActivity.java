package com.mealmaniac;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OrderActivity extends Activity {

	public static final String EXTRA_RESTAURANT_NAME = "com.mealmaniac.RESTAURANT_NAME";
	public static final String EXTRA_ITEM_ORDERED = "com.mealmaniac.ITEM_ORDERED";
	public static final String EXTRA_PRICE = "com.mealmaniac.PRICE";
	public static final String EXTRA_NUMBER = "com.mealmaniac.NUMBER";

	private String restaurant = "RESTAURANT NAME";
	private String item = "ITEM ORDERED";
	private String price = "PRICE";
	private String number = "number";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		
		//Get the information from the intent
		Intent intent = getIntent();
		String caller = intent.getStringExtra("caller");
		
		if(caller.equals("MainActivity")) {
			//TODO: Get a Random Restaurant for some MANIA!!
			//TODO:Place the order here!!
		} else if(caller.equals("MealPreviewActivity")) {
			restaurant = intent.getStringExtra(MealPreviewActivity.EXTRA_RESTAURANT_NAME);
	        item = intent.getStringExtra(MealPreviewActivity.EXTRA_ITEM_ORDERED);
	        price = intent.getStringExtra(MealPreviewActivity.EXTRA_PRICE);
	        number = intent.getStringExtra(MealPreviewActivity.EXTRA_NUMBER);
	        Log.i("APP:", restaurant);
	        Log.i("APP:", item);
	        Log.i("APP:", price);
	        //TODO:Place the order here!! 
		}
		

		//Pause for 5 seconds then move on >_>
		new Handler().postDelayed(new Runnable() {
            @Override
         public void run() {
             final Intent mainIntent = new Intent(OrderActivity.this, ReviewActivity.class);
             mainIntent.putExtra(EXTRA_RESTAURANT_NAME, restaurant);
     		 mainIntent.putExtra(EXTRA_ITEM_ORDERED, item);
     		 mainIntent.putExtra(EXTRA_PRICE, price);
     		 mainIntent.putExtra(EXTRA_NUMBER, number);
             OrderActivity.this.startActivity(mainIntent);
             OrderActivity.this.finish();
         }
     }, 5000);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action_menu_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
