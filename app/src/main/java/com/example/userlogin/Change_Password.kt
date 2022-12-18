package com.example.userlogin

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random
import kotlin.random.nextInt

class Change_Password : AppCompatActivity() {

    lateinit var oldpass : EditText
    lateinit var newpass : EditText
    lateinit var connewpass : EditText
    lateinit var captchacode : TextView
    lateinit var captcha : EditText
    lateinit var changepass : Button
    lateinit var cancel : Button
    lateinit var captcharefresh : Button

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        oldpass=findViewById(R.id.oldpass)
        newpass=findViewById(R.id.newpass)
        connewpass=findViewById(R.id.connewpass)
        captchacode=findViewById(R.id.captchacode)
        captcha=findViewById(R.id.captcha)
        changepass=findViewById(R.id.changepass)
        cancel=findViewById(R.id.cancel)
        captcharefresh=findViewById(R.id.captcharefresh)

        var r = Random.nextInt(111111..999999)
        captchacode.setText("$r")

        captcharefresh.setOnClickListener {
            var r = Random.nextInt(111111..999999)
            captchacode.setText("$r")
        }

        cancel.setOnClickListener {
            finish()
        }

        var gmail = intent.getStringExtra("smail")

        changepass.setOnClickListener {
            var op=oldpass.text.toString()
            var np=newpass.text.toString()
            var cnp=connewpass.text.toString()
            var cc=captchacode.text.toString()
            var c=captcha.text.toString()

            if(!op.equals("") && !np.equals("") && !cnp.equals("")){

                if(!c.equals("")){
//                    Your new password cannot be the same as your current password.
                    if (np==cnp){

                        if (cc==c){
                            var db = MydbHelper(this)
                            var rd = db.readableDatabase
                            var data=rd.rawQuery("select * from UserData where email=$gmail",null)
                            if (data.moveToNext()){
                                var ee = data.getString(data.getColumnIndex("ps"))
                                if (op.equals(ee)){
                                    var cv= ContentValues()
                                    cv.put("ps",np)
                                    cv.put("cps",cnp)
                                    var cp = db.writableDatabase
                                    var cdata=cp.update("UserData",cv,"email=$gmail",null)
                                    Toast.makeText(this, "Your Password has been Changed Successfully", Toast.LENGTH_SHORT).show()
                                    finish()
                                }else{
                                    Toast.makeText(this, "Old Password does not match", Toast.LENGTH_SHORT).show()
                                }

                            }

                        }else{
                            Toast.makeText(applicationContext, "Invalid Captcha", Toast.LENGTH_SHORT).show()
                        }

                    }else{
                        Toast.makeText(applicationContext, "New password must be same", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(applicationContext,"Please Enter Captha",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(applicationContext,"All fileds are required",Toast.LENGTH_SHORT).show()
            }

        }

    }
}