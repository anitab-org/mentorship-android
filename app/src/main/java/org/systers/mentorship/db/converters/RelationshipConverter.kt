package org.systers.mentorship.db.converters

import androidx.room.TypeConverter
import org.systers.mentorship.models.Relationship.RelationUserResponse

class RelationshipConverter {
    @TypeConverter
    fun fromRelationUserResponse(response: RelationUserResponse?): String? = response?.name

    @TypeConverter
    fun toRelationUserResponse(name: String): RelationUserResponse =
        RelationUserResponse(name.length, name)
}
