package com.vijay.validation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Pattern emailpattern = null;

    EditText email_ed, pan_card, isempty,afterdecimal;
    Button validate;
    Pattern pancardPattern = null;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailpattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        pancardPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");


        email_ed = findViewById(R.id.email_ed);
        pan_card = findViewById(R.id.pan_card);
        isempty = findViewById(R.id.isempty);
        afterdecimal= findViewById(R.id.afterdecimal);
        afterdecimal.addTextChangedListener(new DecimalFilter(afterdecimal, getApplicationContext()));
        afterdecimal.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);







        validate = findViewById(R.id.validate);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(emailpattern.matcher(email_ed.getText().toString())).matches()) {
                    Toast.makeText(getApplicationContext(), "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    email_ed.setText("");
                    email_ed.startAnimation(animation);
                } else if (!(pancardPattern.matcher(pan_card.getText().toString())).matches()) {
                    Toast.makeText(getApplicationContext(), "Please enter valid pan number", Toast.LENGTH_SHORT).show();
                    pan_card.setText("");
                    pan_card.startAnimation(animation);

                } else if (isempty.getText().toString().trim().isEmpty()) {
                    isempty.setText("");
                    isempty.startAnimation(animation);
                }

//---------------------------------another way to check -----------------------
                /*if (isempty.getText().toString().trim().equals("")) {
                    isempty.startAnimation(animation);
                }
                */


                else {
                    Toast.makeText(getApplicationContext(), "valid", Toast.LENGTH_SHORT).show();
                    Log.e("email_ed  ", email_ed.getText().toString());
                    Log.e("pan_card  ", pan_card.getText().toString());
                }
            }
        });
    }
}
