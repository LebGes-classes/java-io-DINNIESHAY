package university.group;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

    private int id;
    private String name;

    public Group() {}

    public Group(String name) {
        this.name = name;
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Group " +
                "id: " + id +
                ", name: " + name +
                "\n";
    }
}
