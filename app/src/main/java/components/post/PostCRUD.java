package components.post;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import components.classes.Class;
import components.member.Member;
import components.member.MemberCRUD;

public class PostCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PLink.db";
    private static final String TABLE_NAME = "post";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE ="date";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_CLASS ="classid";

    public PostCRUD(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creat_file_table = "create table "+TABLE_NAME+ " ("+ KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_TITLE + " TEXT, "
                + KEY_DATE +" TEXT, "+ KEY_AUTHOR + " INTERGER, "+ KEY_CLASS + " INTEGER, "+
                " FOREIGN KEY ("+KEY_AUTHOR+") REFERENCES member(id)," +
                " FOREIGN KEY ("+KEY_CLASS+") REFERENCES class(id))" ;
        db.execSQL(creat_file_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
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
            String date = cursor.getString(2);
            int authorid = cursor.getInt(3);
            Cursor c = db.query("Member",null,"id= ?",new String[]{String.valueOf(authorid)},null,null,null);
            Member author = new Member(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
            int classid = cursor.getInt(4);
        }

        return list;
    }
}
