package com.example.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class msg extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg);
		
		
		
		
		Button boite=(Button) findViewById(R.id.btn_boite);
			
		Button envoi=(Button) findViewById(R.id.btn_msgadminc);
		
		 boite.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(msg.this,boite_reception.class);
					startActivity(i);
					
					
				}
			});
		 envoi.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//lien vers page inscription
					Intent i = new Intent(msg.this,envoyer_msg_client_ad.class);
					startActivity(i);
					
				}
			});
		
	}

}
