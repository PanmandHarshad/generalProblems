package LinkedList;

import java.util.HashSet;
import java.util.Set;

public class StudentSet {
    public static void main(String[] args) {
//        Student student1 = new Student("Harshad", 22);
//        System.out.println("Student 1: " + student1);
//        Student student2 = new Student("Harshad", 23);
//        System.out.println("Student 2: " + student2);
//        Student student3 = new Student("Harshad", 22);
//        System.out.println("Student 3: " + student3);
//        Student student4 = new Student("Harshad", 23);
//        System.out.println("Student 4: " + student4);
//        Student student5 = new Student("Harshad", 22);
//        System.out.println("Student 5: " + student5);
//
//        Set<Student> studentSet = new HashSet<>();
//        studentSet.add(student1);
//        studentSet.add(student2);
//        studentSet.add(student3);
//        studentSet.add(student4);
//        studentSet.add(student5);
//
//        for (Student student : studentSet) {
//            System.out.println("Student : " + student);
//        }

        Set<Student> studentSet = new HashSet<>();

        // Add Student objects without overriding hashCode() and equals()
        studentSet.add(new Student("Alice", 22));
        studentSet.add(new Student( "Bob",23));
        studentSet.add(new Student( "Alice",22)); // Potential duplicate

        // Print the contents of the Set
        System.out.println("Students in Set:");
        for (Student student : studentSet) {
            System.out.println(student);
        }
    }
}
