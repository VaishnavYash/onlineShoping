package yash.com.example.majorquizapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseTable(context: Context){

    private val databaseOpenHelper: DatabaseOpenHelper
    init {
        databaseOpenHelper = DatabaseOpenHelper(context)
    }

    private class DatabaseOpenHelper internal constructor(private val helperContext: Context):
        SQLiteOpenHelper(helperContext, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            val query = ("CREATE TABLE "+ TABLE_USER +" ("+
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT," +
                    COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT" + ")")
            db.execSQL(query)
        }

        override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
            Log.w(
                "DictionaryDatabase",
                "Upgrading database from version $p1 to $p2 , which will " +
                        "destroy all old data"
            )
            db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
            onCreate(db)
        }

    }

    fun checkEmail(userEmail: String): Boolean {
        val db: SQLiteDatabase = databaseOpenHelper.readableDatabase
        val selectionArg = arrayOf(userEmail)
        val selection: String = "$COLUMN_USER_EMAIL= ?"
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_USER WHERE $selection", selectionArg)

        return cursor.count==0
    }

    fun getUserName(userEmail: String):String {
        val db: SQLiteDatabase = databaseOpenHelper.readableDatabase
        val selectionArg = arrayOf(userEmail)
        val selection: String = "$COLUMN_USER_EMAIL= ?"
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_USER WHERE $selection", selectionArg)
        cursor.moveToFirst()

        return cursor.getString(1)
    }

    fun addUser(name: String, email:String, password: String){
        val values = ContentValues()

        values.put(COLUMN_USER_NAME, name)
        values.put(COLUMN_USER_EMAIL, email)
        values.put(COLUMN_USER_PASSWORD, password)


        val db = databaseOpenHelper.writableDatabase
        db.insert(TABLE_USER, null, values)
        db.close()
    }

    fun checkLogin(email: String, password: String) : Boolean{
        val db: SQLiteDatabase = databaseOpenHelper.readableDatabase
        val selectionArg = arrayOf(email, password)
        val selection: String = "$COLUMN_USER_EMAIL= ?"
        val passSelection: String = "$COLUMN_USER_PASSWORD= ?"
        var cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_USER WHERE $selection AND $passSelection",selectionArg)
        return cursor.count>0

    }

    companion object {
        private const val DATABASE_VERSION : Int = 1
        private const val DATABASE_NAME : String = "UserManager.db"
        const val TABLE_USER: String = "user"
        const val COLUMN_USER_ID: String = "user_id"
        const val COLUMN_USER_NAME: String = "user_name"
        const val COLUMN_USER_EMAIL: String = "user_email"
        const val COLUMN_USER_PASSWORD: String = "user_password"
    }
}
