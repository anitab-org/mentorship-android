package org.anitab.mentorship.remote.services;

import java.lang.System;

/**
 * This interface describes the methods related to Mentorship Task REST API
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ%\u0010\t\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lorg/anitab/mentorship/remote/services/TaskService;", "", "addTaskToMentorshipRelation", "Lorg/anitab/mentorship/remote/responses/CustomResponse;", "relationId", "", "createTask", "Lorg/anitab/mentorship/remote/requests/CreateTask;", "(ILorg/anitab/mentorship/remote/requests/CreateTask;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeTaskFromMentorshipRelation", "taskId", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTasksFromMentorshipRelation", "", "Lorg/anitab/mentorship/models/Task;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface TaskService {
    
    /**
     * This function gets all the tasks from a mentorship relation
     * @param relationId id of the mentorship relation in question
     * @return an observable instance of a list of [Task]s
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "mentorship_relation/{relation_id}/tasks")
    public abstract java.lang.Object getAllTasksFromMentorshipRelation(@retrofit2.http.Path(value = "relation_id")
    int relationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.Task>> p1);
    
    /**
     * This function marks a task from a mentorship relation as complete
     * @param relationId id of the mentorship relation in question
     * @param taskId id of the task in question
     * @return an observable instance of <CustomResponse>
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.PUT(value = "mentorship_relation/{relation_id}/task/{task_id}/complete")
    public abstract java.lang.Object completeTaskFromMentorshipRelation(@retrofit2.http.Path(value = "relation_id")
    int relationId, @retrofit2.http.Path(value = "task_id")
    int taskId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p2);
    
    /**
     * This function creates a new task
     * @param relationId id of the mentorship relation in question
     * @param relationId mentorship relation id
     * @param createTask object to serialize task description
     * @return an observable instance of <CustomResponse>
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "mentorship_relation/{relation_id}/task")
    public abstract java.lang.Object addTaskToMentorshipRelation(@retrofit2.http.Path(value = "relation_id")
    int relationId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.anitab.mentorship.remote.requests.CreateTask createTask, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p2);
}