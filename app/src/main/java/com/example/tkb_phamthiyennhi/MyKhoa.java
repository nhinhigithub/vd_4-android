package com.example.tkb_phamthiyennhi;

public class MyKhoa {
    String makhoa;
    String tenkhoa;

    //ham khoi tao
    public  MyKhoa(String makhoa, String tenkhoa){
        this.makhoa=makhoa;
        this.tenkhoa=tenkhoa;
    }

    //thu tuc hien thi du lieu
    public String toString(){
        String msg="";
        msg+="Mã khoa: "+ this.makhoa + "\n";
        msg+="Tên khoa: "+ this.tenkhoa;
        return msg;
    }
}
