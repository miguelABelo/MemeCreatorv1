package com.example.mibelo.memecreatorv1;

import android.content.Intent;
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

public class ShowDownloads extends AppCompatActivity {

    private ListView downList;
    private String[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_downloads);

        downList = (ListView) findViewById(R.id.downloadsList);

        downList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) downList.getItemAtPosition(position);
                Intent intent = new Intent(ShowDownloads.this, ShowSingleDownload.class);
                intent.putExtra("download", s);
                startActivity(intent);
            }
        });

        showDownloads();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Meme Creator");
        setSupportActionBar(myToolbar);
    }

    private void showDownloads(){

        String filteredFiles[];
        int j = 0;

        files = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).list();

        for(String s:files){
            //For now only detects jpg files
            if(s.contains(".jp") || s.contains("png"))
                j++;
        }

        filteredFiles = new String[j];

        j = 0;

        for(int i = 0; i < files.length; i++){
            String s = files[i];
            if(s.contains(".jp") || s.contains(".png")){
                filteredFiles[j] = s;
                j++;
            }
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.medium_black_text, filteredFiles);

        downList.setAdapter(adapter);

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
