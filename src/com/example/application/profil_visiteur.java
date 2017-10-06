package com.example.application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class profil_visiteur extends Activity {
	EditText nom,prenom,tel,adresse,email,login,password,confirme;
	


	
	
	String URL = "http://10.0.2.2/pfe/Android/profil_visiteur.php" ;
	String url_update= "http://10.0.2.2/pfe/Android/modif_profilvisiteur.php";

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil_visiteur);
		
		  
		    nom=(EditText) findViewById(R.id.id_nom2);
		    prenom=(EditText) findViewById(R.id.id_prenom2); 
			tel=(EditText) findViewById(R.id.id_tel2);
			adresse=(EditText) findViewById(R.id.id_adresse2);
			email=(EditText) findViewById(R.id.id_email2);
			login=(EditText) findViewById(R.id.id_login2);
			password=(EditText) findViewById(R.id.id_password2);
		 confirme=(EditText) findViewById(R.id.id_confirme2);
		 
		 Button valider=(Button) findViewById(R.id.but_modif2);
	

		
		
		
		 
		 //////

	   		
		
		
		
		
		
		
	  	   		 String result =null;
			   		InputStream is = null;
			   		StringBuilder sb = new StringBuilder();
			   		ArrayList<NameValuePair> nameValuePairs=new ArrayList<NameValuePair> ();
			   		//envoyer  les deux parametres au code phop pour l'utiliser dans la requete sql exactement dans la clause WHERE
			   		nameValuePairs.add(new BasicNameValuePair("id_user",connexion.id_c));
			   		
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
			nom.setText(json_data.getString("nom_visiteur").toString()); // "password" nom de champs dans la base de donbées
			prenom.setText(json_data.getString("prenom_visiteur").toString());
			tel.setText(json_data.getString("tel_visiteur").toString());
			email.setText(json_data.getString("email_visiteur").toString());
			adresse.setText(json_data.getString("adresse_visiteur").toString());
			login.setText(json_data.getString("login").toString());
			password.setText(json_data.getString("password").toString());
	
			 
			
			   		  
			   		  
			   		
			   		   
			   		   
			   		  

			   		}catch(JSONException e){
			   		//  Log.e("log_tag", "Error parsing data " + e.toString());
			   		Toast.makeText(profil_visiteur.this, "S'il vous plez reveerifier vos cordonnées", Toast.LENGTH_LONG).show();
			   		}
			   		//
			   		    
			   		 ///// action de bouton modifier 
			   		valider.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// recuperation de contenu ddes champs 

							String nom1=nom.getText().toString();
							String prenom1=prenom.getText().toString();
							String tel1=tel.getText().toString();
							String email1=email.getText().toString();
							String adresse1=adresse.getText().toString();
							String login1=login.getText().toString();
							String password1=password.getText().toString();
							String confirme1=confirme.getText().toString();
							
							
							
							
							if(nom1.equals("") || prenom1.equals("") || tel1.equals("")|| email1.equals("")|| adresse1.equals("")|| login1.equals("")|| password1.equals("")|| confirme1.equals(""))
							{

								Toast.makeText(profil_visiteur.this, " veuillez saisir le champs obligatoires", Toast.LENGTH_SHORT).show();
								return;
							}
							
							
							 // verification sur email 
							// verification si le contenue de champs email confome au format de l'email 
							// On déclare le pattern que l’on doit vérifier
							Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
							// On déclare un matcher, qui comparera le pattern avec la
							// string passée en argument
							Matcher m = p.matcher(email1);
							// Si l’adresse mail saisie ne correspond au format d’une
							// adresse mail on un affiche un message à l'utilisateur
							if (!m.matches()) { 
								Toast.makeText(profil_visiteur.this,"Désole votre Email nom valide",
							        Toast.LENGTH_SHORT).show();
									return;
							}
							
							
						 	if( !(password1.equals(confirme1)))
							{

								confirme.setError(" S'il vous plez reverifier vos cordonnées");
								return;
							}
							 
						 	
						 	// communication  avec la base  et modification 7

							InputStream is = null;
							String result = null;
							// envoyer les edittext a modifier au script php
							ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
							postParameters.add(new BasicNameValuePair("id_user", connexion.id_c)); 
							postParameters.add(new BasicNameValuePair("nom", nom1)); 
							postParameters.add(new BasicNameValuePair("prenom", prenom1)); 
						   	postParameters.add(new BasicNameValuePair("tel", tel1 ));
						   	postParameters.add(new BasicNameValuePair("email", email1 ));
						 
						   	postParameters.add(new BasicNameValuePair("adresse", adresse1));
						   
						   
						   	postParameters.add(new BasicNameValuePair("login", login1));
						   	postParameters.add(new BasicNameValuePair("password", password1));
							try{
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost(url_update);
								httppost.setEntity(new UrlEncodedFormEntity(postParameters));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();

							}catch(Exception e){
								Log.e("log_tag", "Error in http connection " + e.toString());
							}
							// Convertion de la requÃªte en string
							try{
								BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
								StringBuilder sb = new StringBuilder();
								String line = null;
								while ((line = reader.readLine()) != null) {
									sb.append(line + "\n");
								}
								is.close();
								result=sb.toString();
							}catch(Exception e){
								Log.e("log_tag", "Error converting result " + e.toString());
							}
							
							if(result.contains("Ok")){
								// si la modification a etes effectué on va afficher un message de succces
								 Toast.makeText(profil_visiteur.this.getApplicationContext(),"Votre Profil a été Modifier", Toast.LENGTH_LONG).show();
						}
							else 
							{Toast t ;
							t = Toast.makeText(profil_visiteur.this.getApplicationContext(), "Erreur Connection", Toast.LENGTH_LONG);
							t.show();
							 
							}  
						 	
						 	
						 	/////////////////////////////////
							
						}
					});
			   		    
			   		    
	}
		
		
		
	// MENU 
	 public boolean onCreateOptionsMenu(Menu menu){
 	   // Page Accueil 
 	  MenuItem item   = menu.add("Menu Visiteur");
 	  item .setIcon(R.drawable.imagesmenu);
 	  item .setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem arg0) {
				Intent i = new Intent(getApplicationContext(), menu_visiteur.class);
				startActivity(i);
				return false;
			}
		});
 	  // Page Devises
 	 MenuItem item1  = menu.add("Deconnexion");
 	  item1.setIcon(R.drawable.deconnex); 
 	  item1.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
 		public boolean onMenuItemClick(MenuItem arg0) {
			Intent i = new Intent(getApplicationContext(), connexion.class);
			startActivity(i);
			return false;
		}
	});

 
 	  return  super.onCreateOptionsMenu(menu);
}	
		
	
}

