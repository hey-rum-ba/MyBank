package com.example.MyBank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9685744758,'Rahula Pradhan',15000.00,'Rahulrocker@gmail.com','875478548754','BARB0SAMBAL')");
        db.execSQL("insert into user_table values(5896748574,'Govinda Manekar',10500.50,'Govindameher18@gmail.com','876523451254','HDFC0BBSR')");
        db.execSQL("insert into user_table values(9865988754,'Manohara Paikrai',1400.50,'Manohara4455@gmail.com','985687548787','BARB0SAMBAL')");
        db.execSQL("insert into user_table values(9865875487,'Chandana Kumar',1500.50,'chanduloktra@gmail.com','784598655456','ICICI0SAMBAL')");
        db.execSQL("insert into user_table values(8765985487,'Sonam Gupta',12600.40,'Sonamikali1212@gmail.com','788754569865','HDFC0BBSR')");
        db.execSQL("insert into user_table values(8765659854,'Phulabani Nayak',1945.60,'phulabani89@gmail.com','326554215465','ICICI0PATIA')");
        db.execSQL("insert into user_table values(9865659854,'Sanga Bhadra Meher',5800.00,'sangameher0011@gmail.com','986565659865','HDFC0PATIA')");
        db.execSQL("insert into user_table values(8787875487,'BijaLaxmi Nayak',750.00,'Bijalaxmi@gmail.com','875656236598','BOI0SAMBAL')");
        db.execSQL("insert into user_table values(9454879894,'Rohana Lakra',5598.50,'LakraRo@yahoo.com','563232125456','HDFC0SAMBAL')");
        db.execSQL("insert into user_table values(9898656554,'Ramu Kaka',125273.80,'Ramu69@kaka.com','238545124578','HDFC0DUBAI')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
