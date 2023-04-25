package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Lastimage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lastimage);
        ImageView imgg=(ImageView)findViewById(R.id.imageView);
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        File[] files = mediaStorageDir.listFiles();
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
                return Long.compare(f2.lastModified(), f1.lastModified());
            }
        });
        File mostRecentFile = files[0];
        imgg.setImageBitmap(BitmapFactory.decodeFile(mostRecentFile.getPath()));
    }
}