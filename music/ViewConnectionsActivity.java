package com.example.music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ViewConnectionsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter matchAdapter;
    private RecyclerView.LayoutManager matchLayoutManager;
   // private Button backButton;

    private ArrayList<Matches> resultMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_connections);
        resultMatches=new ArrayList<Matches>();
      //  backButton=new Button(findViewById(R.id.back))
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        matchLayoutManager=new LinearLayoutManager(ViewConnectionsActivity.this);
        recyclerView.setLayoutManager(matchLayoutManager);
        matchAdapter=new MatchesAdapter(getDataSetMatches(), ViewConnectionsActivity.this);
        recyclerView.setAdapter(matchAdapter);

        for(int i=0; i<5;i++) {
            Matches match = new Matches(Integer.toString(i));
            resultMatches.add(match);
        }
        matchAdapter.notifyDataSetChanged();


     /*   backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LoginOrRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }*/
    }
    private List<Matches> getDataSetMatches(){
        return resultMatches;
    }
}