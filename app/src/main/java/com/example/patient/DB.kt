package com.example.patient

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, databaseName, factory, databaseVersion) {

    companion object{
        lateinit var database: SQLiteDatabase

        private val databaseName = "patientApp.db"
        private val databaseVersion = 1

        const val appointmentTableName = "Appointment"
        const val appointmentColTableId = 0
        const val appointmentColAppointmentId = 1
        const val appointmentColCode = 2

        val appointmentCols: Array<String> = arrayOf<String>("tableId", "appointmentId", "code")
        const val appointmentNumberCols = 2
    
   }
private val appointmentCreateSchema =
    "create table Appointment (tableId integer primary key autoincrement" +
        ", appointmentId VARCHAR(50) not null"+
        ", code VARCHAR(50) not null"+
    ")"

    override fun onCreate(db: SQLiteDatabase) {
         db.execSQL(appointmentCreateSchema)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + appointmentCreateSchema)
        onCreate(db)
    }

    fun onDestroy() {
        database.close()
    }

    fun listAppointment(): ArrayList<AppointmentVO> {
        val res = ArrayList<AppointmentVO>()
        database = readableDatabase
        val cursor: Cursor =
            database.query(appointmentTableName, appointmentCols, null, null, null, null, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast()) {
            res.add(setDataAppointment(cursor))
            cursor.moveToNext()
        }
        cursor.close()
        return res
    }

    fun createAppointment(appointmentvo: AppointmentVO) {
        database = writableDatabase
        database.insert(appointmentTableName, appointmentCols[1], putDataAppointment(appointmentvo))
    }

    fun searchByAppointmentappointmentId(value: String): ArrayList<AppointmentVO> {
            val res = ArrayList<AppointmentVO>()
	        database = readableDatabase
	        val args = arrayOf(value)
	        val cursor: Cursor = database.rawQuery(
	            "select tableId, appointmentId, code from Appointment where appointmentId = ?",
	            args
	        )
	        cursor.moveToFirst()
	        while (!cursor.isAfterLast()) {
	            res.add(setDataAppointment(cursor))
	            cursor.moveToNext()
	        }
	        cursor.close()
	        return res
	    }
	     
    fun searchByAppointmentcode(value: String): ArrayList<AppointmentVO> {
            val res = ArrayList<AppointmentVO>()
	        database = readableDatabase
	        val args = arrayOf(value)
	        val cursor: Cursor = database.rawQuery(
	            "select tableId, appointmentId, code from Appointment where code = ?",
	            args
	        )
	        cursor.moveToFirst()
	        while (!cursor.isAfterLast()) {
	            res.add(setDataAppointment(cursor))
	            cursor.moveToNext()
	        }
	        cursor.close()
	        return res
	    }
	     

    fun editAppointment(appointmentvo: AppointmentVO) {
        database = writableDatabase
        val args = arrayOf(appointmentvo.appointmentId)
        database.update(appointmentTableName, putDataAppointment(appointmentvo), "appointmentId =?", args)
    }

    fun deleteAppointment(value: String) {
        database = writableDatabase
        val args = arrayOf(value)
        database.delete(appointmentTableName, "appointmentId = ?", args)
    }


	private fun setDataAppointment(cursor: Cursor): AppointmentVO {
		val appointmentvo = AppointmentVO()
		    appointmentvo.appointmentId = cursor.getString(appointmentColAppointmentId)
		    appointmentvo.code = cursor.getString(appointmentColCode)
		return appointmentvo
	}
	
	private fun putDataAppointment(appointmentvo: AppointmentVO): ContentValues {
        val wr = ContentValues(appointmentNumberCols)
        wr.put(appointmentCols[appointmentColAppointmentId], appointmentvo.appointmentId)
        wr.put(appointmentCols[appointmentColCode], appointmentvo.code)
		return wr
	}
}
