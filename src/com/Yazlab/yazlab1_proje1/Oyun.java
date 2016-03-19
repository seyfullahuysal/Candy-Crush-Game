package com.Yazlab.yazlab1_proje1;

import android.app.Activity;

import android.os.Bundle;


public class Oyun extends Activity 
{
	
	
	OyunEkrani Ekran;
	int seviye;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oyun);
		
		Bundle veri=getIntent().getExtras();
		seviye=veri.getInt("seviye");
	
		
		Ekran = new OyunEkrani(this,seviye);
        setContentView(Ekran);
        Ekran.requestFocus();
		
		


		
	}
	
	
	
	
	
}
