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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Boite_reception_visiteur extends Activity {
	
	ListView listV;
	 public static String id_user ;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.boite_reception_visiteur);
		
		listV =(ListView) findViewById(R.id.list_message_visiteur);
	 	
	      String result = null;
	      InputStream is = null;
	      JSONObject json_data=null;
	      ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	      nameValuePairs.add(new BasicNameValuePair("id_user",connexion.id_c)); 
	  
	       ArrayList<String> donnees = new ArrayList<String>();
	      
	      
	      ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
	      HashMap<String, Object> mapPPPP=null;
	      try{
	      //commandes httpClient
	      HttpClient httpclient = new DefaultHttpClient();
	         HttpPost httppost = new HttpPost("http://10.0.2.2/pfe/Android/liste_message_boitereception_visiteur.php");
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
	                    mapPPPP.put("sujet","RE: "+json_data.getString("sujet"));  
	                    mapPPPP.put("message",json_data.getString("reponse"));
	                    mapPPPP.put("date_envoi",json_data.getString("date_rep")); 
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
	             SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItema, R.layout.item_message,
	                    new String[] { "nom", "prenom", "sujet",  "date_envoi"}, new int[] { R.id.text_nomm2,R.id.text_prenomm2, R.id.textView6_sujet,R.id.text_dateenv2});
	             
	             listV.setAdapter(mSchedule);
	             
	             listV.setOnItemClickListener(new OnItemClickListener() {
	     			@Override
	 				@SuppressWarnings("unchecked")
	              	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	     				
	     				final HashMap<String, Object> map = (HashMap<String, Object>) listV.getItemAtPosition(position);
	     				 String item = listV.getItemAtPosition(position).toString(); 
	     				
	   	        
	   	           
	   	     				 id_user = (String) map.get("id") ;   
	   	                     //  startActivity(i);
	   	                       AlertDialog.Builder alerte=new AlertDialog.Builder(Boite_reception_visiteur.this);
	   	 	    				
	   	 	    				alerte.setItems(R.array.choix_listemsg, new DialogInterface.OnClickListener() {
	   	 	    					
	   	 	    					@Override
	   	 	    					public void onClick(DialogInterface dialog, int arg) {
	   	 	    						// TODO Auto-generated method stub
	   	 	    						 switch (arg){
	   	 	    						case 0 : startActivity(new  Intent(getApplicationContext(), repondre_msg.class)) ; break ;
	   	 	    						 	    					
	   	 	    					default : Toast.makeText(Boite_reception_visiteur.this,"Erreur", Toast.LENGTH_LONG).show();
	   	 	    						 }
	   	 	    					}
	   	 	    				});
	   	 	    				 
	   	 	    				   alerte.show();
	   	 	    				
	   	     		 	}
	   	             	
	   	             });
	   	             
	   	            

	   	 }
	   
	   	 	
	             }
	             

