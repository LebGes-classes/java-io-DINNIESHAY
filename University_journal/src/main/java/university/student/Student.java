package university.student;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String fullName;
    private int groupId;
    private String status;

    public Student() {}

    public Student(int id, String fullName, int groupId, String status) {
        this.id = id;
        this.fullName = fullName;
        this.groupId = groupId;
        this.status = status;
    }

    public Student(String fullName, int groupId, String status) {
        this.fullName = fullName;
        this.groupId = groupId;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student " +
                "id: " + id +
                ", full name: " + fullName +
                ", group id: " + groupId +
                ", status: " + status +
                "\n";
    }
}
