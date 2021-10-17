package org.anitab.mentorship.remote.datamanager;

import java.lang.System;

/**
 * This class represents the data manager related to Mentorship Task API
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J!\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lorg/anitab/mentorship/remote/datamanager/TaskDataManager;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "apiManager", "Lorg/anitab/mentorship/remote/ApiManager;", "addTask", "Lorg/anitab/mentorship/remote/responses/CustomResponse;", "relationId", "", "createTask", "Lorg/anitab/mentorship/remote/requests/CreateTask;", "(ILorg/anitab/mentorship/remote/requests/CreateTask;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeTask", "taskId", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTasks", "", "Lorg/anitab/mentorship/models/Task;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class TaskDataManager {
    private final org.anitab.mentorship.remote.ApiManager apiManager = null;
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    
    /**
     * This will call a method from Taskservice interface to fetch all tasks
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllTasks(int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.Task>> p1) {
        return null;
    }
    
    /**
     * This will call a method from Taskservice interface to complete a task
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object completeTask(int relationId, int taskId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p2) {
        return null;
    }
    
    /**
     * This will call a method from Taskservice interface to add task for a relationship
     * @param relationId mentorship relation id
     * @param createTask object to serialize task description
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addTask(int relationId, @org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.remote.requests.CreateTask createTask, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p2) {
        return null;
    }
    
    public TaskDataManager(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    public TaskDataManager() {
        super();
    }
}