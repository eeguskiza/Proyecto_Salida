package org.proyectosalida.Datos;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Conexion {
    public static Firestore db;

    public static void conectar() {
        try {
            FileInputStream serviceAccount = new FileInputStream("proyectosalida-2004.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            System.out.println("Conexión con la base de datos establecida");
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo de configuración");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }

    }
}
