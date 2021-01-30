package com.stock.stockPrice.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stock.stockPrice.main.StockInfo;


public class FirebaseInitializer {
		
	private static final String DATABASE_URL = "https://stockprice-259cd-default-rtdb.firebaseio.com";
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference ref;

	public static void update(Map<String, StockInfo> st) throws IOException {
       
		FileInputStream serviceAccount = new FileInputStream("./serviceAccount.json");
	
        try {
            FirebaseOptions options =  FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(DATABASE_URL)
                .build();
            
            FirebaseApp.initializeApp(options);
            
            firebaseDatabase = FirebaseDatabase.getInstance(DATABASE_URL);
            
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    

        try {
            ref = firebaseDatabase.getReference();
                        
            final CountDownLatch latch = new CountDownLatch(1);
              
            ref.setValue(st, new DatabaseReference.CompletionListener() {
                
            	@Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
            		            		
                    if (databaseError != null) {
                        System.out.println("Data could not be saved " + databaseError.getMessage());
                        latch.countDown();
                    } else {
                        System.out.println("Data saved successfully.");
                        latch.countDown();
                    }
                }
            });
            latch.await();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        FirebaseApp.getInstance().delete();
        
	}
	
	
}
