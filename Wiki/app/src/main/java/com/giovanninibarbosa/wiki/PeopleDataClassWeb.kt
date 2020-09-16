package com.giovanninibarbosa.wiki

data class PeopleResult (val results : List<People>)
data class People (val name : String, val height : String, val gender : String, val mass : String)
