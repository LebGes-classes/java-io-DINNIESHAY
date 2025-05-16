package university.grade;

import java.io.Serializable;

public class Grade implements Serializable {

    private int id;
    private int subjectId;
    private int studentId;

    public Grade() {}

    public Grade(int id, int subjectId, int studentId) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Grade " +
                "id: " + id +
                ", subjectId: " + subjectId +
                ", studentId: " + studentId +
                "\n";
    }
}
