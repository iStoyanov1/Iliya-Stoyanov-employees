package repository;

import data.Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectRepository {

    private List<Project> projects;

    public ProjectRepository() {
        this.projects = new ArrayList<>();
    }

    public void addProject(Project project){
        this.projects.add(project);
    }

    public List<Project> getAllProjects(){
        return this.projects;
    }

}
