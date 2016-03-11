package com.example.mibelo.memecreatorv1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;

public class SetTextMeme extends AppCompatActivity {

    private EditText top;
    private EditText bottom;
    private EditText name;
    private byte[] imageB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text_meme);

        top = (EditText) findViewById(R.id.topText);
        bottom = (EditText) findViewById(R.id.bottomText);
        name = (EditText) findViewById(R.id.nameText);

        top.setHint("Top Caption");
        bottom.setHint("Bottom Caption");
        name.setHint("Name your Meme");

        final Button button = (Button) findViewById(R.id.memeMeBroButton);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        memeButtonClicked(v);
                    }
                }
        );

        Bundle extras = getIntent().getExtras();
        imageB = extras.getByteArray("picture");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Meme Creator");
        setSupportActionBar(myToolbar);
    }

    private void memeButtonClicked(View v){
        Intent intent = new Intent(this, FinalPreview.class);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageB, 0, imageB.length, options);

        Bitmap mutableB = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        Canvas canvas = new Canvas(mutableB);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(15);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        String topText = top.getText().toString();
        String bottomText = bottom.getText().toString();
        String nametext = name.getText().toString();

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        //Position calculation
        int xPos1 = (int) (width - paint.getTextSize() * Math.abs(topText.length() / 2)) / 2;
        int xPos2 = (int) (width - paint.getTextSize() * Math.abs(bottomText.length() / 2)) / 2;
        int yPos1 = height/8;
        int yPos2 = height - height/8;

        canvas.drawBitmap(mutableB, 0, 0, paint);
        canvas.drawText(topText, xPos1, yPos1, paint);
        canvas.drawText(bottomText, xPos2, yPos2, paint);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mutableB.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imageB = stream.toByteArray();

        intent.putExtra("picture", imageB);
        intent.putExtra("name", nametext);

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
