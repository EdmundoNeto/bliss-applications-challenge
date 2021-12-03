package com.edmundo.blisschallenge.domain.database.dao

import androidx.room.*
import com.edmundo.blisschallenge.domain.database.entity.AvatarEntity

@Dao
interface AvatarDao {

    @Query("SELECT * from avatar")
    fun getAllAvatar(): List<AvatarEntity>

    @Query("select * from avatar where login = :login")
    fun getUserAvatar(login: String): AvatarEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserAvatar(avatar: AvatarEntity)

    @Delete
    fun deleteUserAvatar(avatar: AvatarEntity)



}