package com.example.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class menu_inscription extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_inscription);
		
		Button client=(Button) findViewById(R.id.btn_client);
		Button visiteur=(Button) findViewById(R.id.btn_visiteur);
		
client.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//lien vers page inscription
				Intent i = new Intent(menu_inscription.this,inscription_client.class);
				startActivity(i);
				
			}	});
visiteur.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//lien vers page inscription
		Intent i = new Intent(menu_inscription.this,inscription_visiteur.class);
		startActivity(i);
		
	}	});
			
					
				
			
		
		
	}
	


}

