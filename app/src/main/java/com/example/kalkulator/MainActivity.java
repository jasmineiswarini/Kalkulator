package com.example.kalkulator;

import static com.example.kalkulator.DBmain.KEY_KALKU;
import static com.example.kalkulator.DBmain.KEY_angka1;
import static com.example.kalkulator.DBmain.KEY_angka2;
import static com.example.kalkulator.DBmain.KEY_hasil;
import static com.example.kalkulator.DBmain.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBmain db;
    EditText e1,e2;
    TextView e3;
    RadioButton r1,r2,r3,r4;
    RadioGroup rg;
    Button b1, b2,b3, b4;
    SQLiteDatabase sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findid();
        insertdata();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                e2.setText("");
                e3.setText("");
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1.isChecked()){
                    int angka1 = Integer.parseInt(e1.getText().toString());
                    int angka2 = Integer.parseInt(e2.getText().toString());
                    int tambah = angka1 + angka2;
                    e3.setText(String.valueOf(tambah));

                }else if (r2.isChecked()){
                    int angka1 = Integer.parseInt(e1.getText().toString());
                    int angka2 = Integer.parseInt(e2.getText().toString());
                    int kurang = angka1 - angka2;
                    e3.setText(String.valueOf(kurang));
                }else if (r3.isChecked()){
                    int angka1 = Integer.parseInt(e1.getText().toString());
                    int angka2 = Integer.parseInt(e2.getText().toString());
                    double bagi = angka1 / angka2;
                    e3.setText(Double.toString(bagi));
                }else if (r4.isChecked()){
                    int angka1 = Integer.parseInt(e1.getText().toString());
                    int angka2 = Integer.parseInt(e2.getText().toString());
                    int kali = angka1 * angka2;
                    e3.setText(String.valueOf(kali));
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
    private void findid(){
        e1 = (EditText) findViewById(R.id.angka1);
        e2 = (EditText) findViewById(R.id.angka2);
        e3 = (TextView) findViewById(R.id.textViewhasil);

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        r1 = (RadioButton) findViewById(R.id.tambah);
        r2 = (RadioButton) findViewById(R.id.kurang);
        r3 = (RadioButton) findViewById(R.id.bagi);
        r4 = (RadioButton) findViewById(R.id.kali);

        b1 = (Button) findViewById(R.id.buttonclear);
        b2 = (Button) findViewById(R.id.buttonhasil);
        b3 = (Button) findViewById(R.id.buttonsimpan);
        b4 = (Button) findViewById(R.id.buttondisplay);

    }
    private void  insertdata(){
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBmain(MainActivity.this);
                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_angka1, e1.getText().toString());
                cv.put(KEY_angka2,e2.getText().toString());
                cv.put(KEY_hasil, e3.getText().toString());
                if (r1.isChecked()){
                    cv.put(KEY_KALKU, r1.getText().toString());
                }if (r2.isChecked()){
                    cv.put(KEY_KALKU, r2.getText().toString());
                }if(r3.isChecked()){
                    cv.put(KEY_KALKU,r3.getText().toString());
                }if(r4.isChecked()){
                    cv.put(KEY_KALKU,r4.getText().toString());
                }
                Long rec = sq.insert(TABLE_NAME, null,cv);
                if (rec!=null){
                    Toast.makeText(MainActivity.this, "Data inserted succesfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Display.class);
                startActivity(intent);
            }
        });
    }

}