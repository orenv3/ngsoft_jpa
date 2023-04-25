package com.demo.ngsoft.repositories;

import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.requestObjects.CreateTaskRequest;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class CommentRepoTest {


    @Autowired
    private CommentRepo CommentRepoUnderTest;

    @Autowired
    private TaskRepo taskRepoDbBuilder;

    @Autowired
    private UserRepo userRepoDbBuilder;;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findByTaskId_TitleIn() {
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
        Task task1 = taskRepoDbBuilder.getReferenceById(1L);
        Task task2 = taskRepoDbBuilder.getReferenceById(2L);
        Task task3 = taskRepoDbBuilder.getReferenceById(3L);
        Task task4 = taskRepoDbBuilder.getReferenceById(4L);
        Task task5 = taskRepoDbBuilder.getReferenceById(5L);
        Task task6 = taskRepoDbBuilder.getReferenceById(6L);
        Task task7 = taskRepoDbBuilder.getReferenceById(7L);
        Task task8 = taskRepoDbBuilder.getReferenceById(8L);

        task1.setAssignee(user1);
        taskRepoDbBuilder.save(task1);
        task2.setAssignee(user2);
        taskRepoDbBuilder.save(task2);
        task3.setAssignee(user3);
        taskRepoDbBuilder.save(task3);
        task4.setAssignee(user4);
        taskRepoDbBuilder.save(task4);
        task5.setAssignee(user5);
        taskRepoDbBuilder.save(task5);
        task6.setAssignee(user3);
        taskRepoDbBuilder.save(task6);
        task7.setAssignee(user4);
        taskRepoDbBuilder.save(task7);
        task8.setAssignee(user5);
        taskRepoDbBuilder.save(task8);

        System.out.println(taskRepoDbBuilder.findAll());

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
        taskRepoDbBuilder.save(new Task(task1));

        CreateTaskRequest task2 = new CreateTaskRequest(
                "task2",
                "task2",
                "PENDING"
        );
        taskRepoDbBuilder.save(new Task(task2));

        CreateTaskRequest task3 = new CreateTaskRequest(
                "task3",
                "task3",
                "PENDING"
        );
        taskRepoDbBuilder.save(new Task(task3));

        CreateTaskRequest task4 = new CreateTaskRequest(
                "task4",
                "task4",
                "PENDING"
        );
        taskRepoDbBuilder.save(new Task(task4));

        CreateTaskRequest task5 = new CreateTaskRequest(
                "task5",
                "task5",
                "PENDING"
        );
        taskRepoDbBuilder.save(new Task(task5));

        CreateTaskRequest task6 = new CreateTaskRequest(
                "task6",
                "task6",
                "PENDING"
        );
        taskRepoDbBuilder.save(new Task(task6));

        CreateTaskRequest task7 = new CreateTaskRequest(
                "task7",
                "task7",
                "PENDING"
        );
        taskRepoDbBuilder.save(new Task(task7));


        CreateTaskRequest task8 = new CreateTaskRequest(
                "task8",
                "task8",
                "PENDING"
        );
        taskRepoDbBuilder.save(new Task(task8));
    }
}