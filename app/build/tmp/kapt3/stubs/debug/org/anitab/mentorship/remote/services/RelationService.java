package org.anitab.mentorship.remote.services;

import java.lang.System;

/**
 * This interface describes the methods related to Mentorship Relation REST API
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\b\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0011\u0010\u000e\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0011\u001a\u00020\u00032\b\b\u0001\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lorg/anitab/mentorship/remote/services/RelationService;", "", "acceptRelationship", "Lorg/anitab/mentorship/remote/responses/CustomResponse;", "relationId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelRelationship", "deleteRelationship", "getAllPendingRelationships", "", "Lorg/anitab/mentorship/models/Relationship;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllRelationships", "getCurrentRelationship", "getPastRelationships", "rejectRelationship", "sendRequest", "relationshipRequest", "Lorg/anitab/mentorship/remote/requests/RelationshipRequest;", "(Lorg/anitab/mentorship/remote/requests/RelationshipRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface RelationService {
    
    /**
     * This function returns all mentorship requests and relations of the current user
     * @return an observable instance of a list of [Relationship]s
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "mentorship_relations")
    public abstract java.lang.Object getAllRelationships(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.Relationship>> p0);
    
    /**
     * this function returns all pending mentorship requests and relations of the current user
     * @return an observable instance of a list of [Relationship]s
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "mentorship_relations/pending")
    public abstract java.lang.Object getAllPendingRelationships(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.Relationship>> p0);
    
    /**
     * this function returns all pending mentorship requests and relations of the current user
     * This function returns past mentorship requests and relations of the current user
     * @return an observable instance of a list of [Relationship]s
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "mentorship_relations/past")
    public abstract java.lang.Object getPastRelationships(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.Relationship>> p0);
    
    /**
     * This function performs the acceptance of a mentorship request
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.PUT(value = "mentorship_relation/{relation_id}/accept")
    public abstract java.lang.Object acceptRelationship(@retrofit2.http.Path(value = "relation_id")
    int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1);
    
    /**
     * This function performs the rejection of a mentorship request
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.PUT(value = "mentorship_relation/{relation_id}/reject")
    public abstract java.lang.Object rejectRelationship(@retrofit2.http.Path(value = "relation_id")
    int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1);
    
    /**
     * This function performs the deletion of a mentorship request
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.DELETE(value = "mentorship_relation/{relation_id}")
    public abstract java.lang.Object deleteRelationship(@retrofit2.http.Path(value = "relation_id")
    int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1);
    
    /**
     * This function performs the cancellation of a mentorship relation
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.PUT(value = "mentorship_relation/{relation_id}/cancel")
    public abstract java.lang.Object cancelRelationship(@retrofit2.http.Path(value = "relation_id")
    int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1);
    
    /**
     * This function performs sending a mentorship request
     * @param relationshipRequest data required to send a mentorship request
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "mentorship_relation/send_request")
    public abstract java.lang.Object sendRequest(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.anitab.mentorship.remote.requests.RelationshipRequest relationshipRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1);
    
    /**
     * This function return the current mentorship relation
     * @return an observable instance of [Relationship]
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "mentorship_relations/current")
    public abstract java.lang.Object getCurrentRelationship(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.models.Relationship> p0);
}