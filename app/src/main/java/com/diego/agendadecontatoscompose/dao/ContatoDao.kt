package com.diego.agendadecontatoscompose.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.diego.agendadecontatoscompose.model.Contato

@Dao
interface ContatoDao {

    @Insert
    fun gravar(listaContatos: MutableList<Contato>)

    @Query("SELECT * FROM tabela_contatos ORDER BY nome ASC")
    fun getContatos(): MutableList<Contato>

    @Query(
        "UPDATE tabela_contatos SET nome = :novoNome, sobrenome = :novoSobrenome, idade = :novaIdade, celular = :novoCelular " +
        "WHERE uid = :id"
    )
    fun atualizar(id: Int, novoNome: String, novoSobrenome: String, novaIdade: String, novoCelular: String)

    @Query("DELETE FROM tabela_contatos WHERE uid = :id ")
    fun deletar(id: Int)

    @Query("SELECT * FROM tabela_contatos WHERE uid = :id")
    suspend fun getContatoById(id: Int): Contato?

}