package components.classmember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import components.classes.ClassCRUD;
import components.file.File;
import components.classes.Class;
import components.member.Member;

public class ClassMemberCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Plink_database.db";
    private static final String TABLE_NAME = "classmember";
    private static final String KEY_MEMBERID = "memberid";
    private static final String KEY_CLASSID = "classid";
    private static final String KEY_ISOWNER = "isowner";

    public ClassMemberCRUD(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_file_table ="create table if not exists "+ TABLE_NAME + "(MEMBERID INTEGER NOT NULL ,CLASSID INTEGER NOT NULL,ISOWNER INTEGER DEFAULT 0 NOT NULL)";
        db.execSQL(create_file_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertClassMember(ClassMember classMember) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MEMBERID,classMember.getMember().getId());
        contentValues.put(KEY_CLASSID,classMember.getLop().getId());
        contentValues.put(KEY_ISOWNER,classMember.getIsOwner());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteClassMember(ClassMember classMember) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, KEY_MEMBERID + " = ? and " + KEY_CLASSID + " = ?", new String[] { String.valueOf(classMember.getMember().getId()),String.valueOf(classMember.getLop().getId()) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }

    public List<Class> getClassbyMember(Member member, Context context){
        List<Class> classList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_MEMBERID + " = ?", new String[] { String.valueOf(member.getId()) },null, null, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            int classid = cursor.getInt(1);
            Class lop = new Class();
            lop.setId(classid);
            Cursor c = db.rawQuery("SELECT * from class where id =?",new String[]{String.valueOf(lop.getId())});
            c.moveToFirst();
            System.out.println(c.getInt(0)+" "+c.getString(1)+" "+c.getString(2));
            lop.setId(c.getInt(0));
            lop.setName(c.getString(1));
            lop.setNote(c.getString(2));
            classList.add(lop);
            cursor.moveToNext();
        }
        return classList;
    }
    public void QueryData (String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public List<Member> getMemberfromClass(Class lop){
        List<Member> mem= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_CLASSID + " = ?", new String[] { String.valueOf(lop.getId()) },null, null, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            int memid = cursor.getInt(0);
            Cursor cursor1 = db.query("member", null, "id" + "= ?", new String[]{ String .valueOf(memid)}, null, null, null );
            cursor1.moveToFirst();
            Member member = new Member(cursor1.getInt(0), cursor1.getString(1), cursor1.getString(2), cursor1.getString(3),
                    cursor1.getString(4),cursor1.getString(5),cursor1.getString(6));
            mem.add(member);
            cursor.moveToNext();
        }
        return mem;

    }
}

