package util;

import data.Project;
import repository.ProjectRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileReader {

    private static final String FILE_NAME = "SirmaSolution.txt";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private final ProjectRepository projectRepository;

    public FileReader() {
        this.projectRepository = new ProjectRepository();
    }

    public List<Project> readFile() throws ParseException {

        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            System.exit(0);
        }

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            String[] data = line.split(", ");

            Date dateFrom = new SimpleDateFormat(DATE_PATTERN).parse(data[2]);
            Date dateTo = null;
            if (data[3].equals("NULL")) {
                LocalDateTime today = LocalDateTime.now();
                String pattern = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH).format(today);
                dateTo = new SimpleDateFormat(DATE_PATTERN).parse(pattern);
            } else {
                dateTo = new SimpleDateFormat(DATE_PATTERN).parse(data[3]);
            }


            Project project = new Project(Integer.parseInt(data[0]), Integer.parseInt(data[1]), dateFrom, dateTo);
            this.projectRepository.addProject(project);
        }
        return this.projectRepository.getAllProjects();
    }

}
