package org.systers.mentorship.remote.services;

import java.lang.System;

/**
 * * This interface describes the methods related to Mentorship Task REST API
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006H\'J\u001e\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u000e"}, d2 = {"Lorg/systers/mentorship/remote/services/TaskService;", "", "addTaskToMentorshipRelation", "Lio/reactivex/Observable;", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "relationId", "", "createTask", "Lorg/systers/mentorship/remote/requests/CreateTask;", "completeTaskFromMentorshipRelation", "taskId", "getAllTasksFromMentorshipRelation", "", "Lorg/systers/mentorship/models/Task;", "app_release"})
public abstract interface TaskService {
    
    /**
     * * This function gets all the tasks from a mentorship relation
     *     * @param relationId id of the mentorship relation in question
     *     * @return an observable instance of a list of [Task]s
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "mentorship_relation/{relation_id}/tasks")
    public abstract io.reactivex.Observable<java.util.List<org.systers.mentorship.models.Task>> getAllTasksFromMentorshipRelation(@retrofit2.http.Path(value = "relation_id")
    int relationId);
    
    /**
     * * This function marks a task from a mentorship relation as complete
     *     * @param relationId id of the mentorship relation in question
     *     * @param taskId id of the task in question
     *     * @return an observable instance of <CustomResponse>
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "mentorship_relation/{relation_id}/task/{task_id}/complete")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> completeTaskFromMentorshipRelation(@retrofit2.http.Path(value = "relation_id")
    int relationId, @retrofit2.http.Path(value = "task_id")
    int taskId);
    
    /**
     * * This function creates a new task
     *     * @param relationId id of the mentorship relation in question
     *     * @param relationId mentorship relation id
     *     * @param createTask object to serialize task description
     *     * @return an observable instance of <CustomResponse>
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "mentorship_relation/{relation_id}/task")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> addTaskToMentorshipRelation(@retrofit2.http.Path(value = "relation_id")
    int relationId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.systers.mentorship.remote.requests.CreateTask createTask);
}