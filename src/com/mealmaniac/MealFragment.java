package com.mealmaniac;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MealFragment extends Fragment {
	
	/* The argument key for the page number this fragment represents.
    */
	public static final String ARG_RESTAURANT = "restaurant";
	public static final String ARG_ITEM = "item";
	public static final String ARG_PRICE = "price";
	public static final String ARG_NUMBER = "number";
   
   	private int mealNumber;
   	private String restaurant;
   	private String item;
   	private String price;
			
	public static MealFragment create(int number, Meal meal) {
		Log.i("MealFragment","create:");
		MealFragment fragment = new MealFragment();
		Bundle args = new Bundle();
        
		args.putInt(ARG_NUMBER, number);
        args.putString(ARG_RESTAURANT, meal.getRestaurant());
        args.putString(ARG_ITEM, meal.getItem());
        args.putString(ARG_PRICE, "$"+meal.getPrice());
        fragment.setArguments(args);
		
        return fragment;
	}
	
	public MealFragment() {
		
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i("MealFragment","onCreate:");
        mealNumber = getArguments().getInt(ARG_NUMBER);
        restaurant = getArguments().getString(ARG_RESTAURANT);
        item = getArguments().getString(ARG_ITEM);
        price = getArguments().getString(ARG_PRICE);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		Log.i("MealFragment","layout inflating:");
		View view = inflater.inflate(R.layout.fragment_meal, container, false);
		((TextView)view.findViewById(R.id.number)).setText(getString(R.string.restaurant_number)+(mealNumber+1));
		((TextView)view.findViewById(R.id.restaurant_name_textview)).setText(restaurant);
		((TextView)view.findViewById(R.id.item_textview)).setText(item);
		((TextView)view.findViewById(R.id.price_textview)).setText(price);

		return view;
	}

}
