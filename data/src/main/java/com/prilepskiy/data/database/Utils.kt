package com.prilepskiy.data.database

fun filter(name: String, status: String, gender: String): String {
    val query = "SELECT * FROM characned_table  WHERE "
    val arrayBilder = mutableListOf<String>()

    if (name.isNotEmpty()) arrayBilder.add("name LIKE ('%' || :name || '%')")
    if (status.isNotEmpty()) arrayBilder.add("status LIKE  ('%' || :status || '%')")
    if (gender.isNotEmpty()) arrayBilder.add("gender LIKE ('%' || :gender || '%')")

    if (arrayBilder.size == 1) query + arrayBilder.first()
    else if (arrayBilder.size == 2) query + arrayBilder.first() + "&" + arrayBilder[1]
    else if (arrayBilder.size == 3) query + arrayBilder.first() + "&" + arrayBilder[1] + "&" + arrayBilder[2]

    return "$query Order By page"
}
