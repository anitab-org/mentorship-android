package org.systers.mentorship.models;

import java.lang.System;

/**
 * * This class represents statistics of the user actions on the app.
 * * @param name the name of the user
 * * @param pendingRequests number of pending requests
 * * @param acceptedRequests number of accepted requests
 * * @param completedRelations number of completed relations
 * * @param cancelledRelations number of cancelled relations
 * * @param rejectedRequests number of rejected requests
 * * @param achievements a list of up-to 3 completed tasks
 */
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0001J\t\u0010 \u001a\u00020\u0005H\u00d6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u00d6\u0003J\t\u0010%\u001a\u00020\u0005H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f\u00a8\u0006,"}, d2 = {"Lorg/systers/mentorship/models/HomeStatistics;", "Landroid/os/Parcelable;", "name", "", "pendingRequests", "", "acceptedRequests", "completedRelations", "cancelledRelations", "rejectedRequests", "achievements", "", "Lorg/systers/mentorship/models/Task;", "(Ljava/lang/String;IIIIILjava/util/List;)V", "getAcceptedRequests", "()I", "getAchievements", "()Ljava/util/List;", "getCancelledRelations", "getCompletedRelations", "getName", "()Ljava/lang/String;", "getPendingRequests", "getRejectedRequests", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
public final class HomeStatistics implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    private final int pendingRequests = 0;
    private final int acceptedRequests = 0;
    private final int completedRelations = 0;
    private final int cancelledRelations = 0;
    private final int rejectedRequests = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.systers.mentorship.models.Task> achievements = null;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final int getPendingRequests() {
        return 0;
    }
    
    public final int getAcceptedRequests() {
        return 0;
    }
    
    public final int getCompletedRelations() {
        return 0;
    }
    
    public final int getCancelledRelations() {
        return 0;
    }
    
    public final int getRejectedRequests() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.systers.mentorship.models.Task> getAchievements() {
        return null;
    }
    
    public HomeStatistics(@org.jetbrains.annotations.NotNull()
    java.lang.String name, int pendingRequests, int acceptedRequests, int completedRelations, int cancelledRelations, int rejectedRequests, @org.jetbrains.annotations.NotNull()
    java.util.List<org.systers.mentorship.models.Task> achievements) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.systers.mentorship.models.Task> component7() {
        return null;
    }
    
    /**
     * * This class represents statistics of the user actions on the app.
     * * @param name the name of the user
     * * @param pendingRequests number of pending requests
     * * @param acceptedRequests number of accepted requests
     * * @param completedRelations number of completed relations
     * * @param cancelledRelations number of cancelled relations
     * * @param rejectedRequests number of rejected requests
     * * @param achievements a list of up-to 3 completed tasks
     */
    @org.jetbrains.annotations.NotNull()
    public final org.systers.mentorship.models.HomeStatistics copy(@org.jetbrains.annotations.NotNull()
    java.lang.String name, int pendingRequests, int acceptedRequests, int completedRelations, int cancelledRelations, int rejectedRequests, @org.jetbrains.annotations.NotNull()
    java.util.List<org.systers.mentorship.models.Task> achievements) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
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
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
}