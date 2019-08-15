package id.go.pajak.fiskusapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    Cursor cursor;


    private static final String DATABASE_NAME = "fiskusapp.db";
    private static final String DATABASE_PATH = "/data/data/id.go.pajak.fiskusapp/databases/";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUser = "create table user (id integer primary key, " +
                "nip9 text not null, pwd text not null, " +
                "nip19 text not null, nama text not null, " +
                "unitkerja text not null, jabatan text not null)";
        db.execSQL(sqlUser);

        String sqlToken = "create table token (id integer primary key, " +
                "user text not null, token text not null, " +
                "created datetime not null, expired datetime not null)";
        db.execSQL(sqlToken);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS token");

        onCreate(db);
    }

    public String cekDbPresent() {
        String checkFlag ="Ada";
        try{
            SQLiteDatabase.openDatabase(DATABASE_PATH+DATABASE_NAME, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
        catch(SQLiteException sqlException){

            checkFlag="Tidak";
        }

        return checkFlag;

    }

    public Boolean cekTabels() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlUser = "SELECT * FROM user";
        cursor = db.rawQuery(sqlUser,null);
        if(cursor.getCount() >0){
            return true;
        }else{
            return false;
        }
    }



    public void cekLogin() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlUser = "SELECT * FROM token WHERE created < NOW()  AND expired < NOW() ";
        cursor = db.rawQuery(sqlUser,null);
        if(cursor.getCount() >0){

        };
    }

    public void createTables() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlUser = "create table user (id integer primary key, " +
                            "nip9 text not null, pwd text not null, " +
                            "nip19 text not null, nama text not null, " +
                            "unitkerja text not null, jabatan text not null)";
        db.execSQL(sqlUser);

        String sqlToken = "create table token (id integer primary key, " +
                            "user text not null, token text not null, " +
                            "created datetime not null, expired datetime not null)";
        db.execSQL(sqlToken);

    }

    public void insertTableUser(String nip9,String pwd,
                                String nip19,String nama,
                                String unitkerja,String jabatan) {

        SQLiteDatabase db =this.getWritableDatabase();

        String sql = "insert into `user` (id," +
                            "nip9,pwd," +
                            "nip19,nama," +
                            "unitkerja,jabatan)" +
                            "values('1','"+
                            nip9+"','"+pwd+"','"+
                            nip19+"','"+nama+"','"+
                            unitkerja+"','"+jabatan+"')";
        db.execSQL(sql);

        db.close();
    }

    public void insertTableToken(String nip9,String token,
                                String created,String expired) {

        SQLiteDatabase db =this.getWritableDatabase();

        String sql = "insert into `token` (id," +
                "user,token," +
                "created,expired) " +
                "values ('1','"+
                nip9+"','"+token+"','"+
                created+"','"+expired+"')";
        db.execSQL(sql);

        db.close();
    }


}
