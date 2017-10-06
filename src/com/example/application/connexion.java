package com.example.application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class connexion extends Activity {
	EditText login,password;
	 Spinner choix;
	 public static  String nom_personne,id_c;
	
	 private static final String URL = "http://10.0.2.2/pfe/Android/cnx_client.php";
	 private static final String URL2 = "http://10.0.2.2/pfe/Android/cnx_visiteur.php";
	 @Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.connexion); 
			 login=(EditText) findViewById(R.id.ed_login);
			 password=(EditText) findViewById(R.id.ed_password);
			 TextView inscrire=(TextView) findViewById(R.id.ed_inscrire);
			Button cnx=(Button) findViewById(R.id.btn_cnx);
			 choix=(Spinner) findViewById(R.id.id_choix);
			 
			 
				 ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.choixx, android.R.layout.simple_spinner_item);
			     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // ce ligne pour ajouter le radio bouton devant chaque item
				 choix.setAdapter(adapter);
			
			inscrire.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					 AlertDialog.Builder alerte = new AlertDialog.Builder(connexion.this); // declaration d'une alerte dialogue
					   
					   alerte.setMessage("Select your profile");// message de l'alerte
					   alerte.setPositiveButton("Service", new DialogInterface.OnClickListener() { // ajouter un bouton pour l'alerte 
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
							 		Intent i = new Intent(connexion.this,inscription_client.class);
								    startActivity(i);
						}
					});
					   
					   
					 
					   alerte.setNegativeButton("Customer", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							Intent i = new Intent(connexion.this,inscription_visiteur.class);
						    startActivity(i);
						}
					});
					   
					   alerte.show(); 
					
				}
			});
		
			cnx.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {

	String log= login.getText().toString();
	String pass= password.getText().toString();


	if(log.equals("") || pass.equals(""))
	{

		Toast.makeText(connexion.this, " veuillez saisir le champs login et password", Toast.LENGTH_SHORT).show();
		return;
	}


	   String x=choix.getSelectedItem().toString();
	if(x.equals("Customer"))
	{
		
		String result =null;
		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		ArrayList<NameValuePair> nameValuePairs=new ArrayList<NameValuePair> ();
		nameValuePairs.add(new BasicNameValuePair("login",log));
		nameValuePairs.add(new BasicNameValuePair("password",pass));
		//Execute HTTP Post Request
		try{
			HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(URL2);
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		is = entity.getContent();    
		}catch(Exception e){
		Log.e("log_tag", "Error in http connection " + e.toString());
		} 
		try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
			
		}
		is.close();
		result=sb.toString();

		}catch(Exception e){
		Log.e("log_tag", "Error in http connection " + e.toString());
		}
		
		try {
		
		JSONArray jArray = new JSONArray(result);
		int b=jArray.length();

			  JSONObject json_data = jArray.getJSONObject(0); 

		  String passe1 =	json_data.getString("password").toString(); 
		  String  login1 =json_data.getString("login").toString();  
		  nom_personne = json_data.getString("nom_visiteur").toString()+" "+json_data.getString("prenom_visiteur").toString();  
		  id_c = json_data.getString("id_visiteur").toString();  
		
		   
		   
		   if ((passe1.equals(pass)) && (login1.equals(log)) ){
			  
			   AlertDialog.Builder alerte = new AlertDialog.Builder(connexion.this); 
			   alerte.setTitle("Welcome"); 
			   alerte.setIcon(R.drawable.logol); 
			     alerte.setPositiveButton("Ok", new DialogInterface.OnClickListener() { 
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					 login.setText("");
					 password.setText("");
					 		Intent i = new Intent(connexion.this,menu_visiteur.class);
						    startActivity(i);
				}
			});
			  
			   alerte.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					finish(); 
				}
			});
			   
			   alerte.show(); 
		   }
		   else {
			   
			   Toast.makeText(connexion.this, "Please revisit your coordinates", Toast.LENGTH_LONG).show();
		   }


		}catch(JSONException e){
		
		Toast.makeText(connexion.this, "Please revisit your coordinates", Toast.LENGTH_LONG).show();
		}

	}else{

	

	
	String result =null;
	InputStream is = null;
	StringBuilder sb = new StringBuilder();
	ArrayList<NameValuePair> nameValuePairs=new ArrayList<NameValuePair> ();
	nameValuePairs.add(new BasicNameValuePair("login",log));
	nameValuePairs.add(new BasicNameValuePair("password",pass));
	
	try{
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httppost = new HttpPost(URL);
	httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	HttpResponse response = httpclient.execute(httppost);
	HttpEntity entity = response.getEntity();
	is = entity.getContent();    
	}catch(Exception e){
	Log.e("log_tag", "Error in http connection " + e.toString());
	} 
	try{
	BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

	String line = null;
	while ((line = reader.readLine()) != null) {
		sb.append(line + "\n");
		
	}
	is.close();
	result=sb.toString();

	}catch(Exception e){
	Log.e("log_tag", "Error in http connection " + e.toString());
	}
	
	try {
	
	JSONArray jArray = new JSONArray(result);
	int b=jArray.length();

		  JSONObject json_data = jArray.getJSONObject(0); 
		  String passe1 =	json_data.getString("password").toString(); 
	  String  login1 =json_data.getString("login").toString();  
	  nom_personne = json_data.getString("nom_client").toString()+" "+json_data.getString("prenom_client").toString();   
	  id_c = json_data.getString("id_client").toString();  
	  
	  
	  
	  
	  
	   
	   if ((passe1.equals(pass)) && (login1.equals(log)) ){

		   AlertDialog.Builder alerte = new AlertDialog.Builder(connexion.this); 
		   alerte.setTitle("Welcome"); 
		   alerte.setIcon(R.drawable.logol);
		    alerte.setPositiveButton("Ok", new DialogInterface.OnClickListener() { 
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				 login.setText("");
				 password.setText("");
				 		Intent i = new Intent(connexion.this,menu_client.class);
					    startActivity(i);
			}
		});
		    
		   alerte.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				finish(); 
			}
		});
		   
		   alerte.show(); 
	   }
	   else {
		   
		   Toast.makeText(connexion.this, " Please revisit your coordinates ", Toast.LENGTH_LONG).show();
	   }


	}catch(JSONException e){

	Toast.makeText(connexion.this, " Please revisit your coordinates", Toast.LENGTH_LONG).show();
	}



	}


	}
	});
	}


	}
