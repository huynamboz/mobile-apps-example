package com.example.keep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.keep.model.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("messasge");
        firestore = FirebaseFirestore.getInstance();

//        login("test@gmail.com", "123456");
//        createNewUser("nam@gmail.com", "123456");
//        postDataToRealtimeDB();
//        readDataFromRealtimeDB();
//        postDataToFirestore();
        addPostData(new Post("Huy Nam", "Hello this is hello world app"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private  void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("DEBUG", "Login successfull");
                        } else {
                            Log.d("DEBUG", "Login failed");
                        }
                    }
                });
    }
    private void createNewUser(String newUserEmail, String newUserPass) {
        mAuth.createUserWithEmailAndPassword(newUserEmail, newUserPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("DEBUG", "Create successfull");
                        } else {
                            Log.d("DEBUG", "Create failed");
                        }
                    }
                });
    }

    private void resetPass(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("DEBUG", "reset successfull");
                        } else {
                            Log.d("DEBUG", "reset failed");
                        }
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
    }

    private void postDataToRealtimeDB(String data) {
        myRef.setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("DEBUG", "Post data successfull");
                        } else {
                            Log.d("DEBUG", "Post data failed");
                        }
                    }
                });
    }

    private void readDataFromRealtimeDB() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                Log.d("DEBUG", "Value is " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("DEBUG", "Failed to read value ", error.toException());
            }
        });
    }

    private void postDataToFirestore() {
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        firestore.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("DEBUG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("DEBUG", "Error adding document", e);
                    }
                });
    }

    public void addPostData(Post data) {
        DatabaseReference myRefRoot = database.getReference();
        myRefRoot.child("posts").setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("DEBUG", "Post data successfull");
                        } else {
                            Log.d("DEBUG", "Post data failed");
                        }
                    }
                });
    }
}