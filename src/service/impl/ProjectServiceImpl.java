package service.impl;

import data.Project;
import service.ProjectService;
import util.FileReader;

import java.text.ParseException;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private final FileReader fileReader;

    public ProjectServiceImpl() {
        this.fileReader = new FileReader();
    }


    @Override
    public List<Project> fillData() throws ParseException {
        return this.fileReader.readFile();

    }

}
