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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class recherch extends Activity {
	Spinner  ville,categorie,region;
	
	String URL_Categorie = "http://10.0.2.2/pfe/Android/liste_categorie.php" ;
	String URL_Region = "http://10.0.2.2/pfe/Android/liste_region.php" ;
	String URL_ville = "http://10.0.2.2/pfe/Android/liste_ville.php" ;
	public static String  categorie_choisi,ville_choisi,region_choisi;
	
	JSONArray jArray ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.recherche);
		
		ville=(Spinner) findViewById(R.id.id_villerch);
		categorie=(Spinner) findViewById(R.id.id_categorierch);
		region=(Spinner) findViewById(R.id.id_regionrch);
		
		Button rechercher=(Button) findViewById(R.id.btn_rechercherch);
		
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
			   		    
			   		    
			   		    
	
		
		
		
			   		 rechercher.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								
							categorie_choisi=categorie.getSelectedItem().toString();
							ville_choisi=ville.getSelectedItem().toString();
							region_choisi=region.getSelectedItem().toString();
							
								Intent i = new Intent(recherch.this,liste_service.class);
								startActivity(i);
								
							}
						});
						
						
						
		
	}
	

	
	private String getCategorie(String returnString) {
		InputStream is = null;
		String result = "";
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


		
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


