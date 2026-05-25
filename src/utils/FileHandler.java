package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  // public static void saveToFile(List<Student> students) throw IOException{
  //   saveToFile(students, DEFAULT_FILE_PATH);
  // }

  // public static List<Student> load

  // public static List<Student> loadFromFile
  // public static List<Student> loadFromFile() throws IOException {
  //   return loadFromFile(DEFAULT_FILE_PATH);
  // }

  // public static boolean dataFileExists() {
  //   return new File(DEFAULT_FILE_PATH).exists();
  // }
}
