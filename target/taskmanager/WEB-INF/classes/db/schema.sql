DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS teams;

CREATE TABLE teams (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role ENUM('PROJECT_MANAGER', 'DEVELOPER', 'DESIGNER') NOT NULL,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE SET NULL
);

CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    status ENUM('IN_PREPARATION', 'IN_PROGRESS', 'PAUSED', 'COMPLETED', 'CANCELED') NOT NULL,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE SET NULL
);

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    priority ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL,
    status ENUM('TO_DO', 'IN_PROGRESS', 'DONE') NOT NULL,
    creation_date DATE NOT NULL,
    due_date DATE NOT NULL,
    project_id INT,
    assigned_member_id INT,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (assigned_member_id) REFERENCES members(id) ON DELETE SET NULL
);

CREATE INDEX idx_project_name ON projects (name);
CREATE INDEX idx_task_title ON tasks (title);
CREATE INDEX idx_member_email ON members (email);

-- Example data insertion
INSERT INTO teams (name) VALUES ('Team Alpha'), ('Team Beta');

INSERT INTO members (first_name, last_name, email, role, team_id)
VALUES 
('John', 'Doe', 'john.doe@example.com', 'DEVELOPER', 1),
('Jane', 'Doe', 'jane.doe@example.com', 'DESIGNER', 2);

INSERT INTO projects (name, description, start_date, end_date, status, team_id)
VALUES 
('Project A', 'A sample project description', '2024-01-01', '2024-06-01', 'IN_PROGRESS', 1),
('Project B', 'Another project description', '2024-02-01', '2024-07-01', 'IN_PREPARATION', 2);

INSERT INTO tasks (title, description, priority, status, creation_date, due_date, project_id, assigned_member_id)
VALUES 
('Task 1', 'Task description 1', 'MEDIUM', 'TO_DO', '2024-01-05', '2024-02-05', 1, 1),
('Task 2', 'Task description 2', 'HIGH', 'IN_PROGRESS', '2024-01-10', '2024-03-01', 1, 2),
('Task 3', 'Task description 3', 'LOW', 'DONE', '2024-01-20', '2024-04-01', 2, NULL);