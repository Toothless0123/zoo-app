package com.example.zooappframework;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private ImageView animalImg;
    private Button getRandom;
    private TextView animalName;
    private TextView animalDescription;
    private HashMap<String, Animal> aniMap;

    private void readAnimalData() {
        aniMap = new HashMap<String, Animal>();
        int count = 0;

        InputStream is = getResources().openRawResource(R.raw.animal_info);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {

                String [] tokens = line.split(",");
                String aniName = tokens[0];
                String aniDesc = tokens[1];
                String imgName = tokens[2];

                aniMap.put((aniName.replaceAll("\\s", "")).toLowerCase(), new Animal(aniName, aniDesc, imgName, count));
                count++;
            }
        } catch (IOException e) {

            Log.wtf("MyActivity","Error reading data file on line "+line, e);
            e.printStackTrace();
        }
    }

    private void showAnimal(Animal a){

        animalName.setText(a.getName());
        animalDescription.setText(a.getDesc());
        int resID = getResources().getIdentifier(a.getIM(), "drawable",  getPackageName());
        animalImg.setImageResource(resID);
    }

    private boolean fetchAnimal(String key){
        boolean outcome = false;
        if (aniMap.containsKey(key)){
            Animal a = aniMap.get(key);
            showAnimal(a);
            outcome = true;
        }
        return outcome;
    }

    private void pickInputAnimal(){
        System.out.println("Inside pickInputAnimal");
        Scanner input = new Scanner(System.in);
        String picked = input.nextLine();
        picked = (picked.replaceAll("\\s", "")).toLowerCase();
        boolean result = fetchAnimal(picked);
        if (!result){
            for (String s: aniMap.keySet()){
                if (s.contains(picked)){
                    Animal a = aniMap.get(s);
                    showAnimal(a);
                }
            }
        }
    }

    private void pickRandAnimal(Button b){
        b.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Random r = new Random();
                int keyVal = r.nextInt(67);
                for (String s: aniMap.keySet()){
                    Animal a = aniMap.get(s);
                    if (a.getID() == keyVal){
                        showAnimal(a);
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("THIS PROGRAM HAS STARTED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animalImg = (ImageView)findViewById(R.id.animalView);
        animalName = (TextView)findViewById(R.id.animalName);
        animalDescription = (TextView)findViewById(R.id.animalDescription);
        getRandom = (Button)findViewById(R.id.Randomize);
        readAnimalData();
        System.out.println(aniMap.size());
        //pickInputAnimal();
        pickRandAnimal(getRandom);
    }


}