package com.example.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

 

public class Index extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);


		Thread timer = new Thread(){  // creation  d'un compteur
			public void run(){
				try{
					sleep(5000);// durée de compteuur = 5 seconde
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					
					// action  a faire lors de fin de 5 seconde 
				Intent i = new Intent (Index.this,connexion.class);
					startActivity(i); // demmarrer l'action 
				}				
			}			
		};
		timer.start();  // pour lancer le compteuur
	}



	@Override
	protected void onPause() { // méthode prédefinit ( en cas  ou de reception d'appel telephonique 
		// TODO Auto-generated method stub
		super.onPause(); 
		finish();
	}

	}

