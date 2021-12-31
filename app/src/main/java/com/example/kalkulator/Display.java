package com.example.kalkulator;

import static com.example.kalkulator.DBmain.TABLE_NAME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Display extends AppCompatActivity {
    DBmain db;
    SQLiteDatabase sq;
    ListView listView;
    String[] angka1;
    String[] angka2;
    String[] hasil;
    String[] operasi;
    String[] daftar;
    LinearLayout ly;
    int[] id;
    ArrayList<Model> modelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        findid();
        display();
        dialog();


    }

    private void display(){
        db = new DBmain(this);
        sq = db.getReadableDatabase();
        Cursor cursor = sq.rawQuery("select *from "+TABLE_NAME+"", null );
        if (cursor.getCount()>0){
            id = new  int[cursor.getCount()];
            angka1 = new String[cursor.getCount()];
            angka2 = new String[cursor.getCount()];
            hasil = new String[cursor.getCount()];
            operasi = new String[cursor.getCount()];
            int i=0;
            while (cursor.moveToNext()){
                id[i] = cursor.getInt(0);
                angka1[i] = cursor.getString(3);
                angka2[i] = cursor.getString(4);
                hasil[i] = cursor.getString(2);
                operasi[i] = cursor.getString(1);
                i++;
            }
            Custom custom = new Custom();
            listView.setAdapter(custom);

        }
        listView.setSelected(true);


    }

    private void findid(){
        listView=(ListView) findViewById(R.id.lv);

    }

    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return angka1.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t1,t2,t3,t4;
            convertView= LayoutInflater.from(Display.this).inflate(R.layout.data, parent, false);
            t1 = convertView.findViewById(R.id.satu);
            t1.setText(angka1[position]);

            t2 = convertView.findViewById(R.id.arit);
            t2.setText(operasi[position]);

            t3 = convertView.findViewById(R.id.dua);
            t3.setText(angka2[position]);

            t4 = convertView.findViewById(R.id.sama);
            t4.setText(hasil[position]);
            return convertView;
        }

    }
    private void dialog(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftar[position];
                final CharSequence[] pilih = {"Ya", "Tidak"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Display.this);
                dialog.setTitle("HAPUS");
                dialog.setItems(pilih, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 :
                                db.getWritableDatabase();
                                sq.execSQL("DELETE FROM course WHERE angka1'"+selection+"'");
                                db.getReadableDatabase();
                                break;
                            case 1:
                                display();
                        }
                    }
                });
            }


    });
    }


}