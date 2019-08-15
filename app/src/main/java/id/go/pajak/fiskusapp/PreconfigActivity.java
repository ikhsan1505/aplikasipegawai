package id.go.pajak.fiskusapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PreconfigActivity extends AppCompatActivity {
    private Integer timer = 3000;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preconfig);


        DBHelper dbHelper = new DBHelper(this);

        dbHelper.insertTableUser("060105300", "alisyahbani",
                                    "198405152003121004", "Ikhsan Alisyahbani",
                                    "Direktorat Teknologi Informasi dan Komunikasi", "PRAKOM");

        dbHelper.insertTableToken("060105300","tokengue","2019-08-14 20:02:22","2019-08-14 20:02:22");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent gotoMain =  new Intent(PreconfigActivity.this,MainActivity.class);
                startActivity(gotoMain);
                finish();
            }
        },timer);
    }
}
