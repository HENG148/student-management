package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class FileHandler {
  public static final String DEFAULT_FILE_PATH = "data/students.txt";

  public static void saveToFile(List<Student> students, String filePath)
      throws IOException {
    Path path = Paths.get(filePath);
    Files.createDirectories(path.getParent() != null ? path.getParent() : Paths.get("."));
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write("# Student Management System - Data File");
      writer.newLine();
      writer.write("# Format: studentId|fullName|age|gender|course|email");
      writer.newLine();
      for (Student s : students) {
        writer.write(s.toCsv());
        writer.newLine();
      }
    }
  }
  
  public static void saveToFile(List<Student> students) throws IOException {
    saveToFile(students, DEFAULT_FILE_PATH);
  }

  public static List<Student> loadFromFile(String filePath) throws IOException {
    List<Student> students = new ArrayList<>();
    File file = new File(filePath);
    if (!file.exists()) {
      return students;
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      int lineNumber = 0;
      while ((line = reader.readLine()) != null) {
        lineNumber++;
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#"))
          continue;
        try {
          students.add(Student.fromCsv(line));
        } catch (IllegalArgumentException e) {
          System.err.println("[WARN] Skipping bad line" + lineNumber + ":" + e.getMessage());
        }
      }
    }
    return students;
  }

  public static List<Student> loadFromFile() throws IOException {
    return loadFromFile(DEFAULT_FILE_PATH);
  }

  public static boolean dataFileExists() {
    return new File(DEFAULT_FILE_PATH).exists();
  }
}
