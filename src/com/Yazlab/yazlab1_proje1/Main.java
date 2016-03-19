package com.Yazlab.yazlab1_proje1;






import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;




public class Main extends Activity 
{

	   
    
	
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		Button s1=(Button)findViewById(R.id.button1);
		Button s2=(Button)findViewById(R.id.button2);
		Button s3=(Button)findViewById(R.id.button3);

		final Bundle bundle=new Bundle();
		
		
		final Intent intent=new Intent("com.Yazlab.yazlab1_proje1.OYUN");
		
		
		s1.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
					
				
		        bundle.putInt("seviye", 3);
				intent.putExtras(bundle);
				startActivity(intent);	
				
			}
		});
		
		
		
		
		
		s2.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				
					bundle.putInt("seviye", 4);
					intent.putExtras(bundle);
					startActivity(intent);	;	
		
				
			}
		});
		
		
		
		
		
		s3.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				 	bundle.putInt("seviye", 5);
					intent.putExtras(bundle);
					startActivity(intent);	
				
				
				
			}
		});
		
		
		
		
		
		
		 		 
	}
}




	
