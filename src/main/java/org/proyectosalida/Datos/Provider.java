package org.proyectosalida.Datos;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.protobuf.Api;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
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

    public static boolean actualizarPersona(String collection, String documento, Map<String, Object> data) {
        db = FirestoreClient.getFirestore();

        try{
            DocumentReference docRef = db.collection(collection).document(documento);
            ApiFuture<WriteResult> result = docRef.update(data);
            System.out.println("Actualizado con éxito: " + result.get().getUpdateTime());
            return true;
        }catch(Exception e){
            System.out.println("Error al actualizar: " + e.getMessage());
        }
        return false;

    }

    public static boolean eliminarPersona(String collection, String documento) {
        db = FirestoreClient.getFirestore();

        try{
            DocumentReference docRef = db.collection(collection).document(documento);
            ApiFuture<WriteResult> result = docRef.delete();
            System.out.println("Eliminado con éxito: " + result.get().getUpdateTime());
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar: " + e.getMessage());
        }
        return false;

    }

    public static void cargarTablaDueño(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Correo");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Edad");
        modelo.addColumn("Locales");

        try{
            CollectionReference dueños = Conexion.db.collection("Dueño");
            ApiFuture<QuerySnapshot> query = dueños.get();
            for (DocumentSnapshot document : query.get().getDocuments()) {
                modelo.addRow(new Object[]{
                        document.getId(),
                        document.getString("Nombre"),
                        document.getString("Apellido"),
                        document.getString("Teléfono"),
                        document.getString("Correo"),
                        document.getString("Contraseña"),
                        document.getDouble("Edad"),
                        //document.get("Locales")
                        document.getData() //Guardamos los datos en raw y luego si es el que inicia sesion se convierten
                });
            }

        }
        catch(Exception e){
            System.err.println("Error al cargar la tabla: " + e.getMessage());
        }

        tabla.setModel(modelo);

    }

    public static void cargarTablaCliente(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Correo");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Edad");
        modelo.addColumn("Visitas");


        try{
            CollectionReference dueños = Conexion.db.collection("Cliente");
            ApiFuture<QuerySnapshot> query = dueños.get();
            for (DocumentSnapshot document : query.get().getDocuments()) {
                modelo.addRow(new Object[]{
                        document.getId(),
                        document.getString("Nombre"),
                        document.getString("Apellido"),
                        document.getString("Teléfono"),
                        document.getString("Correo"),
                        document.getString("Contraseña"),
                        document.getDouble("Edad"),
                        document.get("Visitas")
                });
            }

        }
        catch(Exception e){
            System.err.println("Error al cargar la tabla: " + e.getMessage());
        }

        tabla.setModel(modelo);

    }
}
