package com.example.mibelo.memecreatorv1;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class FinalPreview extends AppCompatActivity {

    private ImageView imageView;
    private Button acceptButton;
    private Bitmap imageB;
    private byte[] barray;
    private String nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_preview);

        Bundle extras = getIntent().getExtras();
        barray  = extras.getByteArray("picture");
        nameText = extras.getString("name");

        imageB = BitmapFactory.decodeByteArray(barray, 0, barray.length);
        imageView = (ImageView) findViewById(R.id.preview);
        imageView.setImageBitmap(imageB);

        acceptButton = (Button) findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptPicture(v);
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Meme Creator");
        setSupportActionBar(myToolbar);

    }

    private void acceptPicture(View v){
        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        imageB = Bitmap.createBitmap(imageB, 0, 0,
                imageB.getWidth(), imageB.getHeight(), matrix,
                true);

        String title = nameText + ".jpg";

        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/pictures/" + title;

        // Store the picture
        try {
            FileOutputStream out = new FileOutputStream(path);
            imageB.compress(Bitmap.CompressFormat.JPEG, 100, out);
            // out.write(bitmapPicture);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(FinalPreview.this, ShowSingleImage.class);
        intent.putExtra("file name", title);
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
