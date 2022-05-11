package components.file;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import components.member.Member;
import components.post.Post;

public class FileCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Plink_database.db";
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

    public File getFile(File file) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(file.getId()) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        File f = new File(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        return f;
    }
    public List<File> getAllFileOfPost(Post post) {
        List<File>  fileList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + ", postfile WHERE postfile.postid = " + post.getId() + " AND postfile.fileid = file.id";

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
    public boolean insertFile(File file, Post p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Random rand = new Random();
        int ranNum = rand.nextInt(10000)+1;
        contentValues.put(KEY_ID,ranNum);
        contentValues.put(KEY_NAME,file.getName());
        contentValues.put(KEY_PATH,file.getPath());
        contentValues.put(KEY_SIZE,file.getSize());
        long result = db.insert(TABLE_NAME,null ,contentValues);

        if(result == -1)
            return false;
        else
            QueryData("INSERT INTO postfile VALUES (" + p.getId() +"," + ranNum + ")");
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
    public List<File> getFileFromListPost(List<Post> listpost){
        SQLiteDatabase db = this.getReadableDatabase();
        List<File> fileList = new ArrayList<>();
        List<Integer> listFileId = new ArrayList<Integer>();
        for (Post p:listpost){
            int id = p.getId();
            String query = "SELECT fileid from postfile WHERE postid ="+id;
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false){
                Integer fileid = cursor.getInt(0);
                System.out.println(fileid+"Nhin vao day nayyyyyyyyy");
                listFileId.add(fileid);
                cursor.moveToNext();

            }
            for(Integer i:listFileId){
                String query1 = "SELECT * FROM file where id =" + i;
                Cursor cursor1 = db.rawQuery(query1,null);
                cursor1.moveToFirst();
                while(cursor1.isAfterLast() == false) {
                    File file = new File(cursor1.getInt(0), cursor1.getString(1), cursor1.getString(2), cursor1.getInt(3));
                    fileList.add(file);
                    cursor1.moveToNext();
                }
            }
        }
    return fileList;
    }
    public boolean deleteFile(File file) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(file.getId()) });
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
