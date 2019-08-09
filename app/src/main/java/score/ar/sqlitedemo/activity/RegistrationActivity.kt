package score.ar.sqlitedemo.activity

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import score.ar.sqlitedemo.dataBase.DBUtils
import score.ar.sqlitedemo.R

class RegistrationActivity : AppCompatActivity() {

    var fName: EditText? = null
    var lName: EditText? = null
    var mobile: EditText? = null
    var email: EditText? = null
    var password: EditText? = null
    var btnUpdate: Button? = null
    var regLogin: Button? = null
    lateinit var myDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        initialize()

        btnUpdate?.setOnClickListener {
            if (validate()) {
                saveValueToSqlite(
                    fName?.text.toString(),
                    lName?.text.toString(),
                    mobile?.text.toString(),
                    email?.text.toString(),
                    password?.text.toString()
                )

                startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
            }
        }

        regLogin?.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
        }
    }

    private fun saveValueToSqlite(
        fName: String,
        lName: String,
        mobile: String,
        email: String,
        password: String
    ) {
        try {
            val cv = ContentValues()

            myDB = this.openOrCreateDatabase(
                DBUtils.DBName,
                MODE_PRIVATE, null
            )

            myDB.execSQL(DBUtils.RegistrationTable)

            cv.put("F_NAME", fName)
            cv.put("L_NAME", lName)
            cv.put("MOBILE", mobile)
            cv.put("EMAIL", email)
            cv.put("PASSWORD", password)

            myDB.insertOrThrow(DBUtils.RegistrationTableName, null, cv)

            myDB.close()


        } catch (e: Exception) {
            myDB.close()
            e.printStackTrace()
        }
    }

    private fun validate(): Boolean {
        if (fName?.text.toString().isEmpty()) {
            Toast.makeText(this@RegistrationActivity, "Please enter First name", Toast.LENGTH_LONG).show()
            return false
        }
        if (lName?.text.toString().isEmpty()) {
            Toast.makeText(this@RegistrationActivity, "Please enter Last name", Toast.LENGTH_LONG).show()
            return false
        }
        if (mobile?.text.toString().isEmpty()) {
            Toast.makeText(this@RegistrationActivity, "Please enter Mobile Number", Toast.LENGTH_LONG).show()
            return false
        }
        if (email?.text.toString().isEmpty()) {
            Toast.makeText(this@RegistrationActivity, "Please enter Email Address", Toast.LENGTH_LONG).show()
            return false
        }
        if (password?.text.toString().isEmpty()) {
            Toast.makeText(this@RegistrationActivity, "Please enter Password", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun initialize() {
        fName = findViewById(R.id.fName)
        lName = findViewById(R.id.lName)
        mobile = findViewById(R.id.mobile)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btnUpdate = findViewById(R.id.btnUpdate)
        regLogin = findViewById(R.id.regLogin)
    }
}
