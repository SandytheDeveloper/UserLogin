package com.example.userlogin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Home : AppCompatActivity() {

    lateinit var signbtn : Button
    lateinit var reg : TextView
    lateinit var userid : EditText
    lateinit var userkey :EditText
    lateinit var sp : SharedPreferences

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        signbtn=findViewById(R.id.signbtn)
        reg=findViewById(R.id.register)
        userid=findViewById(R.id.userid)
        userkey=findViewById(R.id.userkey)

        sp=getSharedPreferences("myPref", MODE_PRIVATE)

        if (sp.contains("User_id")&&sp.contains("User_key")){
            var mmail=sp.getString("User_id","")
            var i = Intent(applicationContext,Profile::class.java)
            i.putExtra("mail",mmail)
            startActivity(i)
            finish()
        }

        var db = MydbHelper(this)

        signbtn.setOnClickListener {
            var rd = db.readableDatabase
            var id=userid.text.toString()
            var key=userkey.text.toString()
            var args= listOf<String>(id,key).toTypedArray()
            var rs = rd.rawQuery("select * from UserData where email = ? and ps = ?",args)
            if (rs.moveToNext()){

                var getname =rs.getString(rs.getColumnIndex("fn")).toString()
                var getmail =rs.getString(rs.getColumnIndex("email")).toString()
                Toast.makeText(applicationContext,"Welcome $getname",Toast.LENGTH_SHORT).show()

                var ed=sp.edit()
                ed.putString("User_id",id)
                ed.putString("User_key",key)
                ed.apply()

                var i = Intent(applicationContext,Profile::class.java)
                i.putExtra("mail",getmail)
                startActivity(i)
                finish()

            }else{
                Toast.makeText(applicationContext,"Invalid Credential",Toast.LENGTH_SHORT).show()
            }

        }

        reg.setOnClickListener {
            var i = Intent(applicationContext,register::class.java)
            startActivity(i)
        }

    }
}


//if(p0!!.getselectedItemPosition="Ahmedabad")