package components.classmember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import components.file.File;
import components.classes.Class;

public class ClassMemberCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PLink.db";
    private static final String TABLE_NAME = "file";
    private static final String KEY_MEMBERID = "memberid";
    private static final String KEY_CLASSID = "classid";
    private static final String KEY_ISOWNER = "isowner";

    public ClassMemberCRUD(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_file_table ="create table " + TABLE_NAME +" (MEMBERID INTEGER NOT NULL ,CLASSID INTEGER NOT NULL,ISOWNER INTEGER DEFAULT 0 NOT NULL)";
        db.execSQL(create_file_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public List<ClassMember> getClassMemberFromClass(Class lop) {
        List<ClassMember>  classMemberList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_CLASSID + " = ?", new String[] { String.valueOf(lop.getId()) },null, null, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            ClassMember cm = new ClassMember(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            classMemberList.add(cm);
            cursor.moveToNext();
        }
        return classMemberList;
    }
    public boolean insertClassMember(ClassMember classMember) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MEMBERID,classMember.getMember().getId());
        contentValues.put(KEY_CLASSID,classMember.getLop().getId());
        contentValues.put(KEY_ISOWNER,classMember.getIsOwner());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteClassMember(ClassMember classMember) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, KEY_MEMBERID + " = ?" + KEY_CLASSID + " = ?", new String[] { String.valueOf(classMember.getMember().getId()),String.valueOf(classMember.getLop().getId()) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }

}
