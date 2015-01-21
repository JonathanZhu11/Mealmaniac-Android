package com.mealmaniac;

import java.util.LinkedList;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MealPreviewActivity extends FragmentActivity{

	public static final String EXTRA_RESTAURANT_NAME = "com.mealmaniac.RESTAURANT_NAME";
	public static final String EXTRA_ITEM_ORDERED = "com.mealmaniac.ITEM_ORDERED";
	public static final String EXTRA_PRICE = "com.mealmaniac.PRICE";
	public static final String EXTRA_NUMBER = "com.mealmaniac.NUMBER";

	private static final int PAGE_LEFT = 0;
	private static final int PAGE_MIDDLE = 1;
	private static final int PAGE_RIGHT = 2;

	private int selectedPageIndex;
	public int indexOfList;
	
	LinkedList<Meal> list = new LinkedList<Meal>();
	Meal[] mealSet = new Meal[3];
    
	/**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager pager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("MealPreviewActivity","onCreate:");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meal_preview);
		setupActionBar();
		
		//Create the original 3 meals to hold
        initList();
        
		Log.i("MealPreviewActivity", list.get(PAGE_MIDDLE).getRestaurant());
		
		// Instantiate a ViewPager and a PagerAdapter.
        pager = (ViewPager) findViewById(R.id.pager);
        
        //Set the first page you see to the middle
        pagerAdapter = new NextMealPagerAdapter(getFragmentManager());
        pager.setAdapter(pagerAdapter);
        setContent(PAGE_MIDDLE);
		indexOfList = PAGE_MIDDLE;
		selectedPageIndex = PAGE_MIDDLE;
		
        pager.setOnPageChangeListener(new OnPageChangeListener() {
        	
        	@Override
			public void onPageSelected(int position) {
				selectedPageIndex = position;
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				if (state == ViewPager.SCROLL_STATE_IDLE) {
 
					// user swiped to right direction --> left page
					if (selectedPageIndex == PAGE_LEFT) {
						list.addFirst(new Meal());
						pager.setCurrentItem(PAGE_MIDDLE, false);
						
						Log.i("Size of List",list.size()+"");
						
					// user swiped to left direction --> right page
					} else if (selectedPageIndex == list.size()-1) {
						list.addLast(new Meal());
						//setContent(indexOfList);
						Log.i("Index of List",indexOfList+"");
						Log.i("Size of List",list.size()+"");
					}
				}		
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}

        });
        
		Button finalize = (Button)findViewById(R.id.finalize_button);
		finalize.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MealPreviewActivity.this, OrderActivity.class);
			
				String restaurant = ((TextView) findViewById(R.id.restaurant_name_textview)).getText().toString();
				String item = ((TextView) findViewById(R.id.item_textview)).getText().toString();
				String price = ((TextView) findViewById(R.id.price_textview)).getText().toString();
				String number = ((TextView) findViewById(R.id.number)).getText().toString();
				
				intent.putExtra("caller", "MealPreviewActivity");
				intent.putExtra(EXTRA_RESTAURANT_NAME, restaurant);
				intent.putExtra(EXTRA_ITEM_ORDERED, item);
				intent.putExtra(EXTRA_PRICE, price);
				intent.putExtra(EXTRA_NUMBER, number);
				
				startActivity(intent);
			}
			
		});
	}
	
	private void initList() {
		for(int i=0; i<3; i++) {
			list.add(new Meal());
		}
		
	}

	private void setContent(int middleIndex) {
		mealSet[1] = list.get(middleIndex);
		pager.setCurrentItem(PAGE_MIDDLE, false);
		mealSet[0] = list.get(middleIndex-1);
		mealSet[2] = list.get(middleIndex+1);
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

		Log.i("MealPreviewActivity","onCreateOptionsMenu:");
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

/*	@Override
	public void onClick(View v) {
		int targetId = v.getId();
		
		if (targetId == R.id.shuffle_button) {		
		
		} else 
		
		if (targetId == R.id.finalize_button) {
			Intent intent = new Intent(this, OrderActivity.class);
			
			String restaurant = ((TextView) findViewById(R.id.restaurant_name_textview)).getText().toString();
			String item = ((TextView) findViewById(R.id.item_textview)).getText().toString();
			String price = ((TextView) findViewById(R.id.price_textview)).getText().toString();

			intent.putExtra("caller", "MealPreviewActivity");
			intent.putExtra(EXTRA_RESTAURANT_NAME, restaurant);
			intent.putExtra(EXTRA_ITEM_ORDERED, item);
			intent.putExtra(EXTRA_PRICE, price);
			
			startActivity(intent);
		}
	}*/
	
	/*
    private class NextMealPagerAdapter extends FragmentStatePagerAdapter {

		public NextMealPagerAdapter(FragmentManager fm) {
            super(fm);
        }
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			super.destroyItem(container, position, object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return ((MealFragment)object).getView()==view;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			MealFragment fragment = (MealFragment)getItem(position);
			container.addView(fragment.getView());
			/*MealFragment fragment = (MealFragment)getItem(position);
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.add(R.id.pager, fragment);
			fragmentTransaction.commit();***
			return fragment;
		}
		
        @Override
        public Fragment getItem(int position) {
        	Meal meal = mealSet[position];
        	Log.i("HELLO WORLD",indexOfList+"");
            return MealFragment.create(indexOfList, meal);
        }

		@Override
		public int getCount() {
			return 3;
		}
    }*/
	private class NextMealPagerAdapter extends FragmentStatePagerAdapter {

		public NextMealPagerAdapter(FragmentManager fm) {
            super(fm);
        }

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Fragment getItem(int position) {
			Meal meal = list.get(position);
			return MealFragment.create(position, meal);
		}
    }
}
