package com.project.construction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class hire extends AppCompatActivity {
    private static int IMG_RESULT = 1;
    ImageView imageViewLoad;
    String[] FILE;
    String ImageDecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);

        imageViewLoad = findViewById(R.id.icon);

        imageViewLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT);

            }


        });
    }
        @Override
        protected void onActivityResult(int requestCode,int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            try {
                String ImageDecode;

                if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                        && null != data) {


                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageViewLoad.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(hire.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }

                }
            } catch (Exception e) {
                Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                        .show();
            }

        }
////



}
