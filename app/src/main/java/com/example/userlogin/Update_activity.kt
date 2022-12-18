package com.example.userlogin

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class Update_activity : AppCompatActivity() {

    lateinit var ufname : EditText
    lateinit var ulname : EditText
    lateinit var umobile : EditText
    lateinit var umail : EditText
    lateinit var udob : TextView
    lateinit var ugender : Spinner
    lateinit var ueducation : Spinner
    lateinit var ucountry : Spinner
    lateinit var ustate : Spinner
    lateinit var ucity : Spinner
    lateinit var udobbtn : Button
    lateinit var cancel : Button
    lateinit var update : Button

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
        setContentView(R.layout.activity_update)

        var i=intent
//        var fna=i.getStringExtra("arr")
//        var lna=i.getStringExtra("lna")
        var ar=i.getStringArrayExtra("arr")

        ufname=findViewById(R.id.ufname)
        ulname=findViewById(R.id.ulname)
        umobile=findViewById(R.id.umobile)
        umail=findViewById(R.id.umail)
        udob=findViewById(R.id.udob)
        ugender=findViewById(R.id.ugender)
        ueducation=findViewById(R.id.ueducation)
        ucountry=findViewById(R.id.ucountry)
        ustate=findViewById(R.id.ustate)
        ucity=findViewById(R.id.ucity)
        udobbtn=findViewById(R.id.udobbtn)
        cancel=findViewById(R.id.cancel)
        update=findViewById(R.id.update)

        cancel.setOnClickListener {
            finish()
        }

        ufname.setText(ar?.get(0) ?: null)
        ulname.setText(ar?.get(1) ?: null)
        umobile.setText(ar?.get(2) ?: null)
        umail.setText(ar?.get(3))
        udob.setText(ar?.get(4))


//        ===================================SPINNER=======================================================================
        var adg = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, gndr)
        ugender.adapter = adg

        var gender=ar?.get(5)
        if (gender != null) {
            var spinnerPosition: Int = adg.getPosition(gender)
            ugender.setSelection(spinnerPosition)
        }

        var ade = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,edu)
        ueducation.adapter=ade

        var eddu=ar?.get(6)
        if (eddu!=null){
            var spinnerPosition: Int = ade.getPosition(eddu)
            ueducation.setSelection(spinnerPosition)
        }

        var adc = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,con)
        ucountry.adapter=adc

        var coun=ar?.get(7)
        if (coun!=null){
            var spinnerPosition: Int = adc.getPosition(coun)
            ucountry.setSelection(spinnerPosition)
        }

//        ON Country selection ---------------------------------------------------------------------
        ucountry.onItemSelectedListener =object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position==0){
                    var ads = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,ind)
                    ustate.adapter=ads

                    ustate.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position==0){
                                var adci = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,guj)
                                ucity.adapter=adci
                                var spinnerPosition: Int = adci.getPosition(ar?.get(9))
                                ucity.setSelection(spinnerPosition)
                            }
                            if (position==1){
                                var adci= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,mah)
                                ucity.adapter=adci
                                var spinnerPosition: Int = adci.getPosition(ar?.get(9))
                                ucity.setSelection(spinnerPosition)
                            }
                            if (position==2){
                                var adci= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,del)
                                ucity.adapter=adci
                                var spinnerPosition: Int = adci.getPosition(ar?.get(9))
                                ucity.setSelection(spinnerPosition)
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
                    var stt=ar?.get(8)
                    if (stt!=null){
                        var spinnerPosition: Int = ads.getPosition(stt)
                        ustate.setSelection(spinnerPosition)
                    }
                }
                if (position==1){
                    var ads= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,fra)
                    ustate.adapter=ads

                    ustate.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position==0){
                                var adci = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,bri)
                                ucity.adapter=adci
                                var spinnerPosition: Int = adci.getPosition(ar?.get(9))
                                ucity.setSelection(spinnerPosition)
                            }
                            if (position==1){
                                var adci= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,cen)
                                ucity.adapter=adci
                                var spinnerPosition: Int = adci.getPosition(ar?.get(9))
                                ucity.setSelection(spinnerPosition)
                            }
                            if (position==2){
                                var adci= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,cor)
                                ucity.adapter=adci
                                var spinnerPosition: Int = adci.getPosition(ar?.get(9))
                                ucity.setSelection(spinnerPosition)
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
                    var stt=ar?.get(8)
                    if (stt!=null){
                        var spinnerPosition: Int = ads.getPosition(stt)
                        ustate.setSelection(spinnerPosition)
                    }

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


//        For Date Selection
        udobbtn.setOnClickListener {

            var c= Calendar.getInstance()
            var y=c.get(Calendar.YEAR)
            var m=c.get(Calendar.MONTH)
            var d=c.get(Calendar.DAY_OF_MONTH)

            var dpd= DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                var a=i2+1

                udob.setText("$i3-$a-$i")

            },y,m,d)

            dpd.show()

        }

        update.setOnClickListener {
            var fn = ufname.text.toString()
            var ln = ulname.text.toString()
            var mb = umobile.text.toString()
            var email = umail.text.toString()
            var birth = udob.text.toString()
            var gen = ugender.selectedItem.toString()
            var edu = ueducation.selectedItem.toString()
            var con = ucountry.selectedItem.toString()
            var st = ustate.selectedItem.toString()
            var ct = ucity.selectedItem.toString()

            if (!fn.equals("") && !ln.equals("") && !mb.equals("") && !birth.equals(""))
            {
                var db = MydbHelper(this).writableDatabase
                var cv=ContentValues()
                cv.put("fn",fn)
                cv.put("ln",ln)
                cv.put("mb",mb)
                cv.put("birth",birth)
                cv.put("gen",gen)
                cv.put("edu",edu)
                cv.put("con",con)
                cv.put("st",st)
                cv.put("ct",ct)

                db.update("UserData",cv,"email=$email",null)
                Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show()

                val i = Intent(applicationContext, Home::class.java)        // Specify any activity here e.g. home or splash or login etc
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                i.putExtra("EXIT", true)
                startActivity(i)
                finish()

            }
        }

    }
}