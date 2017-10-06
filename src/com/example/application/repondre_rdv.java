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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class repondre_rdv extends Activity  {
	
	String URL = "http://10.0.2.2/pfe/Android/repondre_rdv.php" ;
	EditText destinataire,reponse,sujet;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reponder_redez_vous);
		
		destinataire=(EditText) findViewById(R.id.ed_dest_rep);
		reponse=(EditText) findViewById(R.id.editText1_reponsee);
		sujet=(EditText) findViewById(R.id.editText1_sujett);
		Button envoyer=(Button) findViewById(R.id.button1_repondree);
		
		 String result =null;
	   		InputStream is = null;
	   		StringBuilder sb = new StringBuilder();
	   		ArrayList<NameValuePair> nameValuePairs=new ArrayList<NameValuePair> ();
	   		//envoyer  les deux parametres au code phop pour l'utiliser dans la requete sql exactement dans la clause WHERE
	   		nameValuePairs.add(new BasicNameValuePair("id_visiteur",rendez_vous.id_visiteur));

	   		
	   		//Execute HTTP Post Request
	   		try{
	   		// execution de  script pho qui se trouve dans l'URL  avec le protocole http et en utilisant la methode POST
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
	   		String essai=result.substring(0, 4) ;
	   		try {
	   		if (result.matches("<br >")){
	   			 essai=result.substring(0, 2) ; 
	   		}
	   		JSONArray jArray = new JSONArray(result);
	   		int b=jArray.length();

	   			  JSONObject json_data = jArray.getJSONObject(0); 
	   			//  json_data.getString("nom de champs de  base de données que voulez vous le récuperer");
	   			destinataire.setText(json_data.getString("email_visiteur").toString()); // "password" nom de champs dans la base de donbées
	   			sujet.setText(("Reponse de rendez vous pris par tunisiens professionnels"));

	


	 
	
	   		  
	   		  
	   		
	   		   
	   		   
	   		  

	   		}catch(JSONException e){
	   		//  Log.e("log_tag", "Error parsing data " + e.toString());
	   		Toast.makeText(repondre_rdv.this, "Verifier vos cordonnées", Toast.LENGTH_LONG).show();
	   		}
	   		//
	   		
	   		
	   		envoyer.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					final Intent emailIntent = new Intent(
							android.content.Intent.ACTION_SEND);
					emailIntent.setType("plain/text");
					emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
							new String[] { destinataire.getText().toString() });
					emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
							sujet.getText());
					emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
							reponse.getText());
					repondre_rdv.this.startActivity(Intent.createChooser(emailIntent,
							"Send mail..."));

				}
			});
	}}