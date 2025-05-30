package com.diego.agendadecontatoscompose

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diego.agendadecontatoscompose.const.Constantes
import com.diego.agendadecontatoscompose.dao.ContatoDao
import com.diego.agendadecontatoscompose.model.Contato

@Database(entities = [Contato::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun contatoDao(): ContatoDao

    companion object{
        //definir que o banco terá uma instancia apenas
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constantes.DB_CONTATOS
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}