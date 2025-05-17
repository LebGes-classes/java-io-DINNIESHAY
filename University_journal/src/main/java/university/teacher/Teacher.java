package university.teacher;

import java.io.Serializable;

public class Teacher implements Serializable {

    private int id;
    private String fullName;
    private int subjectId;
    private String status;

    public Teacher() {}

    public Teacher(int id, String fullName, int subjectId, String status) {
        this.id = id;
        this.fullName = fullName;
        this.subjectId = subjectId;
        this.status = status;
    }

    public Teacher(String fullName, int subjectId, String status) {
        this.fullName = fullName;
        this.subjectId = subjectId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Teacher " +
                "id: " + id +
                ", fullName: " + fullName +
                ", subjectId: " + subjectId +
                ", status: " + status +
                "\n";
    }
}
