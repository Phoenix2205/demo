package com.example.sony.maytinh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button back, clear;
    Button seven,eight,nine, div;
    Button four, five, six, mul;
    Button one, two, three, minus;
    Button point,zero,equal, plus;
    EditText display;
    String operand="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        point = (Button)findViewById(R.id.btn_point);
        back=(Button)findViewById(R.id.btn_back);
        clear=(Button)findViewById(R.id.btn_clear);

        zero = (Button)findViewById(R.id.btn_0);
        one = (Button)findViewById(R.id.btn_1);
        two = (Button)findViewById(R.id.btn_2);
        three = (Button)findViewById(R.id.btn_3);
        four = (Button)findViewById(R.id.btn_4);
        five = (Button)findViewById(R.id.btn_5);
        six = (Button)findViewById(R.id.btn_6);
        seven = (Button)findViewById(R.id.btn_7);
        eight = (Button)findViewById(R.id.btn_8);
        nine = (Button)findViewById(R.id.btn_9);

        div = (Button)findViewById(R.id.btn_div);
        mul = (Button)findViewById(R.id.btn_mul);
        plus = (Button)findViewById(R.id.btn_plus);
        minus = (Button)findViewById(R.id.btn_min);

        display=(EditText)findViewById(R.id.et_display);

    }
     @Override
    public void onClick(View view)
     {
        switch (view.getId())
        {
            case R.id.btn_clear:
            {
                clearScreen();
                break;
            }

            case R.id.btn_back:
            {
                deleteCharacter();
                displayResult();
                break;
            }

            case R.id.btn_div:
            {
                operand+=div.getText().toString();
                displayResult();
                break;
            }

            case R.id.btn_mul:
            {
                operand+=mul.getText().toString();
                displayResult();
                break;
            }

            case R.id.btn_plus:
            {
                operand+=plus.getText().toString();
                displayResult();
                break;
            }

            case R.id.btn_min:
            {
                operand+=minus.getText().toString();
                displayResult();
                break;
            }

            case R.id.btn_equal:
            {
                calculate();
                break;
            }

            default:
            {
                operand+=((Button)view).getText().toString();
                displayResult();
                break;
            }
        }
     }

    public void deleteCharacter()
    {
        int length= display.getText().length();
        if (length>0)
        {
            display.getText().delete(length-1,length);
            operand=display.getText().toString();
        }
    }

    public void clearScreen()
    {
        display.setText("");
        operand="";
    }

    public void displayResult()
    {
        display.setText(operand);
        int textLength = display.getText().length();
        display.setSelection(textLength, textLength);
    }


    public void calculate()
    {
        if(operand.length()==0) displayResult();
        else
        {
            Cal.TaoBieuThuc(operand);
            Cal.TachChuoi();
            Cal.HauTo();
            double kq= Cal.TinhBieuThuc();
            display.setText("\n"+Double.toString(kq));
        }
    }

}
