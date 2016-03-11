package com.example.mibelo.memecreatorv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;


public class StartActivity extends AppCompatActivity {

    private Button createB;
    private Button seeB;
    private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        createB = (Button) findViewById(R.id.createButton);
        seeB = (Button) findViewById(R.id.seeButton);
        settings = (Button) findViewById(R.id.settingsButton);

        createB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createButtonClick();
            }
        }
        );

        seeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeGallery();
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Meme Creator");
        setSupportActionBar(myToolbar);
    }

    private void createButtonClick(){
        Intent i = new Intent(this, CreateMeme.class);
        startActivity(i);
    }

    private void seeGallery(){
        Intent intent = new Intent(this, ShowImages.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch(item.getItemId()){

            case R.id.home:
                if(!(this.getClass().getSimpleName().equals(StartActivity.class.getSimpleName()))) {
                    intent = new Intent(this, StartActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.gallery:
                if(!(this.getClass().getSimpleName().equals(ShowImages.class.getSimpleName()))) {
                    intent = new Intent(this, ShowImages.class);
                    startActivity(intent);
                }
                break;

            default:
                //TODOOOO!!!
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
