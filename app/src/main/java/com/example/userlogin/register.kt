package com.example.userlogin

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class register : AppCompatActivity() {

    lateinit var fname : EditText
    lateinit var lname : EditText
    lateinit var mobile : EditText
    lateinit var mail : EditText
    lateinit var dob : TextView
    lateinit var gender : Spinner
    lateinit var education : Spinner
    lateinit var country : Spinner
    lateinit var state : Spinner
    lateinit var city : Spinner
    lateinit var password : EditText
    lateinit var conpassword : EditText
    lateinit var dobbtn : Button

    lateinit var create : Button

    var gndr = arrayOf("Male","Female")
    var edu = arrayOf("HSC","SSC","Graduate","Post Graduate","Diploma")
    var con = arrayOf("India","France")

    // State
    var ind = arrayOf("Gujarat","Maharastra","Delhi")
    var fra = arrayOf("Brittany","Centre","Corsica")

    //Indian Cities
    var guj = arrayOf("Ahmedabad","Surat","Gandhinagar")
    var mah = arrayOf("Mumbai","Nagpur","Pune")
    var del = arrayOf("Rajokri","Saidabad","Sultan Pur")

    //France Cities
    var bri = arrayOf("Rennes","Brest","Quimper")
    var cen = arrayOf("Bourges","Blois","Vierzon")
    var cor = arrayOf("Alata","Bonifacio","Zonza")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fname=findViewById(R.id.fname)
        lname=findViewById(R.id.lname)
        mobile=findViewById(R.id.mobile)
        mail=findViewById(R.id.mail)
        dob=findViewById(R.id.dobtext)
        gender=findViewById(R.id.gender)
        education=findViewById(R.id.education)
        country=findViewById(R.id.country)
        state=findViewById(R.id.state)
        city=findViewById(R.id.city)
        password=findViewById(R.id.password)
        conpassword=findViewById(R.id.conpassword)
        dobbtn=findViewById(R.id.dobbtn)

        create=findViewById(R.id.create)

        var adg = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, gndr)
        gender.adapter = adg

        var ade = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,edu)
        education.adapter=ade

        var adc =ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,con)
        country.adapter=adc

//        ON Country selection ---------------------------------------------------------------------
        country.onItemSelectedListener =object :
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position==0){
                var ads = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,ind)
                state.adapter=ads

                    state.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position==0){
                                var adc = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,guj)
                                city.adapter=adc
                            }
                            if (position==1){
                                var adc= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,mah)
                                city.adapter=adc
                            }
                            if (position==2){
                                var adc= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,del)
                                city.adapter=adc
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
            }
            if (position==1){
                var ads= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,fra)
                state.adapter=ads

                state.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (position==0){
                            var adc = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,bri)
                            city.adapter=adc
                        }
                        if (position==1){
                            var adc= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,cen)
                            city.adapter=adc
                        }
                        if (position==2){
                            var adc= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,cor)
                            city.adapter=adc
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

            }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


//        For Date Selection
        dobbtn.setOnClickListener {

            var c= Calendar.getInstance()
            var y=c.get(Calendar.YEAR)
            var m=c.get(Calendar.MONTH)
            var d=c.get(Calendar.DAY_OF_MONTH)

            var dpd= DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                var a=i2+1

                dob.setText("$i3-$a-$i")

            },y,m,d)

            dpd.show()

        }



//        INSERTING DATA to DATABASE

        var db = MydbHelper(this)

        create.setOnClickListener {
            var fn = fname.text.toString()
            var ln = lname.text.toString()
            var mb = mobile.text.toString()
            var email = mail.text.toString()
            var birth = dob.text.toString()
            var gen = gender.selectedItem.toString()
            var edu = education.selectedItem.toString()
            var con = country.selectedItem.toString()
            var st = state.selectedItem.toString()
            var ct = city.selectedItem.toString()
            var ps = password.text.toString()
            var cps = conpassword.text.toString()

            if(!fn.equals("") && !ln.equals("") && !mb.equals("") && !email.equals("") && !birth.equals("")
                && !ps.equals("") && !cps.equals("")){
                if (ps==cps){

                    var db = MydbHelper(this)

                    var rd = db.readableDatabase
                    var args= listOf<String>(email.toString()).toTypedArray()
                    var rs = rd.rawQuery("select * from UserData where email = ?",args)
                    if (rs.moveToNext()) {

                        Toast.makeText(applicationContext,"This Email is already in use",Toast.LENGTH_SHORT).show()

                    }else{
                        var svdata = registerdata(fn,ln,mb,email,birth,gen,edu,con,st,ct,ps,cps)

                        db.saveData(svdata)

//                        var i=Intent(applicationContext,Home::class.java)
//                        startActivity(i)
                        finish()
                    }

                }else{
                    Toast.makeText(applicationContext,"Passwords must be same",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(applicationContext,"All fileds are required",Toast.LENGTH_SHORT).show()
            }
        }

    }//END onCREATE METHOD
}//END MAIN CLASS