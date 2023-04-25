package com.demo.ngsoft.repositories;

import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.requestObjects.CreateTaskRequest;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.utils.TaskStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class TaskRepoTest {


    @Autowired
    private TaskRepo taskRepoUnderTest;

    @Autowired
    private UserRepo userRepoDbBuilder;

    private TaskStatus taskStatus = new TaskStatus();


    @BeforeAll
    void setDbTasksAndAssignees(){
        assignUser2Task();
    }

    @Test
    void getAllByAssignee() {
        // @BeforeAll  given set of DB 

        //when
        long assignee = 3;
        String ARCHIVED_status = taskStatus.getARCHIVED();
        List<Task> tasks =  taskRepoUnderTest.getAllByAssignee(assignee,ARCHIVED_status);

        //then
        assertThat(tasks).isNotNull();
        assertThat(tasks.size()).isEqualTo(2);

    }

    @Test
    void getAllByAssignee_taskWithRelevantAssigneeAndStatusIsNotArchived() {
        // @BeforeAll given set of DB 

        //when
        long assignee = 3;
        String ARCHIVED_status = taskStatus.getARCHIVED();
        List<Task> tasks =  taskRepoUnderTest.getAllByAssignee(assignee,ARCHIVED_status);

        //then
        for (Task task: tasks) {
            assertThat(task.getAssignee()).isNotNull();
            assertThat(task.getAssignee().getId()).isEqualTo(assignee);
            assertThat(task.getStatus()).isNotEqualTo(ARCHIVED_status);
        }
    }

    @Test
    void getAllByAssignee_taskWithStatusEquals_NotArchived() {
        // @BeforeAll given set of DB

        //when
        long assignee = 3;
        String ARCHIVED_status = taskStatus.getARCHIVED();
        List<Task> tasks =  taskRepoUnderTest.getAllByAssignee(assignee,ARCHIVED_status);

        //then get correct list with correct status
        assertThat(tasks.size()).isEqualTo(2);// list size before any change --> size = 2
        for (Task task: tasks) {
            assertThat(task.getStatus()).isNotEqualTo(ARCHIVED_status);
        }

        //when set status to ARCHIVED_status in task id=3
        Task taskWithStatusArch = taskRepoUnderTest.getReferenceById(3L);
        taskWithStatusArch.setStatus(ARCHIVED_status);
        taskRepoUnderTest.save(taskWithStatusArch);

        //then get correct list with correct status
        tasks =  taskRepoUnderTest.getAllByAssignee(assignee,ARCHIVED_status);
        assertThat(tasks.size()).isEqualTo(1);// list size decrease in one object --> size = 1

        for (Task task: tasks) {
            assertThat(task.getStatus()).isNotEqualTo(ARCHIVED_status);
        }


    }


    @Test
    void updateTaskToComplete() {
        // @BeforeAll  given set of DB

        //when
        String status_complete = taskStatus.getCOMPLETED();
        Task taskWithStatusNotComplete = taskRepoUnderTest.getReferenceById(3L);
        //check status is not complete
        assertThat(taskWithStatusNotComplete.getStatus()).isNotEqualTo(status_complete);

        //then
        taskRepoUnderTest.updateTaskToComplete(3L,status_complete);
        Task taskWithStatusComplete = taskRepoUnderTest.getReferenceById(3L);
        assertThat(taskWithStatusComplete.getStatus()).isEqualTo(status_complete);
    }






    /// **************************  set of DB methods **************************  ///
    public void assignUser2Task(){
       setUsersToDb();
        createTasks();
    User user1 = userRepoDbBuilder.getReferenceById(1L);
    User user2 = userRepoDbBuilder.getReferenceById(2L);
    User user3 = userRepoDbBuilder.getReferenceById(3L);
    User user4 = userRepoDbBuilder.getReferenceById(4L);
    User user5 = userRepoDbBuilder.getReferenceById(5L);
    Task task1 = taskRepoUnderTest.getReferenceById(1L);
    Task task2 = taskRepoUnderTest.getReferenceById(2L);
    Task task3 = taskRepoUnderTest.getReferenceById(3L);
    Task task4 = taskRepoUnderTest.getReferenceById(4L);
    Task task5 = taskRepoUnderTest.getReferenceById(5L);
    Task task6 = taskRepoUnderTest.getReferenceById(6L);
    Task task7 = taskRepoUnderTest.getReferenceById(7L);
    Task task8 = taskRepoUnderTest.getReferenceById(8L);

        task1.setAssignee(user1);
        taskRepoUnderTest.save(task1);
        task2.setAssignee(user2);
        taskRepoUnderTest.save(task2);
        task3.setAssignee(user3);
        taskRepoUnderTest.save(task3);
        task4.setAssignee(user4);
        taskRepoUnderTest.save(task4);
        task5.setAssignee(user5);
        taskRepoUnderTest.save(task5);
        task6.setAssignee(user3);
        taskRepoUnderTest.save(task6);
        task7.setAssignee(user4);
        taskRepoUnderTest.save(task7);
        task8.setAssignee(user5);
        taskRepoUnderTest.save(task8);

        System.out.println(taskRepoUnderTest.findAll());

    }

    public void setUsersToDb(){

        CreateUserRequest userRequest1 = new CreateUserRequest(
                "oren","oren@email1",
                true,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest1));

        CreateUserRequest userRequest2 = new CreateUserRequest(
                "avivit","avivit@email1",
                true,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest2));

        CreateUserRequest userRequest3 = new CreateUserRequest(
                "maya","maya@email1",
                false,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest3));

        CreateUserRequest userRequest4 = new CreateUserRequest(
                "Daniel","Daniel@email1",
                false,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest4));

        CreateUserRequest userRequest5 = new CreateUserRequest(
                "raz","raz@email1",
                false,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest5));


        System.out.println(
                userRepoDbBuilder.findAll()
        );

    }

    public void createTasks(){
        CreateTaskRequest task1 = new CreateTaskRequest(
                "task1",
                "task1",
                "PENDING"
        );
        taskRepoUnderTest.save(new Task(task1));

        CreateTaskRequest task2 = new CreateTaskRequest(
                "task2",
                "task2",
                "PENDING"
        );
        taskRepoUnderTest.save(new Task(task2));

        CreateTaskRequest task3 = new CreateTaskRequest(
                "task3",
                "task3",
                "PENDING"
        );
        taskRepoUnderTest.save(new Task(task3));

        CreateTaskRequest task4 = new CreateTaskRequest(
                "task4",
                "task4",
                "PENDING"
        );
        taskRepoUnderTest.save(new Task(task4));

        CreateTaskRequest task5 = new CreateTaskRequest(
                "task5",
                "task5",
                "PENDING"
        );
        taskRepoUnderTest.save(new Task(task5));

        CreateTaskRequest task6 = new CreateTaskRequest(
                "task6",
                "task6",
                "PENDING"
        );
        taskRepoUnderTest.save(new Task(task6));

        CreateTaskRequest task7 = new CreateTaskRequest(
                "task7",
                "task7",
                "PENDING"
        );
        taskRepoUnderTest.save(new Task(task7));


        CreateTaskRequest task8 = new CreateTaskRequest(
                "task8",
                "task8",
                "PENDING"
        );
        taskRepoUnderTest.save(new Task(task8));
    }
}