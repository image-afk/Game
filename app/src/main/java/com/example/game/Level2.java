package com.example.game;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        TextView textView = findViewById(R.id.text_levels);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView  text_left =findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg);

        TextView textdescription = (TextView) dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.leveltwo);


        TextView btnClose = (TextView) dialog.findViewById(R.id.btnclose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent= new Intent(Level2.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialog.dismiss();
            }
        });

        Button btncontinue =(Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);

        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.leveltwoEnd);


        TextView btnClose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent= new Intent(Level2.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();
            }
        });

        Button btncontinue2 =(Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Level2.this, com.example.game.Level3.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }

                dialogEnd.dismiss();
            }
        });


        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(Level2.this,GameLevels.class);
                    startActivity(intent);
                    finish();

                }catch (Exception e){

                }

            }
        });

        final int [] progress = {
                R.id.point1, R.id.point2, R.id.point3,
                R.id.point4, R.id.point5, R.id.point6,
                R.id.point7, R.id.point8, R.id.point9,
                R.id.point10, R.id.point11, R.id.point12,
                R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18,
                R.id.point19, R.id.point20,
        };

        final Animation animation = AnimationUtils.loadAnimation(Level2.this,R.anim.alpha);

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images2[numLeft]);
        text_left.setText(array.texts2[numLeft]);

        numRight = random.nextInt(10);

        while (numLeft==numRight){
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images2[numRight]);
        text_right.setText(array.texts2[numRight]);

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if (numLeft>numRight) {
                        img_left.setImageResource(R.drawable.true_otv);
                    } else{
                        img_left.setImageResource(R.drawable.false_otv);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    if (numLeft>numRight){
                        if (count<20){
                            count=count+1;
                        }
                        for (int i =0;i<20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if (count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count= count-2;
                            }
                        }
                        for (int i =0;i<19;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count==20){

                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if(level >2 ){

                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",3);
                            editor.commit();
                        }
                        dialogEnd.show();

                    }else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        img_left.startAnimation(animation);
                        text_left.setText(array.texts2[numLeft]);

                        numRight = random.nextInt(10);

                        while (numLeft==numRight){
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images2[numRight]);
                        img_right.startAnimation(animation);
                        text_right.setText(array.texts2[numRight]);

                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if (numLeft<numRight) {
                        img_right.setImageResource(R.drawable.true_otv);
                    } else{
                        img_right.setImageResource(R.drawable.false_otv);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){

                    if (numLeft<numRight){
                        if (count<20){
                            count=count+1;
                        }
                        for (int i =0;i<20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if (count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count= count-2;
                            }
                        }
                        for (int i =0;i<19;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    if (count==20){

                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if(level >2 ){

                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",3);
                            editor.commit();
                        }

                        dialogEnd.show();

                    }else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        img_left.startAnimation(animation);
                        text_left.setText(array.texts2[numLeft]);

                        numRight = random.nextInt(10);

                        while (numLeft==numRight){
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images2[numRight]);
                        img_right.startAnimation(animation);
                        text_right.setText(array.texts2[numRight]);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });
    }
    @Override
    public void onBackPressed () {
        try {
            Intent intent = new Intent(Level2.this,GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
}