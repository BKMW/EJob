package com.example.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.ImageButton;

public class menu_client extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_client);
		
		 ImageButton rdv=(ImageButton) findViewById(R.id.id_rdv);
		 ImageButton profil=(ImageButton) findViewById(R.id.id_profil1);
		 ImageButton msg=(ImageButton) findViewById(R.id.id_msg);
		 ImageButton rcl=(ImageButton) findViewById(R.id.id_rcl);
		 
		 rdv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(menu_client.this,rendez_vous.class);
					startActivity(i);
					
				}
			});
		 profil.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(menu_client.this,profil.class);
					startActivity(i);
					
				}
			});
		 msg.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(menu_client.this,boite_reception.class);
					startActivity(i);
					
				}
			});
		 rcl.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(menu_client.this,reclamation.class);
					startActivity(i);
					
				}
			});
	}

}
