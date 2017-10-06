package com.example.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

 
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Demander extends Activity {
	EditText message;
	private int mHour_dep, mMinute_dep ;  
	Button date , heure ; 
	String date_choisit, heure_choisit ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demander_rendez_vous);
		
		
		message=(EditText) findViewById(R.id.id_msg);
			

		 date=(Button) findViewById(R.id.but_date);
		 heure=(Button) findViewById(R.id.but_heure);
		Button envoyer=(Button) findViewById(R.id.button_envoyer);
		
		
		
		// bouton date 
		 final OnDateSetListener odsl=new OnDateSetListener()

	     {

	public void onDateSet(DatePicker arg0, int year, int month, int dayOfMonth) {
		String yearstring= String.valueOf( year ); // recuperer l'année choisie par l'utilisateur 
		String monthstring= String.valueOf( month+1 );
		String daystring= String.valueOf( dayOfMonth );
		String datest= yearstring+"-"+monthstring+"-"+daystring;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // choisir le format de date 
		 Date d= null; // declation de date null 
		try {
			d = sdf.parse(datest); // pour convertir le date choisir en  nouveau format (yyyy-mm-dd)
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	  Log.i("Infos date :", sdf.format(d)); // affichage en LogCat ( juste pour tester )
	  date.setText(sdf.format(d));  // Afficher  la date choisit par le client dans le bouton 
	  date_choisit = sdf.format(d).toString();
	}
	  };
	    date.setOnClickListener(new OnClickListener() // action lors de lique sur le bouton Date depart 

	     {
	public void onClick(View arg0) {
	  // declomment afficaration de datePicker ( comment afficher une agenda pour choisir la date )
	Calendar cal=Calendar.getInstance(); // declaration d'un calandrier
	DatePickerDialog datePickDiag=new DatePickerDialog(Demander.this,odsl,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
	 datePickDiag.show();
	}
	     });
		
		// bouton heure 
	    heure.setOnClickListener(new View.OnClickListener(){

	        @Override
	        public void onClick(View v) {
	            //showDialog(DATE_DIALOG_ID);
	             
	            TimePickerDialog dialog=new TimePickerDialog(Demander.this,mTimeSetListener,mHour_dep, mMinute_dep, false);
	            dialog.show();
	            
	            
	            }});

	    // get the current time
	    final Calendar c = Calendar.getInstance();
	    mHour_dep = c.get(Calendar.HOUR_OF_DAY);
	    mMinute_dep = c.get(Calendar.MINUTE);

	    // display the current date
	    updateDisplay1();
		
		
		/// action de bouton reserver= valider
	    
	    envoyer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			
				String message1=message.getText().toString();	
				
				if(message1.equals("") || date_choisit.equals("date") || heure_choisit.equals("heure"))
				{

					Toast.makeText(Demander.this, " veuillez saisir les champs obligatoires", Toast.LENGTH_SHORT).show();
					return;
				}
				
			 
				
				// insertion dans la base de données 
				 
				 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		   	postParameters.add(new BasicNameValuePair("date", date.getText().toString())); 
		   	postParameters.add(new BasicNameValuePair("heure",  heure_choisit));
		   	postParameters.add(new BasicNameValuePair("message",  message1));
		 	postParameters.add(new BasicNameValuePair("id_user", connexion.id_c));
		 	postParameters.add(new BasicNameValuePair("id_service", liste_service.id_service));
		 	

		   	String response = null;

				 try {
		   	    response = CustomHttpClient.executeHttpPost("http://10.0.2.2/pfe/Android/demande_rdv.php", postParameters);
		   	    String res=response.toString();
		   	   // res = res.trim();
		   	    res= res.replaceAll("\\s+","");         	              	 
		   	    //error.setText(res);
		   	    if(res.equals("1")){
		   		  
						
						  AlertDialog.Builder alerte = new AlertDialog.Builder(Demander.this);

						  alerte.setMessage("Votre demande de rendez vous a été envoyer avec succès");
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
	
	private void updateDisplay1() {
		 heure_choisit= new StringBuilder().append(pad(mHour_dep)).append(":").append(pad(mMinute_dep))+":00" ;
		
	  	}


	  private static String pad(int c) {
	    if (c >= 10)
	        return String.valueOf(c);
	    else
	        return "0" + String.valueOf(c);
	  }




	  //the callback received when the user "sets" the time in the dialog/*
  private TimePickerDialog.OnTimeSetListener mTimeSetListener =
	   new TimePickerDialog.OnTimeSetListener() {
	       public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
	           mHour_dep = hourOfDay;
	           mMinute_dep = minute; 
	           heure.setText(new StringBuilder().append(pad(mHour_dep)).append(":").append(pad(mMinute_dep))+":00"); 
	           updateDisplay1(); 
	           
	       }

	  }; 
		
	}


