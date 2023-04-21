package com.example.tkb_phamthiyennhi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class KHOA extends AppCompatActivity {

    EditText editMaKhoa, editTenKhoa;
    Button bttThem, bttCapnhat, bttXoa, bttHienthi;
    ListView listHienthi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa);
//GOI
        editMaKhoa = (EditText) findViewById(R.id.editMaKhoa);
        editTenKhoa = (EditText) findViewById(R.id.editTenKhoa);

        listHienthi=(ListView)findViewById(R.id.listHienthi);

        bttThem = (Button) findViewById(R.id.bttThem);
        bttCapnhat = (Button) findViewById(R.id.bttCapnhat);
        bttXoa = (Button) findViewById(R.id.bttXoa);
        bttHienthi = (Button) findViewById(R.id.bttHienthi);
//THEM
        bttThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themkhoa();
            }
        });

        bttCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capnhatkhoa();
            }
        });
        bttXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoakhoa();
            }
        });
        bttHienthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienthikhoa();
            }
        });
    }

    //them
    public void themkhoa() {
        //lay dau vao
        String makhoa = editMaKhoa.getText().toString();
        String tenkhoa = editTenKhoa.getText().toString();
        //chuoi sql
        String sql = "Insert into KHOA(MAKHOA text primary key,TENKHOA text) ";
        sql += "Values('" + makhoa + "','" + tenkhoa + "')";
        if (thuvien.doAction(sql) == true) {
            Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            editMaKhoa.setText("");
            editTenKhoa.setText("");
            editMaKhoa.findFocus();
        } else
            Toast.makeText(this, "Them ['KHOA' KHONG] thanh cong", Toast.LENGTH_SHORT).show();
    }

    //cap nhat
    public void capnhatkhoa() {
        //lay dau vao
        String makhoa = editMaKhoa.getText().toString();
        String tenkhoa = editTenKhoa.getText().toString();
        //chuoi sql
        String sql = "Update KHOA set TENKHOA='" + tenkhoa + "' Where MAKHOA='" + makhoa + "'";
        if (thuvien.doAction(sql) == true) {
            Toast.makeText(this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
            editMaKhoa.setText("");
            editTenKhoa.setText("");
            editMaKhoa.findFocus();
        } else
            Toast.makeText(this, "Sua ['KHOA' KHONG] thanh cong", Toast.LENGTH_SHORT).show();
    }

    //xoa
    public void xoakhoa() {
        //lay dau vao
        String makhoa = editMaKhoa.getText().toString();
        //chuoi sql
        String sql = "Delete from KHOA Where MAKHOA='" + makhoa + "'";
        if (thuvien.doAction(sql) == true) {
            Toast.makeText(this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            editMaKhoa.setText("");
            editMaKhoa.findFocus();
        } else
            Toast.makeText(this, "Xoa ['KHOA' KHONG] thanh cong", Toast.LENGTH_SHORT).show();
    }

    public void hienthikhoa(){
        ArrayList<String> list=new ArrayList<String>();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listHienthi.setAdapter(adapter);
        SQLiteDatabase database=null;
        String sql="Select*From KHOA Order by TENKHOA";
        String msg="";
        try{
            database=openOrCreateDatabase("qlKhoaCNTT",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor.moveToFirst()){
                do{
                    msg+=cursor.getString(0)+"-";
                    msg+=cursor.getString(1)+"-";
                }
                while (cursor.moveToFirst());
            }
        }
        catch (Exception exception){
            Toast.makeText(this,"Khong co du lieu",Toast.LENGTH_LONG).show();
        }
        finally {
            database.close();
        }
         listHienthi.setFilterText(msg);
    }
}