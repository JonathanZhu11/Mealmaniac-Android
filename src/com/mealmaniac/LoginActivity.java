package com.mealmaniac;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    public static final String EXTRA_EMAIL = "com.mealmaniac.EMAIL_ADDRESS";
    public static final String EXTRA_PASSWORD = "com.mealmaniac.PASSWORD";
    
    private Status status;
	private Intent intent;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
    	intent = new Intent(LoginActivity.this, MainActivity.class);
        EditText emailText = (EditText) findViewById(R.id.email_textarea);
        String email = emailText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.password_textarea);
        String password = passwordText.getText().toString();
  
        if(email.length()==0) {
        	setError("Please enter an Email");
        } else if(password.length()==0) {
        	setError("Please enter a Password");
        } else {
        	new LoginTask().execute(email, password);
        }
    }
    
    public void signup(View view) {
    	intent = new Intent(LoginActivity.this, MainActivity.class);
        EditText emailText = (EditText) findViewById(R.id.email_textarea);
        String email = emailText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.password_textarea);
        String password= passwordText.getText().toString();
        
        if(email.length()==0) {
        	setError("Please enter a valid Email");
        } else if(password.length()<=8) {
        	setError("Please enter a valid Password");
        } else {
        	intent.putExtra(EXTRA_EMAIL, email);
            intent.putExtra(EXTRA_PASSWORD, password);
        	new LoginTask().execute(email, password);
        }
        
    }
    
    public void start() {
    	startActivity(intent);
    }
    
    public void setError(String message) {
    	TextView error = (TextView)findViewById(R.id.error_textview);
    	error.setText(message);
    }
    
    private class LoginTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			Log.i("Doing in background", "YEP");
			String email = params[0];
			String password = params[1];
			com.mealmaniac.Status.Error error = Client.getInstance().service.login(email, password).error;
	        if(error == null) 
	        	return null;
	        return error.message;
		}

		protected void onPostExecute(String result) {
			if(result==null) {
				try {
					start();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			} else {
				setError("Incorrect email/password combination");
			}
		}
	}

}
