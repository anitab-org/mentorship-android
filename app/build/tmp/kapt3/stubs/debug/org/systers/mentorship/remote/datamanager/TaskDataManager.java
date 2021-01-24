package org.systers.mentorship.remote.datamanager;

import java.lang.System;

/**
 * * This class represents the data manager related to Mentorship Task API
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/systers/mentorship/remote/datamanager/TaskDataManager;", "", "()V", "apiManager", "Lorg/systers/mentorship/remote/ApiManager;", "addTask", "Lio/reactivex/Observable;", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "relationId", "", "createTask", "Lorg/systers/mentorship/remote/requests/CreateTask;", "completeTask", "taskId", "getAllTasks", "", "Lorg/systers/mentorship/models/Task;", "app_debug"})
public final class TaskDataManager {
    private final org.systers.mentorship.remote.ApiManager apiManager = null;
    
    /**
     * * This will call a method from Taskservice interface to fetch all tasks
     *     * @param relationId mentorship relation id
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<org.systers.mentorship.models.Task>> getAllTasks(int relationId) {
        return null;
    }
    
    /**
     * * This will call a method from Taskservice interface to complete a task
     *     * @param relationId mentorship relation id
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> completeTask(int relationId, int taskId) {
        return null;
    }
    
    /**
     * * This will call a method from Taskservice interface to add task for a relationship
     *     * @param relationId mentorship relation id
     *     * @param createTask object to serialize task description
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> addTask(int relationId, @org.jetbrains.annotations.NotNull()
    org.systers.mentorship.remote.requests.CreateTask createTask) {
        return null;
    }
    
    public TaskDataManager() {
        super();
    }
}