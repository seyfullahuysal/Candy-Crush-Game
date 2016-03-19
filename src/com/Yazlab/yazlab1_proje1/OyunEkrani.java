package com.Yazlab.yazlab1_proje1;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

@SuppressLint("ClickableViewAccessibility")


public class OyunEkrani extends View implements OnTouchListener 
{
	
	Toplar ilk=null;
	Toplar son=null;
	Kumeler []kumeler;
	Paint paint = new Paint();
    int seviye;
    boolean ilkmi;
    float aktif_x,aktif_y;;
    boolean patladimi;
    int patlama_sayisi;
    int sure,sure2;
    int[] patlatilanlar;
    boolean kullanicimi;
    boolean bittimi=true;
    
    
    public OyunEkrani(Oyun mainActivity,int seviye) 
    {
    	
    	
    	
        super(mainActivity); 
        kullanicimi=false;
        patladimi=false;
        this.seviye=seviye;
        
        patlama_sayisi=0;
        
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);  
        
        paint.setColor(Color.BLUE);
     
        paint.setAntiAlias(true);
        
        Oku.oku(this,mainActivity,seviye);
        
        ilkmi=true;
        patlatilanlar = new int[seviye];
        
        for(int i=0;i<seviye;i++)
        {
        	patlatilanlar[i]=0;
        }
        
        if(seviye==3)
        {
        	sure2=30;
        }
  
        if(seviye==4)
        {
        	sure2=25;
        }
        if(seviye==5)
        {
        	sure2=15;
        }
        
        
        
