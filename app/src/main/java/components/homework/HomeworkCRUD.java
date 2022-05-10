package components.homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import components.post.Post;
import components.classes.Class;

public class HomeworkCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Plink_database.db";
    private static final String TABLE_NAME = "excersie";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DEADLINE = "deadline";
    private static final String KEY_CLASSID = "classid";

    public HomeworkCRUD(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Create table");
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " title TEXT, content TEXT, deadline DATE, classid INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertHomework(Homework homework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, homework.getTitle());
        cv.put(KEY_CONTENT, homework.getContent());
        cv.put(KEY_DEADLINE, homework.getDeadline());
        cv.put(KEY_CLASSID, homework.getClassid());
        long res = db.insert(TABLE_NAME, null, cv);
        if (res == -1)
            return false;
        return true;
    }

    public List<Homework> getAll(Homework baitap){
        List<Homework> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * from HOMEWORK, Class where Homework.classid = Class.id and Homework.classid="+baitap.getId()+" order by date Desc";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String deadline = cursor.getString(3);
            int classid = cursor.getInt(4);
            Homework h = new Homework(id,title,content,deadline,classid);
            list.add(h);
        }
        return list;
    }
    public List<Homework> getHomeworkByClass(Class c){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Homework> listHomework = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,KEY_CLASSID +" = ?",
                new String[] { String.valueOf(c.getId()) },null,null,null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            Homework h = new Homework(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            listHomework.add(h);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return listHomework;
    }
    public boolean updateHomework(Homework homework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, homework.getTitle());
        cv.put(KEY_CONTENT, homework.getContent());
        cv.put(KEY_DEADLINE, homework.getDeadline());
        cv.put(KEY_CLASSID, homework.getClassid());
        long res = db.update(TABLE_NAME, cv, KEY_ID + " = ?", new String[] {String.valueOf(homework.getId())});
        if (res == 0)
            return false;
        return true;
    }
    public boolean deleteHomework(Homework homework) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] {String.valueOf(homework.getId())});
        if (res == 0)
            return false;
        return true;
    }
}
