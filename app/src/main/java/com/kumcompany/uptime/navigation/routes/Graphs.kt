package com.kumcompany.uptime.navigation.routes

sealed class Graphs(val route: String) {
    object ROOT : Graphs("root_graph")
    object WELCOME : Graphs("welcome_graph")
    object MAIN : Graphs("main_graph")
}