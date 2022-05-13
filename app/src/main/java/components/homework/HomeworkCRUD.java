package components.homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import components.member.Member;
import components.post.Post;
import components.classes.Class;

public class HomeworkCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Plink_database.db";
    private static final String TABLE_NAME = "excersie";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DEADLINE = "deadline";
    private static final String KEY_CREATEAT = "created_at";
    private static final String KEY_CLASSID = "classid";

    public HomeworkCRUD(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Create table");
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " title TEXT, content TEXT, deadline DATE,created_at DATE, classid INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public Homework insertHomework(Homework homework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, homework.getTitle());
        cv.put(KEY_CONTENT, homework.getContent());
        cv.put(KEY_DEADLINE, homework.getDeadline());
        cv.put(KEY_CREATEAT, homework.getCreate_at());
        cv.put(KEY_CLASSID, homework.getClassid());
        long res = db.insert(TABLE_NAME, null, cv);

        db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT * FROM excersie WHERE " + KEY_CREATEAT + "= '" + homework.getCreate_at() + "'", null);
        Homework h = new Homework();
        while(cursor1!=null && cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String title = cursor1.getString(1);
            String content = cursor1.getString(2);
            String deadline = cursor1.getString(3);
            int classid = cursor1.getInt(4);
            String createat = cursor1.getString(5);
            h = new Homework(id,title,content,deadline,classid,createat);
        }
        if (res == -1)
            return null;
        return h;
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
            String createdat = cursor.getString(5);
            Homework h = new Homework(id,title,content,deadline,classid,createdat);
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
            Homework h = new Homework(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5));
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
    public Member getAuthorHomework(Homework homework){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from member,classmember where member.id = classmember.memberid " +
                "and classid =? and isOwner=?",new String[]{String.valueOf(homework.getClassid()),String.valueOf(1)});
        c.moveToFirst();
        if(c !=null){
            Member author = new Member(c.getInt(0),c.getString(1),c.getString(2)
            ,c.getString(3),c.getString(4),c.getString(5), c.getString(6));
            return author;
        }
        return null;
    }
}
