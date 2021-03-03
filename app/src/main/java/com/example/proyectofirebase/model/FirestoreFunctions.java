package com.example.proyectofirebase.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectofirebase.model.pojo.Comprador;
import com.example.proyectofirebase.model.pojo.Telefono;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirestoreFunctions {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser;
    private CollectionReference collection = db.collection("telefono");
    private CollectionReference collection2 = db.collection("comprador");
    private MutableLiveData<List<Telefono>> telefonos = new MutableLiveData<>();
    private MutableLiveData<List<Comprador>> compradores = new MutableLiveData<>();
    public Boolean autentificado = false;

    public void addTelefono(Telefono telefono){
        collection.add(telefono)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.v("xyz", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
    }

    public MutableLiveData<List<Telefono>> readTelefono(){
        collection.get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            try {
                                telefonos.setValue(task.getResult().toObjects(Telefono.class));
                            }catch (NullPointerException e){
                                Log.v("xyz", "Vacio");
                            }
                            for(QueryDocumentSnapshot document: task.getResult()){
                                Log.v("xyz", document.getId()+" =>"+document.getData());
                                Log.v("xyz", document.getData().getClass().getCanonicalName());
                            }
                        }else{
                            Log.v("xyz", task.getException().toString());
                        }
                    }
                });
        return telefonos;
    }

    public void editTelefono(Telefono telefono){
        collection.document(telefono.getNumTelef()).set(telefono)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.v("xyz", aVoid.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
    }

    public void deleteTelefono(String numTelef){
        collection.document(numTelef).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.v("xyz", aVoid.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
        collection2.document(numTelef).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.v("xyz", aVoid.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
    }

    public void addComprador(Comprador comprador){
        collection2.add(comprador)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.v("xyz", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
    }

    public MutableLiveData<List<Comprador>> readComprador(){
        collection2.get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            compradores.setValue(task.getResult().toObjects(Comprador.class));
                            /*for(Telefono c: telefonos){
                                Log.v("xyz", c.toString());
                            }*/
                            for(QueryDocumentSnapshot document: task.getResult()){
                                Log.v("xyz", document.getId()+" =>"+document.getData());
                                Log.v("xyz", document.getData().getClass().getCanonicalName());
                            }
                        }else{
                            Log.v("xyz", task.getException().toString());
                        }
                    }
                });
        return compradores;
    }

    public void editComprador(Comprador comprador){
        collection2.document(comprador.getDni()).set(comprador)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.v("xyz", aVoid.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
    }

    public void deleteComprador(String numTelef){
        collection2.document(numTelef).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.v("xyz", aVoid.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
    }

    public boolean singIn(String email, String clave){

        firebaseAuth.signInWithEmailAndPassword(email, clave)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.v("XYZ","success");
                        currentUser = firebaseAuth.getCurrentUser();
                        Log.v("XYZ",currentUser.getEmail() + " " + currentUser.isEmailVerified());

                    }
                })
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.v("xyz","success");
                        currentUser = firebaseAuth.getCurrentUser();
                        if(task.getException() != null){
                            Log.v("xyz",task.getException().toString());
                            if(task.getException().toString().contains("password") || task.getException().toString().contains("no user") || task.getException().toString().contains("email")){
                                Log.v("xyz","El usuario o la contraseña no son correctos");
                            }
                            currentUser = null;
                        } else {
                            Log.v("xyz","No exception");
                            currentUser = firebaseAuth.getCurrentUser();
                        }
                        if(currentUser == null){
                            Log.v("xyz","null");
                        } else {
                            autentificado = true;
                            Log.v("xyz",currentUser.getEmail() + " " + currentUser.isEmailVerified());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz","failure");
                    }
                });
        return autentificado;
    }

    public boolean create(String email, String clave){
        db = FirebaseFirestore.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.getException()!=null) {
                    Log.v("xyz", task.getException().toString());
                    if(task.getException().toString().contains("email")){
                        Log.v("xyz","Existe una cuenta con ese email, escoge otro");
                    }
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.v("xyz","success");
                autentificado = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("xyz","failure");
                Log.v("xyz",e.getLocalizedMessage());
            }
        });
        return autentificado;
    }
}
