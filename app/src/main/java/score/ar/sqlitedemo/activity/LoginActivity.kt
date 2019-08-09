package score.ar.sqlitedemo.activity

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import score.ar.sqlitedemo.dataBase.DBUtils
import score.ar.sqlitedemo.R

class LoginActivity : AppCompatActivity() {

    var loginEmail: EditText? = null
    var loginPassword: EditText? = null
    var login: Button? = null
    lateinit var myDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()

        login?.setOnClickListener {
            if (validate()) {
                checkLogin(loginEmail?.text.toString(), loginPassword?.text.toString())
            }
        }
    }

    private fun checkLogin(loginEmail: String, loginPassword: String) {

        try {
            var fName = ""
            var lName = ""
            var mob = ""
            var mail = ""
            val cur: Cursor

            myDB = this.openOrCreateDatabase(
                DBUtils.DBName,
                MODE_PRIVATE, null
            )
            myDB.execSQL(DBUtils.RegistrationTable)

            cur = myDB.rawQuery(
                "SELECT * FROM Registration WHERE EMAIL=? AND PASSWORD=?", arrayOf(loginEmail, loginPassword),
                null
            )
            cur.moveToFirst()

            val firstName = cur.getColumnIndex(DBUtils.F_NAME)
            val lastName = cur.getColumnIndex(DBUtils.L_NAME)
            val mobile = cur.getColumnIndex(DBUtils.MOBILE)
            val email = cur.getColumnIndex(DBUtils.EMAIL)

            while (!cur.isAfterLast) {
                fName = cur.getString(firstName)
                lName = cur.getString(lastName)
                mob = cur.getString(mobile)
                mail = cur.getString(email)
                cur.moveToNext()
            }
            cur.close()

            if (loginEmail == mail) {
                var intent = Intent(this@LoginActivity, LandingPage::class.java)
                intent.putExtra("fName", fName)
                intent.putExtra("lName", lName)
                intent.putExtra("mob", mob)
                intent.putExtra("mail", mail)
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun validate(): Boolean {
        if (loginEmail?.text.toString().isEmpty()) {
            Toast.makeText(this@LoginActivity, "Please enter Email Address", Toast.LENGTH_LONG).show()
            return false
        }
        if (loginPassword?.text.toString().isEmpty()) {
            Toast.makeText(this@LoginActivity, "Please enter Password", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun initialize() {
        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)
        login = findViewById(R.id.login)
    }
}
