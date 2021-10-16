package com.example.music.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.music.R;
import com.example.music.connections.Matches;
import com.example.music.connections.MatchesAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter messageAdapter;
    private RecyclerView.LayoutManager messageLayoutManager;
    // private Button backButton;
    private String currentUserID;
    private ArrayList<Message> resultMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        currentUserID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        resultMessages=new ArrayList<Message>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        messageLayoutManager=new LinearLayoutManager(MessageActivity.this);
        recyclerView.setLayoutManager(messageLayoutManager);
        messageAdapter=new MessageAdapter(getDataSetMessages(), MessageActivity.this);
        recyclerView.setAdapter(messageAdapter);

    }
    private List<Message> getDataSetMessages(){
        return resultMessages;
    }

}