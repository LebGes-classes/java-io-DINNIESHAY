package appcontrol.manager;

import appcontrol.visual.services.Services;
import database.access.ScheduleAccess;
import university.lesson.Lesson;

import java.util.ArrayList;

public class ScheduleManager {

    public static void printScheduleForGroup() {
        System.out.println("Choose group to see schedule:");
        if (GroupsManager.noGroups()) {
            System.out.println("No groups");
            System.out.println("\nPress any key to go back");
            Services.getInput();
            return;
        }
        GroupsManager.printGroups();
        int groupId = Integer.parseInt(Services.getInput());

        ArrayList<Lesson> lessons = ScheduleAccess.getLessonsOfGroup(groupId);
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }
}
