package model;

import java.io.Serializable;

public class Student implements Serializable {
  private static final long serialVersionUID = 1L;
  private String studentId;
  private String fullName;
  private int age;
  private String gender;
  private String course;
  private String email;

  public Student(String studentId, String fullName, int age, String gender, String course, String email) {
    this.studentId = studentId;
    this.fullName = fullName;
    this.age = age;
    this.gender = gender;
    this.course = course;
    this.email = email;
  }

  public String getStudentId() {
    return studentId;
  }

  public String getFullName() {
    return fullName;
  }

  public int getAge() {
    return age;
  }

  public String getGender() {
    return gender;
  }

  public String getCourse() {
    return course;
  }

  public String getEmail() {
    return email;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return String.format(
        "| %-10s | %-22s | %-3d | %-8s | %-20s | %-28s",
        studentId, fullName, age, gender, course, email);
  }
  
  public String toDetailString() {
    String line = " " + "-".repeat(46);
    return line + "\n" +
        "Student ID : " + studentId + "\n" +
        "Full Name : " + fullName + "\n" +
        "Age : " + age + "\n" +
        "Gender : " + gender + "\n" +
        "Course : " + course + "\n" +
        "Email : " + email + "\n" +
        line;
  }
  
  public String toCsv() {
    return studentId + "|" + fullName + "|" + age + "|" + gender + "|" + course + "|" + email;
  }

  public static Student fromCsv(String csv) {
    String[] parts = csv.split("\\|", -1);
    if (parts.length != 6) {
      throw new IllegalArgumentException("Malformed line: " + csv);
    }
    return new Student(
      parts[0].trim(),
      parts[1].trim(),
      Integer.parseInt(parts[2].trim()),
      parts[3].trim(),
      parts[4].trim(),
      parts[5].trim()
    );
  }
}
