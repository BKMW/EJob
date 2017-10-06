package com.example.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class liste_service extends Activity {
	
	ListView listV;
	 public static String id_service,num_tel,adr ;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liste_service);
		
		listV =(ListView) findViewById(R.id.list_service);
	 	
	      String result = null;
	      InputStream is = null;
	      JSONObject json_data=null;
	      ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	      nameValuePairs.add(new BasicNameValuePair("categorie",recherch.categorie_choisi)); 
	      nameValuePairs.add(new BasicNameValuePair("ville",recherch.ville_choisi));
	      nameValuePairs.add(new BasicNameValuePair("region",recherch.region_choisi)); 
	       ArrayList<String> donnees = new ArrayList<String>();
	      
	      
	      ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
	      HashMap<String, Object> mapPPPP=null;
	      try{
	      //commandes httpClient
	      HttpClient httpclient = new DefaultHttpClient();
	         HttpPost httppost = new HttpPost("http://10.0.2.2/pfe/Android/liste_service.php");
	         httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	         HttpResponse response = httpclient.execute(httppost);
	         HttpEntity entity = response.getEntity();
	         is = entity.getContent();
	      }
	      catch(Exception e){
	       Log.i("taghttppost",""+e.toString());
	             Toast.makeText(getBaseContext(),e.toString() ,Toast.LENGTH_LONG).show();
	        }
	    
	       
	      //conversion de la réponse en chaine de caractère
	         try
	         {
	          BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	         
	          StringBuilder sb  = new StringBuilder();
	         
	          String line = null;
	         
	          while ((line = reader.readLine()) != null)
	          {
	          sb.append(line + "\n");
	          }
	         
	          is.close();
	         
	          result = sb.toString();
	          Log.i("result",result);
	         }
	         catch(Exception e)
	         {
	          Log.i("tagconvertstr",""+e.toString());
	         }
	         //recuperation des donnees json
	         try{
	           JSONArray jArray = new JSONArray(result);
	            
	              for(int i=0;i<jArray.length();i++)
	              {
	             	 
	               json_data = jArray.getJSONObject(i); 
	                    
	 				 mapPPPP = new HashMap<String, Object>();
	 				
	                   
	                    mapPPPP.put("nom",json_data.getString("nom_client"));   
	                    mapPPPP.put("prenom",json_data.getString("prenom_client"));   
	                    mapPPPP.put("tel",json_data.getString("tel_client"));  
	                    mapPPPP.put("email",json_data.getString("email_client"));
	                    mapPPPP.put("adresse",json_data.getString("adresse_client")); 
	                    mapPPPP.put("id",json_data.getString("id_client"));   
	                    listItem.add(mapPPPP);
	                     
	 				
	                }
	              
	             }
	             catch(JSONException e){
	              Log.i("tagjsonexp",""+e.toString());
	             } catch (ParseException e) {
	              Log.i("tagjsonpars",""+e.toString());
	        }
	             ArrayList<HashMap<String, Object>> listItema = listItem;
	             SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItema, R.layout.item_service,
	                    new String[] { "nom", "prenom", "tel", "email"}, new int[] { R.id.text_nom,R.id.text_prenom,R.id.text_tel, R.id.text_email});
	             
	             listV.setAdapter(mSchedule);
	             
	             listV.setOnItemClickListener(new OnItemClickListener() {
	     			@Override
	 				@SuppressWarnings("unchecked")
	              	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	     				
	     				final HashMap<String, Object> map = (HashMap<String, Object>) listV.getItemAtPosition(position);
	     				 String item = listV.getItemAtPosition(position).toString(); 
	     				
	   	        
	   	           
	   	     				 id_service = (String) map.get("id") ;  
	   	     				num_tel = (String) map.get("tel") ; 
	   	     			adr = (String) map.get("adresse") ; 
	   	                     //  startActivity(i);
	   	                       AlertDialog.Builder alerte=new AlertDialog.Builder(liste_service.this);
	   	 	    				
	   	 	    				alerte.setItems(R.array.choix_liste, new DialogInterface.OnClickListener() {
	   	 	    					
	   	 	    					@Override
	   	 	    					public void onClick(DialogInterface dialog, int arg) {
	   	 	    						// TODO Auto-generated method stub
	   	 	    						 switch (arg){
	   	 	    						case 0 : startActivity(new  Intent(getApplicationContext(), Demander.class)) ; break ;
	   	 	    						case 1 : startActivity(new  Intent(getApplicationContext(), envoyer_msg_client_visiteur.class)); break ;
	   	 	    						case 2 : startActivity(new  Intent(getApplicationContext(), envoyer_reclamation.class)); break ;
	   	 	    					    case 3 :  appeler(); break ; 
	   	 	    					
	   	 	    					 default : Toast.makeText(liste_service.this,"Erreur", Toast.LENGTH_LONG).show();
	   	 	    						 }
	   	 	    					}
	   	 	    				});
	   	 	    				 
	   	 	    				   alerte.show();
	   	 	    				
	   	     		 	}
	   	             	
	   	             });
	   	             
	   	            

	   	 }
	   
	
	private void appeler()
	 {

	
		
			 
			 Intent i = new Intent("android.intent.action.CALL", Uri.parse("tel: "+Uri.encode(num_tel)));
		
         startActivity(i);
				 
				
		
		 
	 }
	private void maps()
	 {

	
		
		Intent i = new Intent (Intent.ACTION_VIEW,Uri.parse(adr));
		startActivity(i);
				 
				
		
		 
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
	             