       init();
    }
    
    

    Noktalar[][] noktalar;
    int uzunluk;
    
    
    private void init()
    {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run() 
            {
            	
            		
                 postInvalidate();
                 
                 
            }
        }, 0,250L);
        
        
        
        final Timer timer2 = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run() 
            {
            	if(bittimi==false)
            	{
            		
            		sure--;
            		
            	}
            	 
            	 
            	 if(sure==0 && sure2>0)
            	 {
            		 sure=59;
            		 sure2--;
            	 }
           
                 
            }
        }, 0,1000L);
        
        
        
    }
    
   
   

    
    void bolgele(int genislik,int yukseklik)
    {
    	
    	uzunluk=(yukseklik+genislik)/22;
    	int x=uzunluk,y=uzunluk,x2=uzunluk*2,y2=uzunluk*2;	 	
    	noktalar=new Noktalar[12][6];
    	
    	
    	for(int i=0;i<12;i++)
    	{
    		for(int j=0;j<6;j++)
    		{	
    				noktalar[i][j]=new Noktalar();
    		}	
    	}
    	

    	
    	for(int i=0;i<(seviye-1)*3;i++)
    	{
    		
    		for(int j=0;j<6;j++)
    		{
    			noktalar[i][j].top=Rastgele(0);
    			noktalar[i][j].x1=x;
				noktalar[i][j].y1=y;
				noktalar[i][j].merkezx=x+uzunluk/2;
				noktalar[i][j].merkezy=y+uzunluk/2;
				noktalar[i][j].durum=true;
				x+=uzunluk;
				noktalar[i][j].x2=x2;
				noktalar[i][j].y2=y2;
				x2+=uzunluk;	

    		}
	
    			x=uzunluk;
    			y+=uzunluk;
    			x2=uzunluk*2;
    			y2+=uzunluk;
    			
    	}
    	
    	
	
    }
    	
    
    
    
    

	

	public Toplar Rastgele(int haric)
    {
    	int random;
    	Toplar gezici=null;;
    	Random r=new Random();
    	random=r.nextInt(141)+1;
    	boolean don=true;
    		while(don)
    		{
    			random=r.nextInt(141)+1;
    			gezici=ilk;
    			
    			while(true)
        		{
        			if(gezici.sira==random)
        			{
        				break;
        			}
        			gezici=gezici.sonraki;
        		}

    			
    			if(gezici.kume.numara!=haric)
    			{
    				don=false;
    			}
    		}
    		
    	return gezici;
    }
    	
    
	
	
	

	public boolean patlat()
	{
		
	int bassutun = 0,bitsutun = 0,say=1;   
    boolean dondur=false;
    	for(int i=0;i<(seviye-1)*3;i++)
       	{
    		for(int j=0;j<5;j++)
       		{	
       					if(noktalar[i][j].top.kume==noktalar[i][j+1].top.kume)
       					{
       						
       						if(say==1)
       						{
       							say++;
       							bassutun=j;	
       						}
       							
       						else
       						{
       							say++;
       						}
       						bitsutun=j+1;
       						
       						if(say>2 && j==4)
           					{
       							
       							for(int x=bassutun;x<=bitsutun;x++)
       							{
       								noktalar[i][x].durum=false;
       								patladimi=true;
       								if(kullanicimi==true)
       								{
       								patlatilanlar[noktalar[i][j].top.kume.numara-1]+=(bitsutun-bassutun)+1;
       								
       								}
       								
       							}
       		
       						

           					say=1;
           					bassutun=0;
           					bitsutun=0;
           					}
       						
       					}	
       					
       					
       					else 
       					{
       						if(say>2)
       							
       						{		
       						 
       							for(int x=bassutun;x<=bitsutun;x++)
       							{
       								noktalar[i][x].durum=false;
       								patladimi=true;
       								if(kullanicimi==true)
       								{
       								patlatilanlar[noktalar[i][j].top.kume.numara-1]+=(bitsutun-bassutun)+1;
       								}
       								
       							}
       						
       						}

       						say=1;
       						bassutun=0;
       						bitsutun=0;
       					
       					}

       		}	
       		say=1;
       		
       		
       	}
		
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
int bassatir = 0,bitsatir = 0;  
say=1;
    	
    	for(int j=0;j<6;j++)
       	{	
    		for(int i=0;i<(((seviye-1)*3)-1);i++)
       		{	
       			
       					if(noktalar[i][j].top.kume==noktalar[i+1][j].top.kume)
       					{
       						
       						if(say==1)
       						{
       							say++;
       							bassatir=i;
       							
       						}
       							
       						else
       						{
       							say++;
       						}
       						bitsatir=i+1;
       						
       						if(say>2 && i==(((seviye-1)*3)-2))
           					{
       						
       						
       							for(int x=bassatir;x<=bitsatir;x++)
       							{
       								noktalar[x][j].durum=false;
       								patladimi=true;
       								if(kullanicimi==true)
       								{
       								patlatilanlar[noktalar[i][j].top.kume.numara-1]+=(bitsatir-bassatir)+1;
       								}
       								
       							}
       							
   	
           						say=1;
           						bassatir=0;
           						bitsatir=0;
           					}
       						
       					}	
       					
       					
       					else 
       					{
       						if(say>2)
       							
       						{		
       						
       						
       							for(int x=bassatir;x<=bitsatir;x++)
       							{
       								noktalar[x][j].durum=false;
       								patladimi=true;
       								if(kullanicimi==true)
       								{
       								patlatilanlar[noktalar[i][j].top.kume.numara-1]+=(bitsatir-bassatir)+1;
       								}
       							}		
       						}

       						say=1;
       						bassatir=0;
       						bitsatir=0;
       					}
       					
       		}	
       		say=1;

       	}
		
    	
    	
		
		kaydir();
		return dondur;
		
		
	}
	
	
	
		public void kaydir()
		{
		
		
	
	
				
			
				Toplar aratop;
				boolean aradurum;
				
				
				for(int j=0;j<6;j++)
		    	{
		    		
		    		for(int i=((seviye-1)*3)-1;i>0;i--)
		    		{
		    			
		    			
		    			
		    			if(noktalar[i][j].durum==false)
		    			{

		    					for(int b=i;b>0;b--)
		    		    		{
		    						
		    						aratop=noktalar[b][j].top;
		    						noktalar[b][j].top=noktalar[b-1][j].top;
		    						noktalar[b-1][j].top=aratop;
		    						aradurum=noktalar[b][j].durum;
		    						noktalar[b][j].durum=noktalar[b-1][j].durum;
		    						noktalar[b-1][j].durum=aradurum;
		    						
		    		    		}		
		    				
		    			}
		    				
		    		}
			
		    	}
		    	
				
				

					for(int j=0;j<6;j++)
			    	{
					
					
						if(noktalar[0][j].durum==false)
						{
							noktalar[0][j].top=Rastgele(0);
							noktalar[0][j].durum=true;
							kaydir();
						}
						
						
			    	}

	
		
	}

		
		
    public void onDraw(Canvas canvas) 
    {
    	super.onDraw(canvas);
    	canvas.drawColor(Color.BLACK);
    	 
    	
    	if(ilkmi==true)
    	{
    		bolgele(canvas.getWidth(),canvas.getHeight());
    		ilkmi=false;
    		sure=59;
    		
    		
        	
    	}
    	
    	patlat();
    	

    	
    	
    	bittimi=true;
    	for(int i=0;i<seviye;i++)
		{
			
			if((patlatilanlar[i]<((seviye*5))-5))
			{
					bittimi=false;
			}
			
			
		}
    	
		if((sure2<=0 && sure<=0))
		{
			bittimi=true;
		}
    	
    	
		if(bittimi==true)
		{
			if(sure2<=0 && sure<=0)
			{
				Bitmap mFinalbitmap= BitmapFactory.decodeResource(getResources(), R.drawable.arkaplan);
				canvas.drawBitmap(mFinalbitmap, 0, 0, null);
				paint.setColor(Color.RED);
				paint.setTextSize(30);
		    	canvas.drawText("SüRE BÝTTÝ KAZANAMADINIZ", canvas.getWidth()/2-200, canvas.getHeight()/2-100, paint);
			}
			
			else
			{
				
			
			Bitmap mFinalbitmap= BitmapFactory.decodeResource(getResources(), R.drawable.arkaplan);
			canvas.drawBitmap(mFinalbitmap, 0, 0, null);
			int puan=0;
			
			for(int i=0;i<seviye;i++)
			{
			
				puan+=((patlatilanlar[i]*10));
	
			}
				
				puan+=sure2*60+sure;
				
				paint.setTextSize(50);
		    	paint.setColor(Color.RED);
		    	
		    	canvas.drawText("Puanýnýz = "+puan+"", canvas.getWidth()/4, 75, paint);
		    	paint.setColor(Color.BLUE);
		    	for(int i=0;i<seviye;i++)
		    	{
		    			
		    		
		    		canvas.drawText("Küme "+(i+1)+" = "+patlatilanlar[i], canvas.getWidth()/4,(i+1)*200, paint);
		    		
		    		
		    	}
			
			}
			
		}
    	
    	
		else
		{
			
			
			
			canvas.drawColor(Color.CYAN);
			
			for(int i=0;i<(seviye-1)*3;i++)
	    	{
	    		
	    		for(int j=0;j<6;j++)
	    		{
	    				if(noktalar[i][j].durum==true)
	    				{
	    					
	    				paint.setColor(Color.rgb(Oku.SayiCevir(noktalar[i][j].top.renk.substring(0,2)),Oku.SayiCevir(noktalar[i][j].top.renk.substring(3,5)), Oku.SayiCevir(noktalar[i][j].top.renk.substring(6,8))));
	    				canvas.drawCircle(noktalar[i][j].merkezx, noktalar[i][j].merkezy,(uzunluk-20)/2, paint);
	    				paint.setColor(Color.BLACK);paint.setTextSize(25);
	    				if(noktalar[i][j].top.OnlukKarsilik==0)
	    				paint.setColor(Color.WHITE);	
	    				canvas.drawText(noktalar[i][j].top.kume.numara+"", noktalar[i][j].merkezx-5, noktalar[i][j].merkezy+5, paint);
	    				}
	    				
	    			
	    		}
		
		
	    	}
	    	
	    	
	    	
	    	paint.setTextSize(40);
	    	
	    	paint.setColor(Color.RED);
	    	
	    	canvas.drawText(sure2+" : "+sure, canvas.getWidth()/2-100, 50, paint);
	    	paint.setTextSize(30);
	    
	    	for(int i=0;i<seviye;i++)
	    	{
	    			
	    		canvas.drawText("Küme "+(i+1), canvas.getWidth()-150,150*(i+1), paint);
	    		canvas.drawText(""+patlatilanlar[i], canvas.getWidth()-150,150*(i+1)+75, paint);
	    		
	    	}
	    	

			
			
			
			
			
		}
    	
    	
    	
    	
    	
    		
    	
    	
    	
    	
		
    }

    
    public Noktalar YerBul()
    {
    	
    	
    	
    	Noktalar nokta = null;
    	
    	
    	cikis: for(int i=0;i<(seviye-1)*3;i++)
    	{
    		
    		for(int j=0;j<6;j++)
    		{
    			
    				if((aktif_x>=noktalar[i][j].x1 && aktif_x<=noktalar[i][j].x2) &&(aktif_y>=noktalar[i][j].y1 && aktif_y<=noktalar[i][j].y2) )
    				{
    					nokta=noktalar[i][j];
    					break cikis;
    				}
    				
    		}

    	}
    	 	
    	return nokta;
    	
    }

  
    
    Noktalar Nokta1 = null,Nokta2=null;
    
	public boolean onTouch(View view, MotionEvent event ) 
    {

		
		
		
			if(event.getAction()==MotionEvent.ACTION_DOWN)
              {
            	  
            	aktif_x=event.getX();
            	aktif_y=event.getY();
            	
            	 if(YerBul()!=null)
            	 {
            		// YerBul().durum=false;
            		 Nokta1=YerBul();
            	 }
            	 kullanicimi=true;
            	 invalidate(); 
              }
         

             
             else if(event.getAction()==MotionEvent.ACTION_MOVE)
             {
            	 kullanicimi=true;
            	aktif_x=event.getX();
             	aktif_y=event.getY();
             	Nokta2=YerBul();
             	Toplar AraTop=null;
             	
             	if(Nokta1!=Nokta2 && (Nokta2!=null && Nokta1!=null))
             	{
             		if((Nokta1.merkezx==Nokta2.merkezx || Nokta1.merkezy==Nokta2.merkezy) && (Nokta1.durum==true && Nokta2.durum==true))
             		{
             			
             		patladimi=false;		
             		AraTop=Nokta1.top;
             		Nokta1.top=Nokta2.top;
             		Nokta2.top=AraTop;
             		patlat();
             		
             		if(patladimi==false)
             		{

                 		AraTop=Nokta1.top;
                 		Nokta1.top=Nokta2.top;
                 		Nokta2.top=AraTop;
             			
                 		
             		}
             		else
             		{
             			patladimi=false;
             		}
             			
             		Nokta1=null;Nokta2=null;AraTop=null;
             		
             		
             		
             		

             		}
		
             		
             	}
             	

            	 
             }
              
             
             
             else if(event.getAction()==MotionEvent.ACTION_UP)
              {
            	
            	kullanicimi=true;
            	aktif_x=0;
             	aktif_y=0;
             	patlat();
            	invalidate();
            	

              }
             
         
         return true;
        
       
    }
    
}




class Noktalar
{
	int x1,y1,x2,y2,merkezx,merkezy;
	Toplar top;
	boolean durum;
	
}
