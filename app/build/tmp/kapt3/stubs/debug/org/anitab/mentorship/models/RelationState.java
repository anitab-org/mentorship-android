package org.anitab.mentorship.models;

import java.lang.System;

/**
 * This [Enum] represents all the states of a Mentorship Relation.
 * These values are the same used in the backend.
 * PENDING   - first applied state when a user sends a request
 * ACCEPTED  - when the receiving user accepts the relation, which will start at the time
 *            of acceptance
 * REJECTED  - when the receiving user rejects the relation, no longer available to be accepted
 * CANCELLED - when any of the users of a current relation cancel a relation
 * COMPLETED - when a current relation passes the end date it becomes completed,
 *            this happens automatically, does not require action from any user
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lorg/anitab/mentorship/models/RelationState;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "PENDING", "ACCEPTED", "REJECTED", "CANCELLED", "COMPLETED", "app_debug"})
public enum RelationState {
    /*public static final*/ PENDING /* = new PENDING(0) */,
    /*public static final*/ ACCEPTED /* = new ACCEPTED(0) */,
    /*public static final*/ REJECTED /* = new REJECTED(0) */,
    /*public static final*/ CANCELLED /* = new CANCELLED(0) */,
    /*public static final*/ COMPLETED /* = new COMPLETED(0) */;
    private final int value = 0;
    
    public final int getValue() {
        return 0;
    }
    
    RelationState(int value) {
    }
}