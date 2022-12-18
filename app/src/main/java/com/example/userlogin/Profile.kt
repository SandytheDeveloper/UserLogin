package com.example.userlogin

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import java.util.*

class Profile : AppCompatActivity() {

    lateinit var more : Button
    lateinit var name : TextView
    lateinit var number : TextView
    lateinit var email : TextView
    lateinit var dob : TextView
    lateinit var gen : TextView
    lateinit var edu : TextView
    lateinit var con : TextView
    lateinit var sta : TextView
    lateinit var cit : TextView
    lateinit var sp : SharedPreferences

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        more=findViewById(R.id.morebtn)
        name=findViewById(R.id.name)
        number=findViewById(R.id.number)
        email=findViewById(R.id.email)
        dob=findViewById(R.id.dob)
        gen=findViewById(R.id.gen)
        edu=findViewById(R.id.edu)
        con=findViewById(R.id.con)
        sta=findViewById(R.id.sta)
        cit=findViewById(R.id.cit)

        var i = intent
        var mmail=i.getStringExtra("mail")

        var db = MydbHelper(this)

        var fna=""
        var lna=""

        var rd = db.readableDatabase
        var args= listOf<String>(mmail.toString()).toTypedArray()
        var rs = rd.rawQuery("select * from UserData where email = ?",args)
        if (rs.moveToNext()) {

            fna = rs.getString(rs.getColumnIndex("fn")).toString()
            lna = rs.getString(rs.getColumnIndex("ln")).toString()
            name.setText(fna + " " + lna)
            number.setText(rs.getString(rs.getColumnIndex("mb")).toString())
            email.setText(rs.getString(rs.getColumnIndex("email")).toString())
            dob.setText(rs.getString(rs.getColumnIndex("birth")).toString())
            gen.setText(rs.getString(rs.getColumnIndex("gen")).toString())
            edu.setText(rs.getString(rs.getColumnIndex("edu")).toString())
            con.setText(rs.getString(rs.getColumnIndex("con")).toString())
            sta.setText(rs.getString(rs.getColumnIndex("st")).toString())
            cit.setText(rs.getString(rs.getColumnIndex("ct")).toString())
        }else{
            Toast.makeText(applicationContext, "Record Not Found", Toast.LENGTH_SHORT).show()
        }



        more.setOnClickListener {

            var pm=PopupMenu(this,more)
            pm.menuInflater.inflate(R.menu.pmenu,pm.menu)
            pm.show()

            pm.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->

                when(item.itemId){
                    R.id.edit-> {
                        var arr= arrayOf(fna,lna,number.text.toString(),email.text.toString(),dob.text.toString(),gen.text.toString(),
                                    edu.text.toString(),con.text.toString(),sta.text.toString(),cit.text.toString())
                        var i=Intent(this,Update_activity::class.java)
                        i.putExtra("arr",arr)

                        startActivity(i)
                        Toast.makeText(applicationContext, "Editing", Toast.LENGTH_SHORT).show()
                    }
                    R.id.change->{
                        var smail=email.text.toString()
                        var i = Intent(applicationContext,Change_Password::class.java)
                        i.putExtra("smail",smail)
                        startActivity(i)
                        Toast.makeText(applicationContext,"Change Your Password",Toast.LENGTH_SHORT).show()
                    }
                    R.id.logout->{
                        sp=getSharedPreferences("myPref", MODE_PRIVATE)
                        var ed=sp.edit()
                        ed.clear()
                        ed.commit()

                        var i = Intent(applicationContext,Home::class.java)
                        startActivity(i)
                        finish()
                        Toast.makeText(applicationContext, "logout", Toast.LENGTH_SHORT).show()
                    }
                }

                true
            })
        }// END more clickListener

    }
}