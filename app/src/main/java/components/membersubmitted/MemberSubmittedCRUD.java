package components.membersubmitted;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import components.member.Member;

public class MemberSubmittedCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Plink_database.db";
    private static final String TABLE_NAME = "membersubmitted";
    private static final String KEY_MEMBERID = "memberid";
    private static final String KEY_EXCERCISEID = "excersieid";
    private static final String KEY_FILEID = "fileid";
    private static final String KEY_TIME = "timesubmit";

    public MemberSubmittedCRUD(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (MEMBERID INTEGER ," +
                "EXCERCISEID INTEGER,TIMESUBMITTED TEXT, FILEID INTEGER," +
                "FOREIGN KEY(MEMBERID) REFERENCES member(id)," +
                "FOREIGN KEY(EXCERCISEID) REFERENCES excersie(id)," +
                "FOREIGN KEY (FILEID) REFERENCES file(id))";
        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertSubmit(MemberSubmitted ms){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MEMBERID, ms.getMemberid());
        contentValues.put(KEY_EXCERCISEID, ms.getExcerciseid());
        contentValues.put(KEY_FILEID, ms.getFileid());
        contentValues.put(KEY_TIME,ms.getTimesubmit());

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean checkSubmit(MemberSubmitted ms){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from membersubmitted where memberid=? and excersieid=?",
                new String[]{String.valueOf(ms.getMemberid()),String.valueOf(ms.getExcerciseid())});
        c.moveToFirst();
        if(c.getCount() > 0){
            //đã nộp bài

            return true;
        }
        return false;
    }
    public MemberSubmitted getMemberSumit(MemberSubmitted ms){
        //Sau khi kiểm tra học sinh đã nộp bài bằng memID và excerciseID thì sẽ get Timesubmit

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from membersubmitted where memberid=? and excersieid=?",
                new String[]{String.valueOf(ms.getMemberid()),String.valueOf(ms.getExcerciseid())});
        c.moveToFirst();
        ms.setTimesubmit(c.getString(3));
        ms.setFileid(c.getInt(2));
        return ms;
    }
    public void checkDeadline(MemberSubmitted ms, String deadline){
        String timesubmit = ms.getTimesubmit();
        //DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy/MM/dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Checkdeadline");
        try {
            System.out.println("Timesub " +timesubmit);
            System.out.println(sdf.parse(deadline).before(sdf.parse(timesubmit)));
        } catch (ParseException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
