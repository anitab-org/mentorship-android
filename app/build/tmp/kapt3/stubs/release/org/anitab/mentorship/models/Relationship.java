package org.anitab.mentorship.models;

import java.lang.System;

/**
 * This data class represents partial information of user of the system.
 * This is used in APIs not directly related with Users, such as in responses
 * related to MentorshipRelation
 *
 * @param id identifier of the mentorship relation
 * @param actionUserId id of the user that sent the request for this mentorship relation
 * @param sentByMe indication if the current user was the action user
 * @param mentor user with mentor role in the relation
 * @param mentee user with mentee role in the relation
 * @param creationDate date of creation unix timestamp
 * @param acceptDate date of acceptance unix timestamp
 * @param startDate start date unix timestamp
 * @param endDate end date unix timestamp
 * @param state state of the relation (@link to RelationState)
 * @param notes notes related to the mentorship relation
 */
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001:B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0011H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\bH\u00c6\u0003J\t\u0010)\u001a\u00020\bH\u00c6\u0003J\t\u0010*\u001a\u00020\u000bH\u00c6\u0003J\t\u0010+\u001a\u00020\u000bH\u00c6\u0003J\t\u0010,\u001a\u00020\u000bH\u00c6\u0003J\t\u0010-\u001a\u00020\u000bH\u00c6\u0003Jw\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001J\u0013\u00100\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u000102H\u00d6\u0003J\t\u00103\u001a\u00020\u0003H\u00d6\u0001J\t\u00104\u001a\u00020\u0011H\u00d6\u0001J\u0019\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\r\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016\u00a8\u0006;"}, d2 = {"Lorg/anitab/mentorship/models/Relationship;", "Landroid/os/Parcelable;", "id", "", "actionUserId", "sentByMe", "", "mentor", "Lorg/anitab/mentorship/models/Relationship$RelationUserResponse;", "mentee", "creationDate", "", "acceptDate", "startDate", "endDate", "state", "notes", "", "(IIZLorg/anitab/mentorship/models/Relationship$RelationUserResponse;Lorg/anitab/mentorship/models/Relationship$RelationUserResponse;FFFFILjava/lang/String;)V", "getAcceptDate", "()F", "getActionUserId", "()I", "getCreationDate", "getEndDate", "getId", "getMentee", "()Lorg/anitab/mentorship/models/Relationship$RelationUserResponse;", "getMentor", "getNotes", "()Ljava/lang/String;", "getSentByMe", "()Z", "getStartDate", "getState", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "RelationUserResponse", "app_release"})
public final class Relationship implements android.os.Parcelable {
    private final int id = 0;
    private final int actionUserId = 0;
    private final boolean sentByMe = false;
    @org.jetbrains.annotations.NotNull()
    private final org.anitab.mentorship.models.Relationship.RelationUserResponse mentor = null;
    @org.jetbrains.annotations.NotNull()
    private final org.anitab.mentorship.models.Relationship.RelationUserResponse mentee = null;
    private final float creationDate = 0.0F;
    private final float acceptDate = 0.0F;
    private final float startDate = 0.0F;
    private final float endDate = 0.0F;
    private final int state = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String notes = null;
    public static final android.os.Parcelable.Creator<org.anitab.mentorship.models.Relationship> CREATOR = null;
    
    public final int getId() {
        return 0;
    }
    
    public final int getActionUserId() {
        return 0;
    }
    
    public final boolean getSentByMe() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.models.Relationship.RelationUserResponse getMentor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.models.Relationship.RelationUserResponse getMentee() {
        return null;
    }
    
    public final float getCreationDate() {
        return 0.0F;
    }
    
    public final float getAcceptDate() {
        return 0.0F;
    }
    
    public final float getStartDate() {
        return 0.0F;
    }
    
    public final float getEndDate() {
        return 0.0F;
    }
    
    public final int getState() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNotes() {
        return null;
    }
    
