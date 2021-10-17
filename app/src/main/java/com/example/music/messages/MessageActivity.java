package com.example.music.messages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.music.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView.Adapter messageAdapter;
    private String currentUserID, connectionID, messageID;
    private ArrayList<Message> resultMessages;
    private EditText messageTextfield;
    DatabaseReference databaseUser, databaseMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        connectionID=getIntent().getExtras().getString("connectionID");
        currentUserID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseUser= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("connections").child("match").child(connectionID).child("messageID");
        databaseMessenger= FirebaseDatabase.getInstance().getReference().child("Messages");
        getMessageID();

        resultMessages=new ArrayList<Message>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager messageLayoutManager = new LinearLayoutManager(MessageActivity.this);
        recyclerView.setLayoutManager(messageLayoutManager);
        messageAdapter=new MessageAdapter(getDataSetMessages(), MessageActivity.this);
        recyclerView.setAdapter(messageAdapter);

        messageTextfield=findViewById(R.id.messageTextfield);
        Button sendButton = findViewById(R.id.send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String newMessageText=messageTextfield.getText().toString();
        if(!newMessageText.isEmpty()){
            DatabaseReference newMessageDB=databaseMessenger.push();
            Map newMessage = new HashMap();
            newMessage.put("createdBy", currentUserID);
            newMessage.put("text", newMessageText);
            newMessageDB.setValue(newMessage);
        }
        messageTextfield.setText(null);
    }
    private List<Message> getDataSetMessages(){
        return resultMessages;
    }
    private void getMessageID(){
        databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    messageID=snapshot.getValue().toString();
                    databaseMessenger=databaseMessenger.child(messageID);
                    getMessages();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void getMessages() {
        databaseMessenger.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()){
                    String message=null;
                    String createdBy=null;
                    if(snapshot.child("text").getValue()!=null){
                        message=snapshot.child("text").getValue().toString();
                    }
                    if(snapshot.child("createdBy").getValue()!=null){
                        message=snapshot.child("createdBy").getValue().toString();
                    }
                    if(message!=null && createdBy!=null){
                        boolean currentUserBoolean=false;
                        if(createdBy.equals(currentUserID)){
                            currentUserBoolean=true;
                        }
                        Message newMessage=new Message(message, currentUserBoolean);
                        resultMessages.add(newMessage);
                        messageAdapter.notifyDataSetChanged();
                    }
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
}