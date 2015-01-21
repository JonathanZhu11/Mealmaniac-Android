package com.mealmaniac;


import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public class Client {

	private static Client instance = null;
	
	public Service service;
	private CookieManager cookieManager;
	private RestAdapter restAdapter;
	
	public interface Service {
		@GET("/ ")
		Status get();
		
		@FormUrlEncoded
		@POST("/signup")
		Status signup(@Field("email") String email, @Field("password") String password);
		
		@FormUrlEncoded
		@POST("/login")
		Status login(@Field("email") String email, @Field("password") String password);
	}
	
	private Client() {
		cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);
		
		restAdapter = new RestAdapter.Builder()
				.setServer("https://www.mealmaniac.com:3010/")
				.build();
		
		service = restAdapter.create(Service.class);
	}
	
	public static Client getInstance() {
		if(instance == null) { 
			instance = new Client();
		} 
		return instance;

	}
}
