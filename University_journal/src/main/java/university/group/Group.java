package university.group;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

    private int id;
    private ArrayList<Integer> students;
    private int amountOfStudents;

    public Group() {}

    public Group(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Integer> students) {
        this.students = students;
    }

    public int getAmountOfStudents() {
        return amountOfStudents;
    }

    public void setAmountOfStudents(int amountOfStudents) {
        this.amountOfStudents = amountOfStudents;
    }

    public void addStudent(int studentId) {
        students.add(studentId);
    }

    public void deductStudent(int studentId) {
        students.remove(studentId);
    }
}
