package components.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import components.classmember.ClassMember;
import components.file.File;
import components.member.Member;
import components.post.Post;

public class ClassCRUD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Plink_database.db";
    private static final String TABLE_NAME = "class";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NOTE = "note";

    public ClassCRUD(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_file_table ="create table" + TABLE_NAME +" (ID INTEGER PRIMARY KEY,NAME TEXT,NOTE TEXT)";
        db.execSQL(create_file_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public Class getClass(Class lopp) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(lopp.getId()) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Class lop = new Class(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return lop;
    }


    public List<Class> getAllClasses() {
        List<Class>  classList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Class lop = new Class(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            classList.add(lop);
            cursor.moveToNext();
        }
        return classList;
    }
    public List<Class> getALlClassMemberNotIn(Member member){
        //Lọc những lớp mà member ở trong
        List<Class> classListMemberIn = new ArrayList<>();
        String query = "Select class.id, class.name, class.note from class inner join Classmember on class.id = classmember.classid where classmember.memberid = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(member.getId())});
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            Class lop = new Class();
            lop.setId(cursor.getInt(0));
            lop.setName(cursor.getString(1));
            lop.setNote(cursor.getString(2));
            classListMemberIn.add(lop);
            cursor.moveToNext();
        }
        //lọc ra những lớp nó ko ở trong:
        List<Class> classList = new ArrayList<>(getAllClasses());
        System.out.println(classList.size()+ " classlist");
        boolean index [] = new boolean[classList.size()];
        for(int i = 0; i< classList.size(); i++){
            index[i] = true;
        }

        for (int i = 0; i< classList.size(); i++){
            for (int j =0; j <classListMemberIn.size(); j++){
                if(classList.get(i).getId() == classListMemberIn.get(j).getId()){
                    index[i] = false;
                }
            }
        }
//        for (boolean i : index){
//            System.out.println(i + "dau buoiiiiii");
//        }
        List<Class> res = new ArrayList<>();
        for (int i = 0; i <index.length; i++){
            if (index[i]){
                res.add(classList.get(i));
            }
        }
        return res;

    }
    public Class insertClass(Class lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(KEY_ID,lop.getId());
        contentValues.put(KEY_NAME,lop.getName());
        contentValues.put(KEY_NOTE,lop.getNote());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return null;
        else{
            Cursor cursor = db.query(TABLE_NAME, null, KEY_NAME + " = ?", new String[] { String.valueOf(lop.getName()) },null, null, null);
            if(cursor != null)
                cursor.moveToFirst();
            Class lopp = new Class(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            return lopp;
        }

    }

    public boolean updateClass(Class lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, lop.getName());
        values.put(KEY_NOTE, lop.getNote());
        long res = db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(lop.getId()) });
        db.close();
        if (res == 0 ){
            return false;
        }
        return true;
    }

    public boolean deleteClass(Class lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(lop.getId()) });
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
