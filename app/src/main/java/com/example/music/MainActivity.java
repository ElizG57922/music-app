package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.music.cards.ArrayAdapterClass;
import com.example.music.cards.Card;
import com.example.music.connections.ViewConnectionsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapterClass arrayAdapter;
    List<Card> rowItems;
    private FirebaseAuth myAuth;
    private DatabaseReference userDatabase;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logoutButton = findViewById(R.id.logout);
        Button viewProfileButton = findViewById(R.id.viewProfile);
        myAuth = FirebaseAuth.getInstance();
        currentUserID = myAuth.getCurrentUser().getUid();
        userDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        checkUsers();

        rowItems=new ArrayList<Card>();
        arrayAdapter = new ArrayAdapterClass(this, R.layout.item, rowItems);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginOrRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Card currentCard = (Card) dataObject;
                String userID = currentCard.getUserID();
                userDatabase.child(userID).child("connections").child("reject").child(currentUserID).setValue(true);
                Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onRightCardExit(Object dataObject) {
                Card currentCard = (Card) dataObject;
                String userID = currentCard.getUserID();
                userDatabase.child(userID).child("connections").child("accept").child(currentUserID).setValue(true);
                checkMatch(userID);
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
            }
            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
            }
        });
    }

    public void checkMatch(String userID){
        DatabaseReference currentConnectionsDb = userDatabase.child(currentUserID).child("connections").child("accept").child(userID);
        currentConnectionsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Toast.makeText(MainActivity.this, "New Connection", Toast.LENGTH_LONG).show();

                    String key=FirebaseDatabase.getInstance().getReference().child("Messages").push().getKey();

                    userDatabase.child(snapshot.getKey()).child("connections").child("match").child(currentUserID).child("messageID").setValue(true);
                    userDatabase.child(currentUserID).child("connections").child("match").child(snapshot.getKey()).child("messageID").setValue(true);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void checkUsers() {
        //  final FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users");
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //if snapshot exists, user to be added is not current user (you can't right swipe yourself), and current user hasn't already been rejected by new user
                if(snapshot.exists() && !snapshot.getKey().equals(currentUserID) && !snapshot.child("connections").child("reject").hasChild(currentUserID) && !snapshot.child("connections").child("accept").hasChild(currentUserID)){
                    String profilePicURL="defaultImage";
                    if(!snapshot.child("profilePicURL").getValue().equals("defaultImage")){
                        profilePicURL = snapshot.child("profilePicURL").getValue().toString();
                    }
                    Card item = new Card(snapshot.getKey(), snapshot.child("name").getValue().toString(), profilePicURL);
                    rowItems.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void viewConnections(View view) {
        Intent intent = new Intent(MainActivity.this, ViewConnectionsActivity.class);
        startActivity(intent);
    }
}