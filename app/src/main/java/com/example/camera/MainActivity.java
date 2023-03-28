package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login_btn=(Button)findViewById(R.id.login);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/").addConverterFactory(GsonConverterFactory.create()).build();
        rt=retrofit.create(Retro_interface.class);
        EditText email_inp=(EditText)findViewById(R.id.email);
        EditText password_inp=(EditText)findViewById(R.id.password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.w(TAG,"Clicked");

                String email=email_inp.getText().toString();
                String password=password_inp.getText().toString();
                helperclass h=new helperclass("est",email,password);
                Call<helperclass> call=rt.getimages(h);
                load(call);
            }
        });
    }

    private void load1(Call<Messege> call) {
        call.clone().enqueue(new Callback<Messege>() {
            @Override
            public void onResponse(Call<Messege> call, Response<Messege> response) {
                if(response.isSuccessful()){
                    Messege lt=response.body();
                    Log.w(TAG, "onResponse: "+lt.messege);
                }else{
                    Messege lt=response.body();
                    Log.w(TAG, "onResponseErr: "+lt.messege);
                    Log.w(TAG, "onResponse: "+"Adhiraa");
                }
            }
            @Override
            public void onFailure(Call<Messege> call, Throwable t) {
                Log.w(TAG,"Response Failed...."+t.getMessage());
            }
        });
    }

    public void load(Call<helperclass>call){
        call.clone().enqueue(new Callback<helperclass>() {
            @Override
            public void onResponse(Call<helperclass> call, Response<helperclass> response) {
                if(response.isSuccessful()){
                    helperclass lt=response.body();
                        Log.w("TAG",lt.email+","+lt.password);
                }
            }
            @Override
            public void onFailure(Call<helperclass> call, Throwable t) {
                Log.w(TAG,"Response Failed...."+t.getMessage());
            }
        });
    }
}