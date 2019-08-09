package score.ar.sqlitedemo.dataBase

object DBUtils {

    var DBName = "DemoDB.db"
    val DATABASE_VERSION = 1

    var ID = "ID"
    var F_NAME = "F_NAME"
    var L_NAME = "L_NAME"
    var MOBILE = "MOBILE"
    var EMAIL = "EMAIL"
    var PASSWORD = "PASSWORD"

    var RegistrationTableName = "Registration"
    val RegistrationTable = ("CREATE TABLE IF NOT EXISTS " + RegistrationTableName
            + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "F_NAME TEXT,"
            + "L_NAME TEXT,"
            + "MOBILE TEXT,"
            + "EMAIL TEXT,"
            + "PASSWORD TEXT);")

}