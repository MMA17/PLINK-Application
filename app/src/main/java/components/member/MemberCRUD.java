package components.member;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.core.view.accessibility.AccessibilityViewCommand;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MemberCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PLink.db";
    private static final String TABLE_NAME = "member";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ROLE = "role";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DOB = "dob";

    public MemberCRUD(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table ="CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT,PHONE TEXT,PASSWORD TEXT, ROLE TEXT, DOB DATE)";
        db.execSQL(create_students_table);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(KEY_ID,member.getId());
        contentValues.put(KEY_NAME,member.getName());
        contentValues.put(KEY_PHONE,member.getPhone());
        contentValues.put(KEY_ROLE,member.getRole());
        contentValues.put(KEY_PASSWORD,member.getPassword());
        contentValues.put(KEY_DOB, member.getDOB());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        db.close();
        if(result == -1)
            return false;
        else
            return true;

    }

    public Member getMember(Member mem) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?",
                new String[] { String.valueOf(mem.getId()) },null, null, null);
        if(cursor == null){
            return null;
        }
            cursor.moveToFirst();
        Member member = new Member(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        cursor.close();
        db.close();
        return member;
    }

    public Member getMemberbyPhone(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_NAME,null,"Phone = ?",new String[]{String.valueOf(phone)},null,null,null);
        Cursor cursor = db.rawQuery("SELECT * From "+TABLE_NAME+" WHERE Phone = "+phone,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Member member = new Member(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            cursor.close();
            db.close();
            return member;
        }
        else{
            cursor.close();
            db.close();
            return null;
        }
        //Member member = new Member(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));


    }
    public List<Member> getAllMembers() {
        List<Member>  memberList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Member member = new Member(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            memberList.add(member);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return memberList;
    }

    public boolean updateMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, member.getName());
        values.put(KEY_PHONE, member.getPhone());
        values.put(KEY_ROLE, member.getRole());
        values.put(KEY_PASSWORD, member.getPassword());
        values.put(KEY_DOB, String.valueOf(member.getDOB()));
        long res = db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(member.getId()) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }

    public boolean deleteMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(member.getId()) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }

    public boolean checkLogin(Member member){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+ TABLE_NAME + " where "+KEY_PHONE+" = "+member.getPhone()+" AND "+KEY_PASSWORD+" = "+member.getPassword();

        Cursor cursor = db.rawQuery(query,null);
        cursor = db.query(TABLE_NAME,null,"Phone =? AND Password=?",
                new String[]{member.getPhone(),member.getPassword()},null,null,null);
        if(cursor.getCount() <=0 ){
            //khong co ban ghi

            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public Member getMemberbyId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(id) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Member member = new Member(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        cursor.close();
        db.close();
        return member;
    }

}
