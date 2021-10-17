package org.anitab.mentorship.remote.datamanager;

import java.lang.System;

/**
 * This class represents the data manager related to Mentorship Relation API
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lorg/anitab/mentorship/remote/datamanager/RelationDataManager;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "apiManager", "Lorg/anitab/mentorship/remote/ApiManager;", "acceptRelationship", "Lorg/anitab/mentorship/remote/responses/CustomResponse;", "relationId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelRelationship", "deleteRelationship", "getAllPendingRelationsAndRequests", "", "Lorg/anitab/mentorship/models/Relationship;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllRelationsAndRequests", "getCurrentRelationship", "getPastRelationships", "rejectRelationship", "sendRequest", "relationshipRequest", "Lorg/anitab/mentorship/remote/requests/RelationshipRequest;", "(Lorg/anitab/mentorship/remote/requests/RelationshipRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RelationDataManager {
    private final org.anitab.mentorship.remote.ApiManager apiManager = null;
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    
    /**
     * This will call a method of RelationService interface to fetch
     * all mentorship requests and relations
     * @return an Observable of a list of [Relationship]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllRelationsAndRequests(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.Relationship>> p0) {
        return null;
    }
    
    /**
     * This will call a method of RelationService interface to fetch
     * all pending mentorship requests and relations
     * @return an Observable of a list of [Relationship]s
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllPendingRelationsAndRequests(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.Relationship>> p0) {
        return null;
    }
    
    /**
     * This will call a method of RelationService interface to fetch
     * past mentorship requests and relations
     * @return an Observable of a list of [Relationship]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPastRelationships(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.Relationship>> p0) {
        return null;
    }
    
    /**
     * This will call a method from RelationService interface to accept a mentorship request
     * @param relationId id of the request being accepted
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object acceptRelationship(int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1) {
        return null;
    }
    
    /**
     * This will call a method from RelationService interface to reject a mentorship request
     * @param relationId id of the request being rejected
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object rejectRelationship(int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1) {
        return null;
    }
    
    /**
     * This will call a method from RelationService interface to delete a mentorship request
     * @param relationId id of the request being deleted
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteRelationship(int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1) {
        return null;
    }
    
    /**
     * This will call a method from RelationService interface to cancel a mentorship relation
     * @param relationId id of the request being canceled
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object cancelRelationship(int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1) {
        return null;
    }
    
    /**
     * This will call a method from RelationService interface to send mentorship request
     * @param relationshipRequest object with fields to send a mentorship request
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendRequest(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.remote.requests.RelationshipRequest relationshipRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1) {
        return null;
    }
    
    /**
     * This will call a method from RelationService interface to get accepted mentorship relation
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCurrentRelationship(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.models.Relationship> p0) {
        return null;
    }
    
    public RelationDataManager(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    public RelationDataManager() {
        super();
    }
}