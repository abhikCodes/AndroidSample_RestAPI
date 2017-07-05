package com.codingblocks.restapiretrofitjson.models

/**
 * Created by abhik on 30/06/17.
 */

data class ToDo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
