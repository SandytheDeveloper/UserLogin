package com.example.userlogin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

var dbname="LoginRecord"
var tablename = "UserData"

var ufn="fn"
var uln="ln"
var umb="mb"
var uemail="email"
var ubirth="birth"
var ugen="gen"
var uedu="edu"
var ucon="con"
var ust="st"
var uct="ct"
var ups="ps"
var ucps="cps"

class MydbHelper(var con:Context):SQLiteOpenHelper(con, dbname,null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {

        var q="create table $tablename($ufn varchar(20),$uln varchar(20),$umb varchar(20),$uemail varchar(40),$ubirth varchar(20)," +
                "$ugen varchar(10),$uedu varchar(20),$ucon varchar(20),$ust varchar(20),$uct varchar(20),$ups varchar(20),$ucps varchar(20))"
        p0!!.execSQL(q)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun saveData(r:registerdata){

        var db = writableDatabase

        var c=ContentValues()
        c.put(ufn,r.fn)
        c.put(uln,r.ln)
        c.put(umb,r.mb)
        c.put(uemail,r.email)
        c.put(ubirth,r.birth)
        c.put(ugen,r.gen)
        c.put(uedu,r.edu)
        c.put(ucon,r.con)
        c.put(ust,r.st)
        c.put(uct,r.ct)
        c.put(ups,r.ps)
        c.put(ucps,r.cps)

        var ans=db.insert(tablename,null,c)
        if (ans==(0).toLong()){
            Toast.makeText(con,"Error...",Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(con,"Account Created Successfully",Toast.LENGTH_SHORT).show()
        }
    }

}