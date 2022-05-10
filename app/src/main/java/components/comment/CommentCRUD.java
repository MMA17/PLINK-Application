package components.comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import components.post.Post;

public class CommentCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Plink_database.db";
    private static final String TABLE_NAME = "comment";
    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_CREATEAT = "created_at";
    private static final String KEY_AUTHOR = "memberid";
    private static final String KEY_POSTID = "postid";

    public CommentCRUD(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_CONTENT +" TEXT, "+ KEY_CREATEAT+" TEXT, "+KEY_AUTHOR+" INTEGER, "+KEY_POSTID+" INTEGER)";
        db.execSQL(create_table);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public List<Comment> getCommentbyPostId(int id){
        List listComment = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME,null,KEY_POSTID+" = ?",new String[]{String.valueOf(id)},null,null,"created_at DESC");
        if(c == null){
            return null;
        }
        c.moveToFirst();
        while(c.isAfterLast() == false){
            Comment cmt = new Comment(c.getInt(0),c.getString(1),c.getString(2),c.getInt(4),c.getInt(3));
            listComment.add(cmt);
            c.moveToNext();
            System.out.println("Them comment" + c.isAfterLast());
        }
        return listComment;
    }
    public void QueryData (String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public boolean addComment(Comment c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_CONTENT, c.getContent());
        cv.put(KEY_AUTHOR, c.getAuthorid());
        cv.put(KEY_CREATEAT, c.getCreated_at());
        cv.put(KEY_POSTID, c.getPostid());
        long res = db.insert(TABLE_NAME, null, cv);
        if (res == -1)
            return false;
        return true;
    }
}
