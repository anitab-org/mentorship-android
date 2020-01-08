package org.systers.mentorship.db

import androidx.room.TypeConverter
import org.systers.mentorship.models.Relationship

object RelationshipConverter {

    @TypeConverter
    @JvmStatic
    fun relationshipUserResponseToString(relationship: Relationship.RelationUserResponse?): String? {
        return relationship?.let {
            "${it.id},${it.name}"
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToRelationship(encodedRelationship: String?): Relationship.RelationUserResponse? {
        return encodedRelationship?.let {
            val values = it.split(",")
            val id = values[0].toInt()
            val name = values[1]
            Relationship.RelationUserResponse(id, name)
        }
    }
}
