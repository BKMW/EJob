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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class inscription_client extends Activity {
	
	String URL_Categorie = "http://10.0.2.2/pfe/Android/liste_categorie.php" ;
	String URL_Region = "http://10.0.2.2/pfe/Android/liste_region.php" ;
	String URL_ville = "http://10.0.2.2/pfe/Android/liste_ville.php" ;
	EditText nom,prenom,tel,email,adresse, login,password,confirme;
	Spinner  region,categorie,ville ;
	JSONArray  jArray ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inscription_client);
		
	
            nom=(EditText) findViewById(R.id.id_nom);
			prenom=(EditText) findViewById(R.id.id_prenom);
			tel=(EditText) findViewById(R.id.id_tel);
			email=(EditText) findViewById(R.id.id_email);
			adresse=(EditText) findViewById(R.id.id_adresse);
			login=(EditText) findViewById(R.id.id_login);
			password=(EditText) findViewById(R.id.id_password);
			confirme=(EditText) findViewById(R.id.id_confirme);
			region=(Spinner) findViewById(R.id.id_region);
			ville=(Spinner) findViewById(R.id.id_ville);
			categorie=(Spinner) findViewById(R.id.id_categorie);
			
			Button inscrire=(Button) findViewById(R.id.but_inscrir);

			
				
				//charger spinner categorie depuis la base de données 
				  ArrayAdapter <CharSequence> adapter1 =
			    		  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
			    		 adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // ajouter un bouton radio devant chacun des ces item 
			    		 
			    		 
			    		  getCategorie(URL_Categorie);  // eppler a la methode getSpecialite qui va recuperer tous les spécialité dans la base de données
			   		   try { 
			   		     	  for(int i=0;i<jArray.length();i++){
			   		  	         	JSONObject json_data = jArray.getJSONObject(i);
			   		  				 adapter1.add(json_data.getString("libelle_categorie").toString());
			   						}
			   		   }
			   		   catch (JSONException e) {
			   							// TODO Auto-generated catch block
			   							e.printStackTrace();
			   				}
			   		  			    
			   		  
			   		    categorie.setAdapter(adapter1);
				 
				 //////
			   		    
			   			//charger spinner categorie depuis la base de données 
			   		 ArrayAdapter <CharSequence> adapter2 =
				    		  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
				    		 adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
				    		 
				    		 
				    		  getville(URL_ville); 
				   		   try { 
				   		     	  for(int i=0;i<jArray.length();i++){
				   		  	         	JSONObject json_data = jArray.getJSONObject(i);
				   		  				 adapter2.add(json_data.getString("libelle_ville").toString());
				   						}
				   		   }
				   		   catch (JSONException e) {
				   							// TODO Auto-generated catch block
				   							e.printStackTrace();
				   				}
				   		  			    
				   		  
				   		    ville.setAdapter(adapter2);
					 
					 //////
				   		//charger spinner categorie depuis la base de données 
							  ArrayAdapter <CharSequence> adapter3 =
						    		  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
						    		 adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
						    		 
						    		 
						    		  getRegion(URL_Region); 
						   		   try { 
						   		     	  for(int i=0;i<jArray.length();i++){
						   		  	         	JSONObject json_data = jArray.getJSONObject(i);
						   		  				 adapter3.add(json_data.getString("libelle_region").toString());
						   						}
						   		   }
						   		   catch (JSONException e) {
						   							// TODO Auto-generated catch block
						   							e.printStackTrace();
						   				}
						   		  			    
						   		  
						   		    region.setAdapter(adapter3);
							 
							 //////
				   		    
				   		    
				
			
				
				
				inscrire.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
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

							Toast.makeText(inscription_client.this, " veuillez saisir les champs obligatoires", Toast.LENGTH_SHORT).show();
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
							Toast.makeText(inscription_client.this,"Désolé votre Email nom valide",
						        Toast.LENGTH_SHORT).show();
								return;
						}
						
						
					 	if( !(password1.equals(confirme1)))
						{

							confirme.setError(" Votre mot de passe est incorrect revérifier le");
							return;
						}
						 
						
						// insertion dans la base de données 
						 
						 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
				   	postParameters.add(new BasicNameValuePair("nom", nom1)); 
				 	postParameters.add(new BasicNameValuePair("prenom", prenom1)); 
				   	postParameters.add(new BasicNameValuePair("tel", tel1 ));
				   	postParameters.add(new BasicNameValuePair("email", email1 ));
				    
				   	postParameters.add(new BasicNameValuePair("adresse", adresse1));
				   	postParameters.add(new BasicNameValuePair("region", region.getSelectedItem().toString()));
				 	postParameters.add(new BasicNameValuePair("ville", ville.getSelectedItem().toString()));
				   	postParameters.add(new BasicNameValuePair("categorie", categorie.getSelectedItem().toString()));
				   	postParameters.add(new BasicNameValuePair("login", login1));
				   	postParameters.add(new BasicNameValuePair("password", password1));
				   	
				   	String response = null;

						 try {
				   	    response = CustomHttpClient.executeHttpPost("http://10.0.2.2/pfe/Android/inscription_client.php", postParameters);
				   	    String res=response.toString();
				   	   // res = res.trim();
				   	    res= res.replaceAll("\\s+","");         	              	 
				   	    //error.setText(res);
				   	    if(res.equals("1")){
				   		  
								
								  AlertDialog.Builder alerte = new AlertDialog.Builder(inscription_client.this);
								  alerte.setTitle("Votre Inscription a été accepté");
								  alerte.setIcon(R.drawable.ic_launcher);
								
								  alerte.setPositiveButton("Merci", new DialogInterface.OnClickListener(){
									   
									   @Override
									public void onClick(DialogInterface dialog, int which) {
									    Intent i = new Intent(getApplicationContext(),connexion.class);
									    startActivity(i);
								   }});
								    
								   alerte.show();
								   return;
				   	    }
				   	} catch (Exception e) {
				   		 
				   	}
						
					}
				});
				
				
				
				
				
				
				
				
				
				
			}
			
			
			private String getCategorie(String returnString) {
				InputStream is = null;
				String result = "";
				
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				//nameValuePairs.add(new BasicNameValuePair("ville",""));

				
				try{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(URL_Categorie);
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();

				}catch(Exception e){
					Log.e("log_tag", "Error in http connection " + e.toString());
				}

				// Convertion de la requête en string
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
				// Parse les données JSON
				try{
					 jArray = new JSONArray(result);
				}
			    catch(JSONException e){
			    		Log.e("log_tag", "Error parsing data " + e.toString());
			    }
		    
				return returnString; 
			}
			
			

			private String getRegion(String returnString) {
				InputStream is = null;
				String result = "";
				
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			

				
				try{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(URL_Region);
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();

				}catch(Exception e){
					Log.e("log_tag", "Error in http connection " + e.toString());
				}

				// Convertion de la requête en string
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
				// Parse les données JSON
				try{
					 jArray = new JSONArray(result);
				}
			    catch(JSONException e){
			    		Log.e("log_tag", "Error parsing data " + e.toString());
			    }
		    
				return returnString; 
			}

			//
			private String getville(String returnString) {
				InputStream is = null;
				String result = "";
				
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			

				
				try{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(URL_ville);
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();

				}catch(Exception e){
					Log.e("log_tag", "Error in http connection " + e.toString());
				}

				// Convertion de la requête en string
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
				// Parse les données JSON
				try{
					 jArray = new JSONArray(result);
				}
			    catch(JSONException e){
			    		Log.e("log_tag", "Error parsing data " + e.toString());
			    }
		    
				return returnString; 
			}

			//

		}
