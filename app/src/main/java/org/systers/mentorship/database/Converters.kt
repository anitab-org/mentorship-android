package org.systers.mentorship.database

import androidx.room.TypeConverter
import org.systers.mentorship.models.Relationship

class Converters {
    @TypeConverter
    fun fromRelationUserResponse(response: Relationship.RelationUserResponse?): Int? = response?.id

    @TypeConverter
    fun toRelationUserRessponse(id: Int): Relationship.RelationUserResponse? =
            Relationship.RelationUserResponse(id, id.toString())
}