    public Relationship(int id, int actionUserId, boolean sentByMe, @org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Relationship.RelationUserResponse mentor, @org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Relationship.RelationUserResponse mentee, float creationDate, float acceptDate, float startDate, float endDate, int state, @org.jetbrains.annotations.NotNull()
    java.lang.String notes) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.models.Relationship.RelationUserResponse component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.models.Relationship.RelationUserResponse component5() {
        return null;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    public final float component7() {
        return 0.0F;
    }
    
    public final float component8() {
        return 0.0F;
    }
    
    public final float component9() {
        return 0.0F;
    }
    
    public final int component10() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    /**
     * This data class represents partial information of user of the system.
     * This is used in APIs not directly related with Users, such as in responses
     * related to MentorshipRelation
     *
     * @param id identifier of the mentorship relation
     * @param actionUserId id of the user that sent the request for this mentorship relation
     * @param sentByMe indication if the current user was the action user
     * @param mentor user with mentor role in the relation
     * @param mentee user with mentee role in the relation
     * @param creationDate date of creation unix timestamp
     * @param acceptDate date of acceptance unix timestamp
     * @param startDate start date unix timestamp
     * @param endDate end date unix timestamp
     * @param state state of the relation (@link to RelationState)
     * @param notes notes related to the mentorship relation
     */
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.models.Relationship copy(int id, int actionUserId, boolean sentByMe, @org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Relationship.RelationUserResponse mentor, @org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Relationship.RelationUserResponse mentee, float creationDate, float acceptDate, float startDate, float endDate, int state, @org.jetbrains.annotations.NotNull()
    java.lang.String notes) {
        return null;
    }
    
    /**
     * This data class represents partial information of user of the system.
     * This is used in APIs not directly related with Users, such as in responses
     * related to MentorshipRelation
     *
     * @param id identifier of the mentorship relation
     * @param actionUserId id of the user that sent the request for this mentorship relation
     * @param sentByMe indication if the current user was the action user
     * @param mentor user with mentor role in the relation
     * @param mentee user with mentee role in the relation
     * @param creationDate date of creation unix timestamp
     * @param acceptDate date of acceptance unix timestamp
     * @param startDate start date unix timestamp
     * @param endDate end date unix timestamp
     * @param state state of the relation (@link to RelationState)
     * @param notes notes related to the mentorship relation
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    /**
     * This data class represents partial information of user of the system.
     * This is used in APIs not directly related with Users, such as in responses
     * related to MentorshipRelation
     *
     * @param id identifier of the mentorship relation
     * @param actionUserId id of the user that sent the request for this mentorship relation
     * @param sentByMe indication if the current user was the action user
     * @param mentor user with mentor role in the relation
     * @param mentee user with mentee role in the relation
     * @param creationDate date of creation unix timestamp
     * @param acceptDate date of acceptance unix timestamp
     * @param startDate start date unix timestamp
     * @param endDate end date unix timestamp
     * @param state state of the relation (@link to RelationState)
     * @param notes notes related to the mentorship relation
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * This data class represents partial information of user of the system.
     * This is used in APIs not directly related with Users, such as in responses
     * related to MentorshipRelation
     *
     * @param id identifier of the mentorship relation
     * @param actionUserId id of the user that sent the request for this mentorship relation
     * @param sentByMe indication if the current user was the action user
     * @param mentor user with mentor role in the relation
     * @param mentee user with mentee role in the relation
     * @param creationDate date of creation unix timestamp
     * @param acceptDate date of acceptance unix timestamp
     * @param startDate start date unix timestamp
     * @param endDate end date unix timestamp
     * @param state state of the relation (@link to RelationState)
     * @param notes notes related to the mentorship relation
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<org.anitab.mentorship.models.Relationship> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.anitab.mentorship.models.Relationship[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.anitab.mentorship.models.Relationship createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
    
    /**
     * This data class represents partial information of user of the system.
     * This is used in APIs not directly related with Users, such as in responses
     * related to MentorshipRelation
     *
     * @param id identifier of the user
     * @param name name of the user
     */
    @kotlinx.android.parcel.Parcelize()
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lorg/anitab/mentorship/models/Relationship$RelationUserResponse;", "Landroid/os/Parcelable;", "id", "", "name", "", "(ILjava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"})
    public static final class RelationUserResponse implements android.os.Parcelable {
        private final int id = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        public static final android.os.Parcelable.Creator<org.anitab.mentorship.models.Relationship.RelationUserResponse> CREATOR = null;
        
        public final int getId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        public RelationUserResponse(int id, @org.jetbrains.annotations.NotNull()
        java.lang.String name) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        /**
         * This data class represents partial information of user of the system.
         * This is used in APIs not directly related with Users, such as in responses
         * related to MentorshipRelation
         *
         * @param id identifier of the user
         * @param name name of the user
         */
        @org.jetbrains.annotations.NotNull()
        public final org.anitab.mentorship.models.Relationship.RelationUserResponse copy(int id, @org.jetbrains.annotations.NotNull()
        java.lang.String name) {
            return null;
        }
        
        /**
         * This data class represents partial information of user of the system.
         * This is used in APIs not directly related with Users, such as in responses
         * related to MentorshipRelation
         *
         * @param id identifier of the user
         * @param name name of the user
         */
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        /**
         * This data class represents partial information of user of the system.
         * This is used in APIs not directly related with Users, such as in responses
         * related to MentorshipRelation
         *
         * @param id identifier of the user
         * @param name name of the user
         */
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        /**
         * This data class represents partial information of user of the system.
         * This is used in APIs not directly related with Users, such as in responses
         * related to MentorshipRelation
         *
         * @param id identifier of the user
         * @param name name of the user
         */
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 3)
        public static final class Creator implements android.os.Parcelable.Creator<org.anitab.mentorship.models.Relationship.RelationUserResponse> {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public final org.anitab.mentorship.models.Relationship.RelationUserResponse[] newArray(int size) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public final org.anitab.mentorship.models.Relationship.RelationUserResponse createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
        }
    }
}