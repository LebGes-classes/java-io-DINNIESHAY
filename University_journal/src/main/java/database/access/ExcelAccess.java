package database.access;

import java.util.ArrayList;

public interface ExcelAccess<T> {

    ArrayList<T> getAll();
    void add(T object);
    void update(T object);
    void delete(T object);
}
