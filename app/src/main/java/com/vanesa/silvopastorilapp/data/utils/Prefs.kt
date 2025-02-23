package com.vanesa.silvopastorilapp.data.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_ID = "123"
    val PREFS_ROL_ID = "1"
    val PREFS_NAME = "com.cursokotlin.sharedpreferences"
    val PREFS_LASTNAME = "incial"
    val PREFS_JWT = "TokenJwt"

    val SHARED_ID           = "shared_id"
    val SHARED_ROL_ID       = "shared_rol"
    val SHARED_NAME         = "shared_name"
    val SHARED_LASTNAME     = "shared_lastname"
    val SHARED_JWT          = "shared_jwt"

    val prefsIdUser: SharedPreferences      = context.getSharedPreferences(PREFS_ID, 0)
    val prefsRolId: SharedPreferences       = context.getSharedPreferences(PREFS_ROL_ID, 0)
    val prefsName: SharedPreferences        = context.getSharedPreferences(PREFS_NAME, 0)
    val prefsLastName: SharedPreferences    = context.getSharedPreferences(PREFS_LASTNAME, 0)
    val prefsJwt: SharedPreferences         = context.getSharedPreferences(PREFS_JWT, 0)

    var id: String
        get() = prefsIdUser.getString(SHARED_ID, "").toString()
        set(value) = prefsIdUser.edit().putString(SHARED_ID, value).apply()

    var rolId: String
        get() = prefsRolId.getString(SHARED_ROL_ID, "").toString()
        set(value) = prefsRolId.edit().putString(SHARED_ROL_ID, value).apply()

    var name: String
        get() = prefsName.getString(SHARED_NAME, "").toString()
        set(value) = prefsName.edit().putString(SHARED_NAME, value).apply()

    var lastname: String
        get() = prefsLastName.getString(SHARED_LASTNAME, "").toString()
        set(value) = prefsLastName.edit().putString(SHARED_LASTNAME, value).apply()

    var jwt: String
        get() = prefsJwt.getString(SHARED_JWT, "").toString()
        set(value) = prefsJwt.edit().putString(SHARED_JWT, value).apply()

}
