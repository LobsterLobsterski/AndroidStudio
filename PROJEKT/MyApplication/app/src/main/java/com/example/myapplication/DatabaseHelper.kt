package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF not exists attractions (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT)")
        addAttraction(AttractionsModelClass(1, "Grabówek", "Najlesze miejsce w Gdynia"))
        addAttraction(AttractionsModelClass(2, "AMW", "Najśmieszniejsze miejsce w Gdynia"))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS attractions")
        onCreate(db)
    }

    fun addAttraction(attraction: AttractionsModelClass):Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id", attraction.attractionId)
        contentValues.put("name", attraction.attractionName) // EmpModelClass Name
        contentValues.put("description", attraction.attractionDescription ) // EmpModelClass Email

        // Inserting Row
        val success = db.insert("attractions", null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    fun viewAttractions():List<AttractionsModelClass>{

        val empList:ArrayList<AttractionsModelClass> = ArrayList<AttractionsModelClass>()
        val selectQuery = "SELECT  * FROM attractions"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var desc: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                desc = cursor.getString(cursor.getColumnIndex("description"))
                val emp= AttractionsModelClass(attractionId = id,
                    attractionName = name,
                    attractionDescription = desc)

                empList.add(emp)

            } while (cursor.moveToNext())
        }
        return empList
    }

    companion object {
        private const val DATABASE_NAME = "gdynia.db"
        private const val DATABASE_VERSION = 1
    }
}
