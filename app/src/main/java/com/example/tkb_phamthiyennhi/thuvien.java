package com.example.TKB_PHAMTHIYENNHI;
//Tran khang vuong
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//thu02
public class sinhvien extends AppCompatActivity {
//TEXT THUVIEN
    EditText txtshow;
    String NameDTB="SINHVIEN.db";
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien);

        Button CreateTB=(Button) findViewById(R.id.btnCRTB);
        Button CreateTBSV=(Button) findViewById(R.id.btnCRTsinhvien);
        Button InsertTB=(Button) findViewById(R.id.btnInsertTB);
        Button UpdateTB=(Button) findViewById(R.id.btnUpdateTB);
        Button DeleteTB=(Button) findViewById(R.id.btnDeleteTB);
        Button HienthiTB=(Button) findViewById(R.id.btnhienthiTB);
        EditText txtmalopTB=(EditText) findViewById(R.id.txtlop);
        EditText txthotenTB=(EditText) findViewById(R.id.txttensinhvien);
        EditText txtSdtTB=(EditText) findViewById(R.id.txtSDT);

        txtshow=(EditText) findViewById(R.id.txtshow);

        CreateTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCreate(NameDTB);
            }
        });
        DeleteTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDeleteDB(NameDTB);
            }
        });
        CreateTBSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sqlTable="CREATE TABLE TBSINHVIEN (MALOP TEXT PRIMARY KEY, HOTEN TEXT , SDT TEXT)";
                doCreateTable(sqlTable);
            }
        });
        InsertTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String malop=txtmalopTB.getText().toString();
                String hotensv=txthotenTB.getText().toString();
                String sdtsv=txtSdtTB.getText().toString();
                String sql="INSERT INTO TBSINHVIEN VALUES('"+malop+"','"+hotensv+"','"+sdtsv+"')";
                if (doAction(sql)==true)
                {
                    Toast.makeText(sinhvien.this,"them thanh cong",Toast.LENGTH_LONG).show();
                    txtmalopTB.setText("");
                    txthotenTB.setText("");
                    txtSdtTB.setText("");
                    txtmalopTB.findFocus();
                }
                else
                {
                    Toast.makeText(sinhvien.this,"them khong thanh cong",Toast.LENGTH_LONG).show();
                }

            }
        });
        UpdateTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql="UPDATE TBSINHVIEN SET MALOP='"+txtmalopTB+"',HOTEN='"+txthotenTB+"' WHERE MALOP='"+txtmalopTB+"'";
                if (doAction(sql)==true)
                {
                    Toast.makeText(sinhvien.this,"sua thanh cong",Toast.LENGTH_LONG).show();
                    txtmalopTB.setText("");
                    txthotenTB.setText("");
                    txtSdtTB.setText("");
                    txtmalopTB.findFocus();
                }
                else
                {
                    Toast.makeText(sinhvien.this,"sua khong thanh cong",Toast.LENGTH_LONG).show();
                }
            }
        });
        HienthiTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienthi();
            }
        });

    }
    public void doCreate(String NAMEDTB)
    {
        try {
            database=openOrCreateDatabase(NAMEDTB,MODE_PRIVATE,null);
            Toast.makeText(this,"Tao CSDL thanh cong",Toast.LENGTH_LONG).show();
        }
        catch (Exception exception)
        {
            Toast.makeText(this,"Tao CSDL [KHONG] thanh cong",Toast.LENGTH_LONG).show();
        }
    }
    public void doDeleteDB(String NAMEDTB)
    {
        try {
            if(deleteDatabase(NAMEDTB)==true) {
                Toast.makeText(this, "Xoa CSDL thanh cong", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(this,"Xoa CSDL [KHONG] thanh cong",Toast.LENGTH_LONG).show();
        }
    }
    public boolean doAction(String sqlTable)
    {
        try {
            database=openOrCreateDatabase(NameDTB,MODE_PRIVATE,null);
            database.execSQL(sqlTable);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
        finally {
            database.close();
        }
    }
    public void doUpdate(String NAMEDTB)
    {
        try {
            database.execSQL(NAMEDTB);
            Toast.makeText(this,"update thanh cong",Toast.LENGTH_LONG).show();
        }
        catch (Exception exception)
        {
            Toast.makeText(this,"update [KHONG] thanh cong",Toast.LENGTH_LONG).show();
        }
    }
    public void doCreateTable(String sqlTable)
    {
        try {
            database.execSQL(sqlTable);
            Toast.makeText(this,"Tao Table thanh cong",Toast.LENGTH_LONG).show();
        }
        catch (Exception exception)
        {
            Toast.makeText(this,"Tao Table [KHONG] thanh cong",Toast.LENGTH_LONG).show();
        }
    }
    public void hienthi()
    {
        database=openOrCreateDatabase(NameDTB,MODE_PRIVATE,null);
        String sql="SELECT * FROM TBSINHVIEN";
        Cursor cursor=database.rawQuery(sql,null);
        String msg="";
        if(cursor.moveToFirst()) {
            do {
                msg += cursor.getString(0) + "-";
                msg += cursor.getString(1) + "-";
                msg += cursor.getString(2) + "\n";

            } while (cursor.moveToNext());
        }
        txtshow.setText(msg);

        database.close();

    }

}
