package com.example.mibelo.memecreatorv1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ShowImages extends AppCompatActivity {

    private ListView filesList;
    private String[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);

        filesList = (ListView) findViewById(R.id.imagesList);

        showSavedFiles();

        filesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) filesList.getItemAtPosition(position);
                Intent intent = new Intent(ShowImages.this, ShowSingleImage.class);
                intent.putExtra("file name", s);
                startActivity(intent);
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Meme Creator");
        setSupportActionBar(myToolbar);

    }

    private void showSavedFiles(){

        String[] filteredImages;

        images = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).list();

        int j = 0;

        for(String s:images){
            if(s.contains(".jpg"))
                j++;
        }

        filteredImages = new String[j];
        j = 0;

        for(String s:images){
            if(s.contains(".jpg")) {
                filteredImages[j] = s;
                j++;
            }
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.medium_black_text, filteredImages);

        filesList.setAdapter(adapter);

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
