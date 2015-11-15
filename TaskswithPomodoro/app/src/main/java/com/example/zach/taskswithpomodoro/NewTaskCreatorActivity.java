package com.example.zach.taskswithpomodoro;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewTaskCreatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_creator);
    }

    public void addItem(View v){

        EditText editTextTitle = (EditText)findViewById(R.id.editTextTitle);
        EditText editTextDetails = (EditText)findViewById(R.id.editTextDetails);

        Intent data = new Intent();
        data.putExtra("title", editTextTitle.getText().toString());
        data.putExtra("details", editTextDetails.getText().toString());
        setResult(Activity.RESULT_OK, data);
        finish();




    }


}
