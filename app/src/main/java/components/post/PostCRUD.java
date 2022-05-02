package components.post;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import components.classes.Class;
import components.member.Member;
import components.member.MemberCRUD;
import components.post.Post;

public class PostCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PLink.db";
    private static final String TABLE_NAME = "post";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_CREATEAT = "create_at";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_CLASSID = "classid";

    public PostCRUD(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + DATABASE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " title TEXT, content TEXT, create_at DATE, author INTEGER, classid INTEGER," +
                " FOREIGN KEY(author) REFERENCES member(id)," +
                " FOREIGN KEY(classid) REFERENCES class(id) )";
        db.execSQL(createTable);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, post.getTitle());
        cv.put(KEY_CONTENT, post.getContent()); 
        cv.put(KEY_CREATEAT, post.getCreate_at());
        cv.put(KEY_AUTHOR, post.getAuthor());
        cv.put(KEY_CLASSID, post.getClassid());
        long res = db.insert(TABLE_NAME, null, cv);
        if (res == -1)
            return false;
        return true;
    }

    public List<Post> getAll(Class lop){
        List<Post> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * from POST, Class where Post.classid = Class.id and Post.classid="+lop.getId()+" order by date Desc";
        //Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,"date DESC");
        Cursor cursor = db.rawQuery(query,null);
        while(cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String date = cursor.getString(3);
            int authorid = cursor.getInt(4);
//            Cursor c = db.query("Member",null,"id= ?",new String[]{String.valueOf(authorid)},null,null,null);
//            Member author = new Member(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
            int classid = cursor.getInt(5);
            Post p = new Post(id,title,content,date,authorid,classid);
            list.add(p);
        }

        return list;
    }
    public Post getPostByClassID(Post post) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null, KEY_CLASSID +" = ?",
                new String[] { String.valueOf(post.getClassid()) }, null, null ,null );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Post p = new Post(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
        cursor.close();
        db.close();
        return p;
    }

    public boolean updatePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, post.getTitle());
        cv.put(KEY_CONTENT, post.getContent());
        cv.put(KEY_CREATEAT, post.getCreate_at());
        cv.put(KEY_AUTHOR, post.getAuthor());
        cv.put(KEY_CLASSID, post.getClassid());
        long res = db.update(TABLE_NAME, cv, KEY_ID + " = ?", new String[] {String.valueOf(post.getId())});
        if (res == 0)
            return false;
        return true;
    }

    public boolean deletePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] {String.valueOf(post.getId())});
        if (res == 0)
            return false;
        return true;
    }
}

