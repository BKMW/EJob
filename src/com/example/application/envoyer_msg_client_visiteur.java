package com.example.application;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class envoyer_msg_client_visiteur extends Activity {
	EditText sujet,commentaire;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout. envoyer_msg_client_visiteur);
		
		
		sujet=(EditText) findViewById(R.id.ed_sujet_msg223);
		commentaire=(EditText) findViewById(R.id.ed_message_msg223);
		

		Button envoyer=(Button) findViewById(R.id.btn_envoyemsg223);
		
		
		envoyer.setOnClickListener(new OnClickListener() {
		
		
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String sujet1=sujet.getText().toString();
				String commentaire1=commentaire.getText().toString();
				
				
				
				if(sujet.equals("") ||commentaire.equals(""))
				{

					Toast.makeText( envoyer_msg_client_visiteur.this, " veuillez saisir les champs obligatoires", Toast.LENGTH_SHORT).show();
					return;
				}
				
		
				
			 	
				 
				
				// insertion dans la base de données 
				 
				 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		   	postParameters.add(new BasicNameValuePair("sujet", sujet1)); 
		 	postParameters.add(new BasicNameValuePair("commentaire", commentaire1)); 
		 	postParameters.add(new BasicNameValuePair("id_visiteur", connexion.id_c)); 
		 	postParameters.add(new BasicNameValuePair("id_client", liste_service.id_service)); 
				   
		   	String response = null;

				 try {
		   	    response = CustomHttpClient.executeHttpPost("http://10.0.2.2/pfe/Android/envoyer_msg_visiteur_client.php", postParameters);
		   	    String res=response.toString();
		   	   // res = res.trim();
		   	    res= res.replaceAll("\\s+","");         	              	 
		   	    //error.setText(res);
		   	    if(res.equals("1")){
		   		  
						
						  AlertDialog.Builder alerte = new AlertDialog.Builder ( envoyer_msg_client_visiteur.this);
						  alerte.setTitle("Message contact");
						  alerte.setIcon(R.drawable.ic_launcher);
						  alerte.setMessage("Votre message a été envoyer avec succès");
						  alerte.setPositiveButton("Merci", new DialogInterface.OnClickListener(){
							   
							   @Override
							public void onClick(DialogInterface dialog, int which) {
							    Intent i = new Intent(getApplicationContext(),menu_visiteur.class);
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
	
	
		
	}


