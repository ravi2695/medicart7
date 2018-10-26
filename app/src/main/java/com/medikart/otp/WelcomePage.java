package com.medikart.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);


        textView = findViewById(R.id.tv);
        imageView = findViewById(R.id.iv);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition);

        textView.startAnimation(animation);
        imageView.startAnimation(animation);

        Thread timer = new Thread(){

            public void run()
            {
                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(WelcomePage.this,MainActivity.class));
                    finish();
                }

            }


        };
        timer.start();
    }
}
