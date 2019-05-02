package com.vijay.validation;


import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class DecimalFilter implements TextWatcher {

    int count = -1;
    EditText et;
    Context activity;
    InputFilter[] fArray;
    public DecimalFilter(EditText edittext, Context activity) {
        et = edittext;
        this.activity = activity;


        fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(10);//Re sets the maxLength of edittext to 10.

    }

    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            String str = et.getText().toString();

            et.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {



                        count--;
                         fArray = new InputFilter[1];
                        fArray[0] = new InputFilter.LengthFilter(10);//Re sets the maxLength of edittext to 10.
                        et.setFilters(fArray);
                    }
                    if (count > 3) {
                        //Toast.makeText(DialogFragment.this), "Sorry! You cant enter more than two digits after decimal point!", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });
            char t = str.charAt(s.length() - 1);
            if (t == '.') {
                count = 0;
            }
            if (count >= 0) {
                if (count == 3) {
                     fArray = new InputFilter[1];
                    fArray[0] = new InputFilter.LengthFilter(s.length());
                    et.setFilters(fArray); // sets edittext's maxLength to number of digits now entered.

                }
                count++;
            }
        }
    }
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {



        fArray[0] = new InputFilter.LengthFilter(10);
        et.setFilters(fArray); // sets edittext's maxLength to number of digits now entered.



    }
    public void onTextChanged(CharSequence s, int start, int before, int countt) {



    }
}


