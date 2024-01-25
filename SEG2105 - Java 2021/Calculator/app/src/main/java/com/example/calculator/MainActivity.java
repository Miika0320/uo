package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //create instance variables and variables for widgets
    Button zero, one, two, three, four, five, six, seven, eight;
    Button nine, equals, plus, minus, times, divide, clear, dot;
    EditText screen;

    private enum operations{add,subtract,multiply, divide, none}
    private operations myOp;
    private double first;
    private double second;
    private double result;
    private String str1;
    private String str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiates instant variable
        str1 = "";
        str2 = "";
        myOp = operations.none;

        // instantiates widget variables
        screen = findViewById(R.id.txtScreen);
        zero = findViewById(R.id.btn0);
        one = findViewById(R.id.btn1);
        two = findViewById(R.id.btn2);
        three = findViewById(R.id.btn3);
        four = findViewById(R.id.btn4);
        five = findViewById(R.id.btn5);
        six = findViewById(R.id.btn6);
        seven = findViewById(R.id.btn7);
        eight = findViewById(R.id.btn8);
        nine = findViewById(R.id.btn9);
        equals = findViewById(R.id.btnEquals);
        plus = findViewById(R.id.btnAdd);
        minus = findViewById(R.id.btnMinus);
        times = findViewById(R.id.btnMultiply);
        divide = findViewById(R.id.btnDivide);
        clear = findViewById(R.id.btnClear);
        dot = findViewById(R.id.btnDecimal);

        //when buttons are clicked - 0

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="0";
                    screen.setText(str1);
                }else {
                    str2+="0";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 1

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="1";
                    screen.setText(str1);
                }else {
                    str2+="1";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 2

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="2";
                    screen.setText(str1);
                }else {
                    str2+="2";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 3

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="3";
                    screen.setText(str1);
                }else {
                    str2+="3";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 4

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="4";
                    screen.setText(str1);
                }else {
                    str2+="4";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 5

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="5";
                    screen.setText(str1);
                }else {
                    str2+="5";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 6

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="6";
                    screen.setText(str1);
                }else {
                    str2+="6";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 7

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="7";
                    screen.setText(str1);
                }else {
                    str2+="7";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 8

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="8";
                    screen.setText(str1);
                }else {
                    str2+="8";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - 9

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="9";
                    screen.setText(str1);
                }else {
                    str2+="9";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - decimal

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+=".";
                    screen.setText(str1);
                }else {
                    str2+=".";
                    screen.setText(str2);
                }
            }
        });

        //when buttons are clicked - clear

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOp == operations.none){
                    str1+="";
                    screen.setText(str1);
                }else {
                    str2+="";
                    screen.setText(str2);
                }
            }
        });

        //operations
        //when buttons are clicked - +
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOp = operations.add;
            }
        });

        //when buttons are clicked - -

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOp = operations.subtract;
            }
        });

        //when buttons are clicked - x

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOp = operations.multiply;
            }
        });

        //when buttons are clicked - /

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOp = operations.add;
            }
        });

        //when buttons are clicked - =

        equals.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (myOp == operations.add){
                    result = Double.parseDouble(str1)+Double.parseDouble(str2);
                    screen.setText(Double.toString(result));
                }else if(myOp == operations.subtract) {
                    result = Double.parseDouble(str1) - Double.parseDouble(str2);
                    screen.setText(Double.toString(result));
                }else if(myOp == operations.multiply) {
                    result = Double.parseDouble(str1) * Double.parseDouble(str2);
                    screen.setText(Double.toString(result));
                }else if(myOp == operations.divide) {
                    result = Double.parseDouble(str1) / Double.parseDouble(str2);
                    screen.setText(Double.toString(result));
                }else{
                    result = Double.parseDouble(str1);
                    screen.setText(Double.toString(result));
                }
                str1 = "";
                str2 = "";
                myOp = operations.none;

            }
        });

    }



}