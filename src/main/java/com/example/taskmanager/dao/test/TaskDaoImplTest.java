package com.example.taskmanager.dao.test;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.dao.impl.TaskDaoImpl;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Priority;
import com.example.taskmanager.model.TaskStatus;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskDaoImplTest {

    private static TaskDao taskDao;

    @BeforeAll
    static void setUp() {
        taskDao = new TaskDaoImpl();
        // Initialize test database
    }

    @AfterAll
    static void tearDown() {
        // Clean up test database
    }

    @Test
    void testCreateAndFindTask() {
        Task task = new Task("Test Task", "Description", Priority.MEDIUM, TaskStatus.NOT_STARTED, LocalDate.now(), LocalDate.now().plusDays(7), 1);
        taskDao.createTask(task);

        Task foundTask = taskDao.findTaskById(task.getId());
        assertNotNull(foundTask);
        assertEquals("Test Task", foundTask.getTitle());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("Update Task", "Description", Priority.LOW, TaskStatus.NOT_STARTED, LocalDate.now(), LocalDate.now().plusDays(7), 1);
        taskDao.createTask(task);

        task.setTitle("Updated Task");
        taskDao.updateTask(task);

        Task updatedTask = taskDao.findTaskById(task.getId());
        assertEquals("Updated Task", updatedTask.getTitle());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("Delete Task", "Description", Priority.HIGH, TaskStatus.NOT_STARTED, LocalDate.now(), LocalDate.now().plusDays(7), 1);
        taskDao.createTask(task);

        taskDao.deleteTask(task.getId());

        Task deletedTask = taskDao.findTaskById(task.getId());
        assertNull(deletedTask);
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = taskDao.getAllTasks(1, 10);
        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
    }

    @Test
    void testGetTasksByProjectId() {
        List<Task> tasks = taskDao.getTasksByProjectId(1, 1, 10);
        assertNotNull(tasks);
    }

    @Test
    void testAssignTaskToMember() {
        Task task = new Task("Assign Task", "Description", Priority.MEDIUM, TaskStatus.NOT_STARTED, LocalDate.now(), LocalDate.now().plusDays(7), 1);
        taskDao.createTask(task);

        taskDao.assignTaskToMember(task.getId(), 1);

        Task assignedTask = taskDao.findTaskById(task.getId());
        assertNotNull(assignedTask.getAssignedMemberId());
        assertEquals(1, assignedTask.getAssignedMemberId());
    }

    @Test
    void testUpdateTaskStatus() {
        Task task = new Task("Status Task", "Description", Priority.HIGH, TaskStatus.NOT_STARTED, LocalDate.now(), LocalDate.now().plusDays(7), 1);
        taskDao.createTask(task);

        taskDao.updateTaskStatus(task.getId(), TaskStatus.IN_PROGRESS.name());

        Task updatedTask = taskDao.findTaskById(task.getId());
        assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());
    }
}