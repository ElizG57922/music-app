package com.example.music.connections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import com.example.music.MainActivity;
import com.example.music.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewConnectionsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter matchAdapter;
    private RecyclerView.LayoutManager matchLayoutManager;
    private String currentUserID;
    private ArrayList<Matches> resultMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_connections);
        currentUserID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        resultMatches=new ArrayList<Matches>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        matchLayoutManager=new LinearLayoutManager(ViewConnectionsActivity.this);
        recyclerView.setLayoutManager(matchLayoutManager);
        matchAdapter=new MatchesAdapter(getDataSetMatches(), ViewConnectionsActivity.this);
        recyclerView.setAdapter(matchAdapter);

        getUserMatchID();
    }

    private void getUserMatchID() {
        DatabaseReference connectionDB= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("connections").child("match");
        connectionDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot match: snapshot.getChildren()){
                        getMatchInfo(match.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getMatchInfo(String key) {
        DatabaseReference userDB= FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        userDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String userID = snapshot.getKey();
                    String name="";
                    String profilePicURL="";
                    if(snapshot.child("name").getValue()!=null){
                        name=snapshot.child("name").getValue().toString();
                    }
                    if(snapshot.child("profilePicURL").getValue()!=null){
                        profilePicURL=snapshot.child("profilePicURL").getValue().toString();
                    }

                    Matches newMatch = new Matches(userID, name, profilePicURL);
                    resultMatches.add(newMatch);
                    matchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private List<Matches> getDataSetMatches(){
        return resultMatches;
    }
}