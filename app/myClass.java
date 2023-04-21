public class myClass {
    String makhoa, tenkhoa;

    public void myClass(String makhoa, String tenkhoa){
        this.makhoa=makhoa;
        this.tenkhoa=tenkhoa;
    }
    public String toString(){
        String msg="";
        msg+="Mã khoa: "+this.makhoa+"\n";
        msg+="Tên khoa: "+this.tenkhoa;
        return msg;
    }
}
