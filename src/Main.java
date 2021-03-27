import data.Project;
import service.ProjectService;
import service.impl.ProjectServiceImpl;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {


    public static void main(String[] args) throws ParseException {

        ProjectService projectService = new ProjectServiceImpl();

        List<Project> projects = projectService.fillData();

        int workingDays = 0;
        int maxWorkingDays = Integer.MIN_VALUE;

        int firstEmployeeId = 0;
        int secondEmployeeId = 0;

        for (int i = 0; i < projects.size(); i++) {

            Project firstEmpProject = projects.get(i);

            int firstEmpProjectId = firstEmpProject.getProjectId();

            Date firstEmpDateFrom = firstEmpProject.getDateFrom();
            Date firstEmpDateTo = firstEmpProject.getDateTo();

            for (int j = i + 1; j < projects.size(); j++) {

                Project secondEmpProject = projects.get(j);

                int secondEmpProjectProjectId = secondEmpProject.getProjectId();

                Date secondEmpDateFrom = secondEmpProject.getDateFrom();
                Date secondEmpDateTo = secondEmpProject.getDateTo();

                if (firstEmpProjectId == secondEmpProjectProjectId) {

                    if (isWorkingTogether(firstEmpDateTo, secondEmpDateFrom)) {

                        if (getWhenTheyWorkTogetherF(firstEmpDateFrom, firstEmpDateTo, secondEmpDateTo)) {
                            workingDays = getWorkingDaysTogether(firstEmpDateFrom, secondEmpDateTo);
                        }else if (getWhenTheyWorkTogetherS(firstEmpDateFrom, firstEmpDateFrom, secondEmpDateTo)){
                            workingDays = getWorkingDaysTogether(secondEmpDateFrom, firstEmpDateTo);
                        }
                        if (workingDays > maxWorkingDays) {
                            maxWorkingDays = workingDays;
                            firstEmployeeId = firstEmpProject.getEmployeeId();
                            secondEmployeeId = secondEmpProject.getEmployeeId();
                        }
                    }
                }
            }
        }
        System.out.printf("Двойка служители, които най-дълго време са работили в екип: [%d %d] - %d дни",
                firstEmployeeId, secondEmployeeId, maxWorkingDays);
    }

    private static int getWorkingDaysTogether(Date firstDate, Date secondDate) {
        return (int) ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
    }

    private static boolean isWorkingTogether(Date firstDate, Date secondDate) {
        return secondDate.before(firstDate);
    }

    private static boolean getWhenTheyWorkTogetherF(Date firstDate, Date secondDate, Date thirdDate){
        return firstDate.before(thirdDate)
                && secondDate.after(thirdDate);
    }

    private static boolean getWhenTheyWorkTogetherS(Date firstDate, Date secondDate, Date thirdDate){
        return firstDate.before(thirdDate)
                && secondDate.before(thirdDate);
    }

}
