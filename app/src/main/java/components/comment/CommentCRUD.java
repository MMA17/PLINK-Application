package components.comment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommentCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PLink.db";
    private static final String TABLE_NAME = "comment";
    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_CREATEAT = "create_at";
    private static final String KEY_AUTHOR = "authorid";
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

    public List<Comment> getCommentbyAuthorId(int id){
        List listComment = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME,null,KEY_AUTHOR+" = ?",new String[]{String.valueOf(id)},null,null,"create_at DES");
        if(c == null){
            return null;
        }
        c.moveToFirst();
        while(c.isAfterLast() == false){
            Comment cmt = new Comment(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getInt(4));
            listComment.add(cmt);
        }
        return listComment;
    }
}
