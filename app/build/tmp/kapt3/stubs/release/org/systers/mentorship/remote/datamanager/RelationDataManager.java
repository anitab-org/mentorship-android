package org.systers.mentorship.remote.datamanager;

import java.lang.System;

/**
 * * This class represents the data manager related to Mentorship Relation API
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0006J\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0006J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006J\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0006J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/systers/mentorship/remote/datamanager/RelationDataManager;", "", "()V", "apiManager", "Lorg/systers/mentorship/remote/ApiManager;", "acceptRelationship", "Lio/reactivex/Observable;", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "relationId", "", "cancelRelationship", "deleteRelationship", "getAllPendingRelationsAndRequests", "", "Lorg/systers/mentorship/models/Relationship;", "getAllRelationsAndRequests", "getCurrentRelationship", "getPastRelationships", "rejectRelationship", "sendRequest", "relationshipRequest", "Lorg/systers/mentorship/remote/requests/RelationshipRequest;", "app_release"})
public final class RelationDataManager {
    private final org.systers.mentorship.remote.ApiManager apiManager = null;
    
    /**
     * * This will call a method of RelationService interface to fetch
     *     * all mentorship requests and relations
     *     * @return an Observable of a list of [Relationship]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<org.systers.mentorship.models.Relationship>> getAllRelationsAndRequests() {
        return null;
    }
    
    /**
     * * This will call a method of RelationService interface to fetch
     *     * all pending mentorship requests and relations
     *     * @return an Observable of a list of [Relationship]s
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<org.systers.mentorship.models.Relationship>> getAllPendingRelationsAndRequests() {
        return null;
    }
    
    /**
     * * This will call a method of RelationService interface to fetch
     *     * past mentorship requests and relations
     *     * @return an Observable of a list of [Relationship]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<org.systers.mentorship.models.Relationship>> getPastRelationships() {
        return null;
    }
    
    /**
     * * This will call a method from RelationService interface to accept a mentorship request
     *     * @param relationId id of the request being accepted
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> acceptRelationship(int relationId) {
        return null;
    }
    
    /**
     * * This will call a method from RelationService interface to reject a mentorship request
     *     * @param relationId id of the request being rejected
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> rejectRelationship(int relationId) {
        return null;
    }
    
    /**
     * * This will call a method from RelationService interface to delete a mentorship request
     *     * @param relationId id of the request being deleted
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> deleteRelationship(int relationId) {
        return null;
    }
    
    /**
     * * This will call a method from RelationService interface to cancel a mentorship relation
     *     * @param relationId id of the request being canceled
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> cancelRelationship(int relationId) {
        return null;
    }
    
    /**
     * * This will call a method from RelationService interface to send mentorship request
     *     * @param relationshipRequest object with fields to send a mentorship request
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> sendRequest(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.remote.requests.RelationshipRequest relationshipRequest) {
        return null;
    }
    
    /**
     * * This will call a method from RelationService interface to get accepted mentorship relation
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.models.Relationship> getCurrentRelationship() {
        return null;
    }
    
    public RelationDataManager() {
        super();
    }
}