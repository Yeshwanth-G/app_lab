package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.hardware.Camera;
import android.media.MediaExtractor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    private static final int MEDIA_TYPE_VIDEO = 2;
    private Camera mCamera;
    Retro_interface rt;
    private FileOutputStream fos;
    private CameraPreview mPreview;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public  int rand=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login_btn=(Button)findViewById(R.id.login);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://localhost:8000/").addConverterFactory(GsonConverterFactory.create()).build();
        rt=retrofit.create(Retro_interface.class);
        EditText email_inp=(EditText)findViewById(R.id.email);
        EditText password_inp=(EditText)findViewById(R.id.password);
        EditText capptcha_inp=(EditText)findViewById(R.id.captcha_inp);
        ImageView img=(ImageView)findViewById(R.id.captcha_img);
        img.setBackgroundColor(getResources().getColor(R.color.teal_700));
        rand=gen(img);
        Toast.makeText(MainActivity.this," "+rand,Toast.LENGTH_LONG).show();
        ImageButton ib=(ImageButton)findViewById(R.id.imageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rand=gen(img);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w(TAG,"Clicked");
                String email=email_inp.getText().toString();
                String password=password_inp.getText().toString();
                String c_inp=capptcha_inp.getText().toString();
                String p=Integer.toString(rand);
                if(!p.equals(c_inp)){
                    Toast.makeText(MainActivity.this,"Invalid Captcha..."+c_inp+","+p,Toast.LENGTH_SHORT).show();
                }else {
                    helperclass h = new helperclass("est", email, password);
                    Call<Messege> call = rt.signup(h);
                    load(call);
                }
            }
        });
    }
    public  int gen(ImageView img){
        Integer random = new Random().nextInt(90001) + 1000; // [0, 60] + 20 => [20, 80]
        String rs=random.toString();
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(getResources().getColor(R.color.purple_500));
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(rs) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        Paint lp=new Paint();
        lp.setColor(getResources().getColor(R.color.purple_700));
        lp.setStrokeWidth(5);
        canvas.drawText(rs, 0, baseline, paint);
        canvas.drawLine(0,0,width,height,lp);
        img.setImageBitmap(image);
        return random;
    }
    public void load(Call<Messege>call){
        call.clone().enqueue(new Callback<Messege>() {
            @Override
            public void onResponse(Call<Messege> call, Response<Messege> response) {
                if(response.isSuccessful()){
                    Messege lt=response.body();
                        Log.d("",","+lt.messege);
                        Toast.makeText(MainActivity.this,lt.messege,Toast.LENGTH_LONG).show();
                }else{
                    Messege lt=response.body();
                    Log.d("",","+lt.messege);
                    Toast.makeText(MainActivity.this,lt.messege,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Messege> call, Throwable t) {
                Log.w(TAG,"Response Failed...."+t.getMessage());
            }
        });
    }
}