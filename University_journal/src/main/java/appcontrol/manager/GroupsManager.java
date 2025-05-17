package appcontrol.manager;

import appcontrol.visual.services.Services;
import database.access.GroupsAccess;
import database.access.StudentsAccess;
import university.group.Group;
import university.student.Student;

import java.util.ArrayList;

public class GroupsManager {

    public static void addGroup() {
        System.out.println("Enter group's name:");
        String groupName = Services.getInput();
        Group group = new Group(groupName);
        GroupsAccess.add(group);
    }

    public static void printInfo() {
        if (noGroups()) {
            System.out.println("No groups");
        } else {
            printGroups();
        }

        System.out.println("\nPress any key to go back");
        Services.getInput();
    }

    public static boolean noGroups() {
        boolean noGroups;

        ArrayList<Group> groups = GroupsAccess.getAll();
        noGroups = groups.isEmpty();

        return noGroups;
    }

    public static void printGroups() {
        ArrayList<Group> groups = GroupsAccess.getAll();
        for (Group group : groups) {
            System.out.print(group);
        }
    }
}
