package com.example.tkb_phamthiyennhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CSDL extends AppCompatActivity {

    Button bttKhoa, bttGiangvien, bttNganh, bttLop, bttChuyende, bttTKB, bttPhancong, bttNgoaile;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdl);

        Button bttTao =(Button) findViewById(R.id.bttTao);
        Button bttXoa=(Button) findViewById(R.id.bttXoa);

        bttKhoa=(Button) findViewById(R.id.bttKhoa);

        //tao
        bttTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase database = openOrCreateDatabase("qlTKB.db", MODE_PRIVATE, null);
                    Toast.makeText(CSDL.this, "Tao CSDL thanh cong", Toast.LENGTH_SHORT).show();

                }
                catch (Exception ex){
                    Toast.makeText(CSDL.this,"Tao CSDL [KHONG] thanh cong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //xoa
        bttXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg="";
                if(deleteDatabase("qlTKB.db")==true)
                    msg+="Xoa CSDL [qlTKB] thanh cong";
                else
                    msg+="Xoa CSDL [qlTKB] khong thanh cong";
                Toast.makeText(CSDL.this,msg,Toast.LENGTH_LONG).show();
            }
        });

        //khoa
        bttKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (CSDL.this, KHOA.class);
                Bundle bundle=new Bundle();

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        /*
        //CSDL
        //KHOA
        bttKhoa.setOnClickListener(v -> {
            //Bundle bundle=new Bundle();

            String sql="CREATE TABLE KHOA (";
            sql+="MAKHOA TEXT PRIMARY KEY,";
            sql+="TENKHOA TEXT)";

            database.execSQL(sql);
        });
        bttGiangvien.setOnClickListener(v -> {
            String sql="CREATE TABLE GIANGVIEN ("+
                    "MAGV TEXT PRIMARY KEY,"+
                    "TENGV TEXT,"+
                    "PHAI TEXT,"+
                    "NGAYSINH DATA,"+
                    "DIACHI TEXT,"+
                    "MATKHAUGV TEXT,"+
                    //khoa ngoai
                    "MAKHOA TEXT NOT NULL CONSTRAINT MAKHOA "+
                    "REFERENCES KHOA(MAKHOA) ON DELETE CASCADE)";
            database.execSQL(sql);
        });
        bttNganh.setOnClickListener(v -> {
            String sql="CREATE TABLE NGANH ("+
                    "MANGANH TEXT PRIMARY KEY,"+
                    "TENNGANH TEXT,"+
                    "SOCD INTEGER,"+
                    "MAKHOA TEXT NOT NULL CONSTRAINT MAKHOA "+
                    "REFERENCES KHOA(MAKHOA) ON DELETE CASCADE)";
            database.execSQL(sql);
        });
        bttLop.setOnClickListener(v -> {
            String sql="CREATE TABLE LOP ("+
                    "MALOP TEXT PRIMARY KEY,"+
                    "TENLOP TEXT,"+
                    "TSSV INTEGER,"+
                    "NAMTUYENSINH DATA,"+
                    "MANGANH TEXT NOT NULL CONSTRAINT MANGANH "+
                    "REFERENCES NGANH(MANGANH) ON DELETE CASCADE)";
            database.execSQL(sql);
        });
        bttChuyende.setOnClickListener(v -> {
            String sql="CREATE TABLE CHUYENDE ("+
                    "MACD TEXT PRIMARY KEY,"+
                    "TENCD TEXT,"+
                    "LYTHUYET TEXT,"+
                    "THUCHANH TEXT)";
            database.execSQL(sql);
        });
        bttTKB.setOnClickListener(v -> {
            String sql="CREATE TABLE TKB ("+
                    "MALOP TEXT PRIMARY KEY,"+
                    "MAGV TEXT PRIMARY KEY,"+
                    "MACD TEXT PRIMARY KEY,"+
                    "NAMHOC DATA PRIMARY KEY,"+
                    "HOCKY INTEGER PRIMARY KEY,"+
                    "NGAY DATA PRIMARY KEY,"+
                    "BUOI TEXT)";
            database.execSQL(sql);
        });

         */
    }
}