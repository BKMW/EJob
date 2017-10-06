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

public class inscription_visiteur extends Activity {
	EditText nom,prenom,tel,email,adresse, login,password,confirme;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inscription_visiteur);
		
		nom=(EditText) findViewById(R.id.id_nom22);
		prenom=(EditText) findViewById(R.id.id_prenom22);
		tel=(EditText) findViewById(R.id.id_tel22);
		email=(EditText) findViewById(R.id.id_email22);
		adresse=(EditText) findViewById(R.id.id_adresse22);
		login=(EditText) findViewById(R.id.id_login22);
		password=(EditText) findViewById(R.id.id_password22);
		confirme=(EditText) findViewById(R.id.id_confirme22);

		Button inscrire=(Button) findViewById(R.id.btn_inscrire22);
		
		inscrire.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String nom2=nom.getText().toString();
				String prenom2=prenom.getText().toString();
				String tel2=tel.getText().toString();
				String email2=email.getText().toString();
				String adresse2=adresse.getText().toString();
				String login2=login.getText().toString();
				String password2=password.getText().toString();
				String confirme2=confirme.getText().toString();
				
				
				
				
				if(nom2.equals("") || prenom2.equals("") || tel2.equals("")|| email2.equals("")|| adresse2.equals("")|| login2.equals("")|| password2.equals("")|| confirme2.equals(""))
				{

					Toast.makeText(inscription_visiteur.this, " veuillez saisir les champs vides", Toast.LENGTH_SHORT).show();
					return;
				}
				
				
				 // verification sur email 
				// verification si le contenue de champs email confome au format de l'email 
				// On déclare le pattern que l’on doit vérifier
				Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
				// On déclare un matcher, qui comparera le pattern avec la
				// string passée en argument
				Matcher m = p.matcher(email2);
				// Si l’adresse mail saisie ne correspond au format d’une
				// adresse mail on un affiche un message à l'utilisateur
				if (!m.matches()) { 
					Toast.makeText(inscription_visiteur.this,"Désole votre Email nom valide",
				        Toast.LENGTH_SHORT).show();
						return;
				}
				
				
			 	if( !(password2.equals(confirme2)))
				{

					confirme.setError(" Votre mot de passe est incorrect revérifier le");
					return;
				}
				 
				
				// insertion dans la base de données 
				 
				 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		   	postParameters.add(new BasicNameValuePair("nom", nom2)); 
		 	postParameters.add(new BasicNameValuePair("prenom", prenom2)); 
		 	postParameters.add(new BasicNameValuePair("adresse", adresse2));
		   	postParameters.add(new BasicNameValuePair("tel", tel2 ));
		   	postParameters.add(new BasicNameValuePair("email", email2 )); 
		   	postParameters.add(new BasicNameValuePair("login", login2));
		   	postParameters.add(new BasicNameValuePair("password", password2));
		   	
		   	String response = null;

				 try {
		   	    response = CustomHttpClient.executeHttpPost("http://10.0.2.2/pfe/Android/inscription_visiteur.php", postParameters);
		   	    String res=response.toString();
		   	   // res = res.trim();
		   	    res= res.replaceAll("\\s+","");         	              	 
		   	    //error.setText(res);
		   	    if(res.equals("1")){
		   		  
						
						  AlertDialog.Builder alerte = new AlertDialog.Builder(inscription_visiteur.this);
						  alerte.setTitle(" Votre Inscription a été accepté");
						  alerte.setIcon(R.drawable.ic_launcher);
						  alerte.setMessage("Bienvenue Dans Notre Application.");
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
	
	
		
	}


