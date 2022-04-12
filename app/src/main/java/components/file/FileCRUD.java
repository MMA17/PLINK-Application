package components.file;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import components.member.Member;

public class FileCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PLink.db";
    private static final String TABLE_NAME = "file";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PATH = "path";
    private static final String KEY_SIZE = "size";

    public FileCRUD(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    public void onCreate(SQLiteDatabase db){
        String create_file_table ="create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY,NAME TEXT,PATH TEXT,PASSWORD TEXT, SIZE INTEGER)";
        db.execSQL(create_file_table);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public File getFile(int fileId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(fileId) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        File file = new File(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        return file;
    }
    public List<File> getAllFile() {
        List<File>  fileList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            File file = new File(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            fileList.add(file);
            cursor.moveToNext();
        }
        return fileList;
    }
    public boolean insertFile(File file) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID,file.getId());
        contentValues.put(KEY_NAME,file.getName());
        contentValues.put(KEY_PATH,file.getPath());
        contentValues.put(KEY_SIZE,file.getSize());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updateFile(File file) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, file.getName());
        values.put(KEY_PATH, file.getPath());
        values.put(KEY_SIZE, file.getSize());
        long res = db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(file.getId()) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }

    public boolean deleteFile(int fileId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(fileId) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }
}
