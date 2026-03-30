

// Test: filter employees who are
// 1. In Engineering
// 2. Salary > 90000
// 3. Name length > 4
// Expected: [Alice, Charlie]


import java.util.List;
import java.util.function.Predicate;

class Employee{
    private String name;
    private int salary;
    private String department;
    private int nm_threshold;
    public Employee(String name, int salary,String department,  int nm_threshold){
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.nm_threshold = nm_threshold;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSalary() {return this.salary;};
    public void setSalary(int salary) {}
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {};
    public int getNm_threshold() {
        return this.nm_threshold;
    }
    public void setNm_threshold(int nm_threshold) {
        this.nm_threshold = nm_threshold;
    }

    @Override
    public String toString() {
       return name + " " + salary + " " + department + " " + nm_threshold;
    }
}

public class Predicate2 {
    public static <T> List<T> filterWithAll(List<T> list, List<Predicate<T>> predicates) {

        Predicate<T> combinedPredicate = predicates
                .stream()
                        .reduce(
                                x->true,Predicate::and
                        )
                ;

       return list
                .stream()
                .filter(
                        combinedPredicate
                )
                .toList();

    }


    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("Alice", 95000, "Engineering", 28),
                new Employee("Bob", 87000, "Engineering", 40),
                new Employee("Charlie", 102000, "Engineering", 32),
                new Employee("Diana", 72000, "HR", 29),
                new Employee("Eve", 93000, "Engineering", 27),  // name length = 3, filtered out
                new Employee("Hank", 91000, "Finance", 31),  // not Engineering
                new Employee("Alexander", 98000, "Engineering", 35)   // salary > 90000, Engineering, length > 4
        );

        List<Predicate<Employee>> predicates = List.of(
                e -> e.getDepartment().equals("Engineering"),
                e -> e.getSalary() > 90000,
                e -> e.getName().length() > 4
        );

        filterWithAll(employees, predicates).stream().forEach(System.out::println);
    }
}
