package org.systers.mentorship.remote.services;

import java.lang.System;

/**
 * * This interface describes the methods related to Mentorship Relation REST API
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0003H\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0003H\'J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0003H\'J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\'\u00a8\u0006\u0013"}, d2 = {"Lorg/systers/mentorship/remote/services/RelationService;", "", "acceptRelationship", "Lio/reactivex/Observable;", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "relationId", "", "cancelRelationship", "deleteRelationship", "getAllPendingRelationships", "", "Lorg/systers/mentorship/models/Relationship;", "getAllRelationships", "getCurrentRelationship", "getPastRelationships", "rejectRelationship", "sendRequest", "relationshipRequest", "Lorg/systers/mentorship/remote/requests/RelationshipRequest;", "app_release"})
public abstract interface RelationService {
    
    /**
     * * This function returns all mentorship requests and relations of the current user
     *     * @return an observable instance of a list of [Relationship]s
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "mentorship_relations")
    public abstract io.reactivex.Observable<java.util.List<org.systers.mentorship.models.Relationship>> getAllRelationships();
    
    /**
     * * this function returns all pending mentorship requests and relations of the current user
     *     * @return an observable instance of a list of [Relationship]s
     *     * 
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "mentorship_relations/pending")
    public abstract io.reactivex.Observable<java.util.List<org.systers.mentorship.models.Relationship>> getAllPendingRelationships();
    
    /**
     * * this function returns all pending mentorship requests and relations of the current user
     *     * This function returns past mentorship requests and relations of the current user
     *     * @return an observable instance of a list of [Relationship]s
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "mentorship_relations/past")
    public abstract io.reactivex.Observable<java.util.List<org.systers.mentorship.models.Relationship>> getPastRelationships();
    
    /**
     * * This function performs the acceptance of a mentorship request
     *     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "mentorship_relation/{relation_id}/accept")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> acceptRelationship(@retrofit2.http.Path(value = "relation_id")
    int relationId);
    
    /**
     * * This function performs the rejection of a mentorship request
     *     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "mentorship_relation/{relation_id}/reject")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> rejectRelationship(@retrofit2.http.Path(value = "relation_id")
    int relationId);
    
    /**
     * * This function performs the deletion of a mentorship request
     *     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "mentorship_relation/{relation_id}")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> deleteRelationship(@retrofit2.http.Path(value = "relation_id")
    int relationId);
    
    /**
     * * This function performs the cancellation of a mentorship relation
     *     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "mentorship_relation/{relation_id}/cancel")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> cancelRelationship(@retrofit2.http.Path(value = "relation_id")
    int relationId);
    
    /**
     * * This function performs sending a mentorship request
     *     * @param relationshipRequest data required to send a mentorship request
     *     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "mentorship_relation/send_request")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> sendRequest(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.systers.mentorship.remote.requests.RelationshipRequest relationshipRequest);
    
    /**
     * * This function return the current mentorship relation
     *     * @return an observable instance of [Relationship]
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "mentorship_relations/current")
    public abstract io.reactivex.Observable<org.systers.mentorship.models.Relationship> getCurrentRelationship();
}