package com.stock.stockPrice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class FirebaseInitializerTest {

	@Test
	public static void shouldReturnDatabaseConnection() throws IOException {
		
		String DATABASE_URL = "https://stockprice-259cd-default-rtdb.firebaseio.com";
		FileInputStream serviceAccount = new FileInputStream("./serviceAccount.json");
        FirebaseOptions options =  FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl(DATABASE_URL)
            .build();
            FirebaseApp.initializeApp(options);
   
       DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

       assertTrue(ref != null);
       
	}
}
