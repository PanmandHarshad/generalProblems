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
                new Employee(1, "Amit", "IT", 90000),
                new Employee(2, "Neha", "HR", 60000),
                new Employee(3, "Rahul", "IT", 120000),
                new Employee(4, "Priya", "Finance", 80000),
                new Employee(5, "Karan", "HR", 75000),
                new Employee(6, "Sneha", "Finance", 95000),
                new Employee(7, "Vijay", "IT", 110000)
        );

        // Q1:
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.toList()));


        // Q2:
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

        // Q3:
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        // Q4:
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())));

        // Q5:
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.counting()));

        // map employee id and employee
        employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));

        // Partition employees by salary
        employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() >= 100_000));

        // find the highest-paid employee in each department
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(
                                        Comparator.comparingDouble(Employee::getSalary)
                                ),
                                Optional::get)
                ));


        // Employee count
        // Minimum salary
        // Maximum salary
        // Average salary
        // Total salary
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.summarizingDouble(Employee::getSalary)));

        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(
                                employee -> employee.getSalary() >= 100_000 ? "HIGH" : "STANDARD"
                        )));


        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.incrementAndGet();

//        Map<String, Double> secondHighestSalariesByDept = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(
//                                Collectors.mapping(Employee::getSalary, Collectors.toSet()),
//                                salaries -> {
//                                    List<Double> sortedSalaries = new ArrayList<>(salaries);
//                                    Collections.sort(sortedSalaries, Comparator.reverseOrder());
//                                    return sortedSalaries.size() > 1 ? sortedSalaries.get(1) : null;
//                                }
//                        )));
//
//        System.out.println("Second highest salaries by department: " + secondHighestSalariesByDept);

//        List<Integer> numbers = List.of(1, 2, 3, 4);
//        numbers.stream()
//                .filter(n -> {
//                    System.out.println("filter: " + n);
//                    return n % 2 == 0;
//                })
//                .map(n -> {
//                    System.out.println("map: " + n);
//                    return n * 10;
//                })
//                .forEach(n -> System.out.println("result: " + n));

        System.out.println("Second highest salaries by department: " + secondHighestSalariesByDept);


    }
}
