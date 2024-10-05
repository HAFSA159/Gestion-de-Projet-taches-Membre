package com.example.taskmanager.dao.test;

import com.example.taskmanager.dao.ProjectDao;
import com.example.taskmanager.dao.impl.ProjectDaoImpl;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.Status;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoImplTest {

    private static ProjectDao projectDao;

    @BeforeAll
    static void setUp() {
        projectDao = new ProjectDaoImpl();
        // Initialize test database
    }

    @AfterAll
    static void tearDown() {
        // Clean up test database
    }

    @Test
    void testCreateAndFindProject() {
        Project project = new Project("Test Project", "Description", LocalDate.now(), LocalDate.now().plusMonths(1), Status.IN_PROGRESS);
        projectDao.createProject(project);

        Project foundProject = projectDao.findProjectById(project.getId());
        assertNotNull(foundProject);
        assertEquals("Test Project", foundProject.getName());
    }

    @Test
    void testUpdateProject() {
        Project project = new Project("Update Project", "Description", LocalDate.now(), LocalDate.now().plusMonths(1), Status.NOT_STARTED);
        projectDao.createProject(project);

        project.setName("Updated Project");
        projectDao.updateProject(project);

        Project updatedProject = projectDao.findProjectById(project.getId());
        assertEquals("Updated Project", updatedProject.getName());
    }

    @Test
    void testDeleteProject() {
        Project project = new Project("Delete Project", "Description", LocalDate.now(), LocalDate.now().plusMonths(1), Status.NOT_STARTED);
        projectDao.createProject(project);

        projectDao.deleteProject(project.getId());

        Project deletedProject = projectDao.findProjectById(project.getId());
        assertNull(deletedProject);
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = projectDao.getAllProjects(1, 10);
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
    }

    @Test
    void testSearchProjects() {
        List<Project> projects = projectDao.searchProjects("Test", 1, 10);
        assertNotNull(projects);
    }

    @Test
    void testGetProjectStatistics() {
        Project project = projectDao.getProjectStatistics(1);
        assertNotNull(project);
        assertNotNull(project.getTasks());
    }
}