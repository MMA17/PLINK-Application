package components.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import components.file.File;

public class ClassCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Plink_database.db";
    private static final String TABLE_NAME = "class";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NOTE = "note";

    public ClassCRUD(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_file_table ="create table" + TABLE_NAME +" (ID INTEGER PRIMARY KEY,NAME TEXT,NOTE TEXT)";
        db.execSQL(create_file_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public Class getClass(Class lopp) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(lopp.getId()) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Class lop = new Class(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return lop;
    }


    public List<Class> getAllClasses() {
        List<Class>  classList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Class lop = new Class(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            classList.add(lop);
            cursor.moveToNext();
        }
        return classList;
    }
    public boolean insertClass(Class lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID,lop.getId());
        contentValues.put(KEY_NAME,lop.getName());
        contentValues.put(KEY_NOTE,lop.getNote());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updateClass(Class lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, lop.getName());
        values.put(KEY_NOTE, lop.getNote());
        long res = db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(lop.getId()) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }

    public boolean deleteClass(Class lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(lop.getId()) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }
    public void QueryData (String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
}
