package com.mealmaniac;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class ReviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//Get the message from the Intent
        Intent intent = getIntent();
        String restaurant = intent.getStringExtra(OrderActivity.EXTRA_RESTAURANT_NAME);
        String item = intent.getStringExtra(OrderActivity.EXTRA_ITEM_ORDERED);
        String price = intent.getStringExtra(OrderActivity.EXTRA_PRICE);
        String number = intent.getStringExtra(OrderActivity.EXTRA_NUMBER);

		
        //Set the text areas as the information
        TextView restaurant_textview = (TextView)findViewById(R.id.restaurant_name_review_textview);
        TextView item_textview = (TextView)findViewById(R.id.item_ordered_review_textview);
        TextView price_textview = (TextView)findViewById(R.id.price_review_textview);
        TextView number_textview = (TextView)findViewById(R.id.number_review_textview);
        
        restaurant_textview.setText(restaurant);
        item_textview.setText(item);
        price_textview.setText("$"+price);
        number_textview.setText(number);
        
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
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
		case R.id.action_settings:
			openSettings();
		}
		return super.onOptionsItemSelected(item);
	}

	private void openSettings() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

}
