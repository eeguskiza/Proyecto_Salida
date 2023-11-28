package org.proyectosalida.Datos;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Map;

public class Provider {
    CollectionReference reference;
    static Firestore db;

    public static boolean guardarPersona(String collection, String documento, Map<String, Object> data) {
        db = FirestoreClient.getFirestore();

        try{
            DocumentReference docRef = db.collection(collection).document(documento);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Guardado con éxito: " + result.get().getUpdateTime());
            return true;
        }catch(Exception e){
            System.out.println("Error al guardar: " + e.getMessage());
        }
        return false;

    }
}
