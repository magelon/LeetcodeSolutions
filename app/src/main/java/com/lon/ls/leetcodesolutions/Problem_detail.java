package com.lon.ls.leetcodesolutions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lon.ls.leetcodesolutions.data.model.Post;
import com.lon.ls.leetcodesolutions.data.model.Problem;


public class Problem_detail extends AppCompatActivity {
    private TextView mTextMessage;
    Problem problem=new Problem();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("problems");

    String resultText="<p>";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(Html.fromHtml(resultText));
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(problem.getSolutioin());
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        Toolbar topToolBar=findViewById(R.id.toolbar_det);
        setSupportActionBar(topToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // get the text from MainActivity
        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);

        //getProblem info from firebase using text
        // Read from the database

        myRef = database.getReference("problems/"+text);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                 problem.setTitle((String)dataSnapshot.child("title").getValue());
                 problem.setDes((String)dataSnapshot.child("body").getValue());
                 problem.setSolutioin((String)dataSnapshot.child("solution").getValue());

                 Log.v("Problem_detail","title "+problem.getTitle());
                // use the text in a TextView
                TextView textView = (TextView) findViewById(R.id.message);

                int len=problem.getDes().length();

                for(int i=0;i<len;i++){
                    if(problem.getDes().charAt(i)=='.'||problem.getDes().charAt(i)==':'){
                        resultText+="</p><p>";
                    }else{
                        resultText+=problem.getDes().charAt(i);
                    }
                }

                textView.setText(Html.fromHtml(resultText));
                problem.setDes(resultText);
                //textView.setText(problem.getDes());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

            }
        };
        myRef.addValueEventListener(postListener);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
