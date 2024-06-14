import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;

        public Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", "IT", 50000),
                new Employee(2, "Jane", "HR", 60000),
                new Employee(3, "Mike", "Finance", 55000),
                new Employee(4, "Emily", "IT", 48000),
                new Employee(5, "David", "Finance", 60000)
        );

        Map<String, Double> secondHighestSalariesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Employee::getSalary, Collectors.toSet()),
                                salaries -> {
                                    List<Double> sortedSalaries = new ArrayList<>(salaries);
                                    Collections.sort(sortedSalaries, Comparator.reverseOrder());
                                    return sortedSalaries.size() > 1 ? sortedSalaries.get(1) : null;
                                }
                        )));

        System.out.println("Second highest salaries by department: " + secondHighestSalariesByDept);


    }
}
