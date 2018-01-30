package Old;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Colin on 20/01/2018.
 */

public class UserNameDatabase extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "findafilm.db";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "password";

    private static final String TABLE_CREATE = "CREATE TABLE " +

            TABLE_NAME + "(" +

            //COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL , " +
            //COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +

            COLUMN_USERNAME + " TEXT NOT NULL, " +
            COLUMN_EMAIL + " TEXT NOT NULL, " +
            COLUMN_PASS + " TEXT NOT NULL " +

            ");";

    public UserNameDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Creating table", "Table created");
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(UserName c)
    {
        Log.d("Insert Contact " , "user "+ c.getUsername() + " pass " + c.getPassword());

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASS, c.getPassword());
        //values.put(COLUMN_EMAIL, c.getEmail());

        db.insert(TABLE_NAME, null, values);
    }

    //Search database to check if password matches what is stored in database
    public String searchPass(String username)
    {
        Log.d("Search user db", "Searching db for username " + username);
        db = this.getReadableDatabase();
        String query = "SELECT username, password from " + TABLE_NAME;


        Cursor cursor = db.rawQuery(query,null);
        Log.d("Query results", cursor.toString());

        String a, b;
        b = "Not found";
        if(cursor.moveToFirst())
        {
            do
            {
                a = cursor.getString(0);
                b = cursor.getString(1);
                if(a.equals(username))
                {
                    Log.d("Search user db", "Found username " + a + " and password " + b);
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
