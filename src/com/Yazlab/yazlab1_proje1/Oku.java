package com.Yazlab.yazlab1_proje1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.Random;




public class Oku
{
    
	
	
    static Toplar ilk=null;
	static Toplar son=null;
	static Kumeler []kumeler;
	static Oyun Anasinif;
    
	public static void oku(OyunEkrani oyunekrani,Oyun anasinif,int seviye)
    {
		Anasinif=anasinif;
		String data = "";
		InputStream is = Anasinif.getResources().openRawResource(R.drawable.renkhex);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		if (is != null) 
		{
			try {
				
					while ((data = reader.readLine()) != null) 
					{
					
						ListeEkle(data);
						
					}
				
					is.close();
				} 
			
			
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		

       Kumele(seviye);
       DosyayaYaz();
       
       oyunekrani.ilk=ilk;
       oyunekrani.son=son;
       oyunekrani.kumeler=kumeler;
       
       ilk=null;
       son=null;
    }
	
	
	
	/////////////////////////////////////////////////
	public static int SayiCevir(String sayi)
	{
		
		int sonuc=0,us=0;
        String onaltilik="0123456789ABCDEF";  
        for(int i = sayi.length()-1 ; i >= 0 ; i--)
        {
        	
            sonuc+=onaltilik.indexOf(sayi.charAt(i))*Math.pow(16, us);
            us++;
         
        }       
        return sonuc;	
	}
////////////////////////////////////////////////////////////////////	
	
	public static void ListeEkle(String renk)
	{
		
		Toplar eklenecek=new Toplar();
		eklenecek.renk=renk;
		eklenecek.OnlukKarsilik=SayiCevir(renk.replace(" ", ""));	
		if(ilk==null)
		{
			
			ilk=eklenecek;
			son=eklenecek;
			ilk.onceki=null;
			ilk.sonraki=null;
			
		}
		else
		{		
			son.sonraki=eklenecek;
			eklenecek.onceki=son;
			son=eklenecek;
			son.sonraki=null;
			
		}
		
	}
	
	
	
	
	
	public static void DosyayaYaz()
	{
		Toplar gezici=ilk;
		
		 try { 
			 
             String text = ""; 
             File myFile = new File("/storage/emulated/0/Kümeler.txt"); 
              
             if (!myFile.exists()) 
             {
                     myFile.createNewFile(); 
             } 
             
             
             FileOutputStream fOut = new FileOutputStream(myFile); 
             OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut,"ISO-8859-9");
  
             
             for(int i=0;i<kumeler.length;i++)
             {
          	  
            	 
            	 text=text+(i+1)+". Kume Elemanlari - Eleman Sayisi = "+kumeler[i].eleman_sayisi+"\n\n";
            	 
          	 
          	   
          	   
          	   while(gezici!=null)
          	   {
          		   if(kumeler[i]==gezici.kume)
          		   {
          			   
          			   
          			
          			  
          			   text=text+gezici.renk+"\n";
          			   
          			   
          		   }
          		   
          		   gezici=gezici.sonraki;
          	   }
          	  text=text+"\n";
          	   
          	   gezici=ilk;
          	   
             }
             
      
             myOutWriter.append(text); 
             myOutWriter.close(); 
             fOut.close(); 
             
		 	} 
		 
		 
		 catch (Exception e) 
		 	{ 
             e.printStackTrace(); 
             
		 	} 
		
		
		
	}
	
	
	
	
	
	public static void Kumele(int KumeSayisi)
	{
		Toplar gezici=ilk;
		kumeler=new Kumeler[KumeSayisi];
		Random random=new Random();
		for(int i=0;i<KumeSayisi;i++)
		{
			kumeler[i]=new Kumeler(random.nextInt(100));
			kumeler[i].numara=i+1;
		}
		
		int uzak;
		int degistimi=1;
		Kumeler DegismeKontrol = null;
		
		while(degistimi==1)
		{
			degistimi=0;
			
			while(gezici!=null)
			{
				DegismeKontrol=gezici.kume;
				uzak=Math.abs(kumeler[0].merkez-gezici.OnlukKarsilik);
				gezici.kume=kumeler[0];

				for(int i=0;i<KumeSayisi;i++)
				{	

					if(Math.abs(kumeler[i].merkez-gezici.OnlukKarsilik)<uzak)
					{			
						gezici.kume=kumeler[i];
						uzak=Math.abs(kumeler[i].merkez-gezici.OnlukKarsilik);
					}
				}
				
				if(DegismeKontrol!=gezici.kume)
				{
					degistimi=1;
				}
				gezici=gezici.sonraki;
			}
			
			
			MerkezBul();
			gezici=ilk;
		}
		
		///////////////////////////toplara sira numrasý ver
		gezici=ilk;
		int sira=1;
		while(gezici!=null)
		{
			gezici.sira=sira;
			sira++;
			gezici=gezici.sonraki;
		}
		
		
		
		
	}
	
	
	public static void MerkezBul()
	{
		Toplar gezici; gezici=ilk;
		int toplam=0,say=0;
		
		for(int i=0;i<kumeler.length;i++)
		{	
			
			while(gezici!=null)
			{	
				if(gezici.kume==kumeler[i])
				{
					toplam+=gezici.OnlukKarsilik;
					say++;		
				}
				gezici=gezici.sonraki;
			}
			kumeler[i].eleman_sayisi=say;
			

			try
			{
				kumeler[i].merkez=toplam/say;
				say=0;toplam=0;
				gezici=ilk;
			}
			
			catch(ArithmeticException e)
			{
				say=0;toplam=0;
				gezici=ilk;
			}	
		}
	}
}
	
	
	
	
