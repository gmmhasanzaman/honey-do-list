package com.example.honeydolistapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button saveBtn;
    private EditText editTextTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = findViewById(R.id.saveBtnId);
        editTextTodo = findViewById(R.id.toDoEditTextId);
        Log.v("Main_Oncreate","This Work");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Write to File
                if (!editTextTodo.getText().toString().equals("")){
                    Log.v("Main_If","This Work");

                    String message = editTextTodo.getText().toString();
                    writToFile(message);

                }



            }
        });
        // Read from File
        try {
            if (readFromFile() != null){
                editTextTodo.setText(readFromFile());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Write to file
    private void writToFile(String message){
        try {
            OutputStreamWriter outputStWriter = new
                    OutputStreamWriter(openFileOutput("todoList.txt", Context.MODE_PRIVATE));

            outputStWriter.write(message);
            outputStWriter.close();//always close streamWriter
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Read from File
    private String readFromFile(){

        String result = "";
        try {
            InputStream inputStream = openFileInput("todoList.txt");

            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((tempString = bufferedReader.readLine()) != null){
                    stringBuilder.append(tempString);

                }
                inputStream.close();
                result = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
}
