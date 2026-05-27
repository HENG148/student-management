package repository;

import java.util.ArrayList;
import java.util.List;

import model.Student;

public class StudentRepository {
  private List<Student> students = new ArrayList<>();

  public List<Student> getAll() {
    return students;
  }

  public Student findbyId(String id) {
    for (Student s : students) {
      if (s.getStudentId().equalsIgnoreCase(id))
        return s;
    }
    return null;
  }

  public boolean existsById(String id) {
    return findbyId(id) != null;
  }

  public int count() {
    return students.size();
  }

  public boolean isEmpty() {
    return students.isEmpty();
  }

  public void add(Student student) {
    students.add(student);
  }

  public void remove(Student student) {
    students.remove(student);
  }

  public void setAll(List<Student> list){
    students = list;
  }
}
