package com.example.mibelo.memecreatorv1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class ShowSingleImage extends AppCompatActivity {

    private TextView title;
    private Button deleteButton, galleryButton;
    private ImageView imageV;
    private String titleS;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_image);

        Bundle extras = getIntent().getExtras();
        titleS = extras.getString("file name");

        title = (TextView) findViewById(R.id.imageName);
        title.setText(titleS);

        deleteButton = (Button) findViewById(R.id.deleteB);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteImageFile();
            }
        });

        galleryButton = (Button) findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGallery();
            }
        });

        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/pictures/"
                + titleS;
        File imgFile = new File(path);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
                    .getAbsolutePath());

            imageV = (ImageView) findViewById(R.id.image);
            imageV.setImageBitmap(myBitmap);

        }

        settingDeletionAlert();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Meme Creator");
        setSupportActionBar(myToolbar);

    }

    private void settingDeletionAlert(){
        alert = new AlertDialog.Builder(this);
        alert.setMessage("This item will be deleted? \n Are you sure?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String path = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath()
                        + "/" + title.getText().toString();
                File toDelete = new File(path);
                toDelete.delete();
                Intent intent = new Intent(ShowSingleImage.this,
                        ShowImages.class);
                startActivity(intent);
            }
        });

        alert.setNegativeButton("No", null);
    }

    private void deleteImageFile(){
        alert.show();
    }

    private void goToGallery(){
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
