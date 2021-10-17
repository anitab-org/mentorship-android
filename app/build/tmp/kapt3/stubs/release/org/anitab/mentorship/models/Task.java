package org.anitab.mentorship.models;

import java.lang.System;

/**
 * This data class represents a task related to a mentorship
 * relation.
 *
 * @param id The id of this task
 * @param description The description of this task
 * @param isDone Represents whether this task has been completed
 * @param createdAt Unix timestamp of when this task was created
 * @param completedAt Unix timestamp of when this task was completed
 */
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ8\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001bJ\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001J\u0013\u0010\u001d\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001J\t\u0010!\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0015\u00a8\u0006\'"}, d2 = {"Lorg/anitab/mentorship/models/Task;", "Landroid/os/Parcelable;", "id", "", "description", "", "createdAt", "", "completedAt", "(ILjava/lang/String;FLjava/lang/Float;)V", "getCompletedAt", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getCreatedAt", "()F", "getDescription", "()Ljava/lang/String;", "getId", "()I", "isDone", "", "()Z", "component1", "component2", "component3", "component4", "copy", "(ILjava/lang/String;FLjava/lang/Float;)Lorg/anitab/mentorship/models/Task;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"})
public final class Task implements android.os.Parcelable {
    private final boolean isDone = false;
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    private final float createdAt = 0.0F;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float completedAt = null;
    public static final android.os.Parcelable.Creator<org.anitab.mentorship.models.Task> CREATOR = null;
    
    public final boolean isDone() {
        return false;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final float getCreatedAt() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getCompletedAt() {
        return null;
    }
    
    public Task(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String description, float createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.Float completedAt) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float component4() {
        return null;
    }
    
    /**
     * This data class represents a task related to a mentorship
     * relation.
     *
     * @param id The id of this task
     * @param description The description of this task
     * @param isDone Represents whether this task has been completed
     * @param createdAt Unix timestamp of when this task was created
     * @param completedAt Unix timestamp of when this task was completed
     */
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.models.Task copy(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String description, float createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.Float completedAt) {
        return null;
    }
    
    /**
     * This data class represents a task related to a mentorship
     * relation.
     *
     * @param id The id of this task
     * @param description The description of this task
     * @param isDone Represents whether this task has been completed
     * @param createdAt Unix timestamp of when this task was created
     * @param completedAt Unix timestamp of when this task was completed
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    /**
     * This data class represents a task related to a mentorship
     * relation.
     *
     * @param id The id of this task
     * @param description The description of this task
     * @param isDone Represents whether this task has been completed
     * @param createdAt Unix timestamp of when this task was created
     * @param completedAt Unix timestamp of when this task was completed
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * This data class represents a task related to a mentorship
     * relation.
     *
     * @param id The id of this task
     * @param description The description of this task
     * @param isDone Represents whether this task has been completed
     * @param createdAt Unix timestamp of when this task was created
     * @param completedAt Unix timestamp of when this task was completed
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
    public static final class Creator implements android.os.Parcelable.Creator<org.anitab.mentorship.models.Task> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.anitab.mentorship.models.Task[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.anitab.mentorship.models.Task createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
}