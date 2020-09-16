package com.giovanninibarbosa.wiki

data class Person (val name : String, val height : String, val gender : String, val mass : String) {
    override fun toString(): String {
        return "${name} - ${height} - ${gender} - ${mass}"
    }
}