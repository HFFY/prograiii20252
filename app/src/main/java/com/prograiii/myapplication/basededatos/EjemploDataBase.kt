package com.prograiii.myapplication.basededatos

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(EjemploParaRoom::class), version = 1)
abstract class EjemploDataBase: RoomDatabase() {
    abstract fun ejemploDao(): EjemploDao
}