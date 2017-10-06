package com.example.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class menu_visiteur extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_visiteur);
		
		 ImageButton recherche=(ImageButton) findViewById(R.id.ed_reherchebtn);
		
		 ImageButton messagerie=(ImageButton) findViewById(R.id.ed_messagerie);
		 ImageButton profil=(ImageButton) findViewById(R.id.btn_profil);
		 
	
		 profil.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(menu_visiteur.this,profil_visiteur.class);
					startActivity(i);
					
				}
			});
		 
		 recherche.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(menu_visiteur.this,recherch.class);
					startActivity(i);
					
				}
			});
	
		 
		 messagerie.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(menu_visiteur.this,Boite_reception_visiteur.class);
					startActivity(i);
					
				}
			});
	}

}
