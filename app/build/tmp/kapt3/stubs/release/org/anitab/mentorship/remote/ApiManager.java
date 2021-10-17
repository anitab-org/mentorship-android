package org.anitab.mentorship.remote;

import java.lang.System;

/**
 * A class that represents API Manager. It encapsulates three services: authentication, relation and user.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0014"}, d2 = {"Lorg/anitab/mentorship/remote/ApiManager;", "", "()V", "authService", "Lorg/anitab/mentorship/remote/services/AuthService;", "getAuthService", "()Lorg/anitab/mentorship/remote/services/AuthService;", "relationService", "Lorg/anitab/mentorship/remote/services/RelationService;", "getRelationService", "()Lorg/anitab/mentorship/remote/services/RelationService;", "taskService", "Lorg/anitab/mentorship/remote/services/TaskService;", "getTaskService", "()Lorg/anitab/mentorship/remote/services/TaskService;", "userService", "Lorg/anitab/mentorship/remote/services/UserService;", "getUserService", "()Lorg/anitab/mentorship/remote/services/UserService;", "Companion", "app_release"})
public final class ApiManager {
    @org.jetbrains.annotations.NotNull()
    private final org.anitab.mentorship.remote.services.AuthService authService = null;
    @org.jetbrains.annotations.NotNull()
    private final org.anitab.mentorship.remote.services.RelationService relationService = null;
    @org.jetbrains.annotations.NotNull()
    private final org.anitab.mentorship.remote.services.UserService userService = null;
    @org.jetbrains.annotations.NotNull()
    private final org.anitab.mentorship.remote.services.TaskService taskService = null;
    private static org.anitab.mentorship.remote.ApiManager apiManager;
    @org.jetbrains.annotations.NotNull()
    public static final org.anitab.mentorship.remote.ApiManager.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.remote.services.AuthService getAuthService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.remote.services.RelationService getRelationService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.remote.services.UserService getUserService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.remote.services.TaskService getTaskService() {
        return null;
    }
    
    public ApiManager() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lorg/anitab/mentorship/remote/ApiManager$Companion;", "", "()V", "apiManager", "Lorg/anitab/mentorship/remote/ApiManager;", "instance", "getInstance", "()Lorg/anitab/mentorship/remote/ApiManager;", "app_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final org.anitab.mentorship.remote.ApiManager getInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}