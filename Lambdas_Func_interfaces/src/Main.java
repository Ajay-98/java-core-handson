//Build a validate class to store the rules, along with messages and validate() the user object.


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

class Validate<T>{

    HashMap<Predicate<T>, String> rules =  new HashMap<>();
    public Validate<T> add(Predicate<T> rule, String msg)
    {
        rules.put(rule, msg);
        return this;
    }

    public void  validate(T obj)
    {
        List op = rules.entrySet()
                .stream()
                .filter((o) -> o.getKey().test(obj))
                .map(Map.Entry::getValue) //only print the error message
                .toList();
        System.out.println(op);
    }
}

class User{
    private String name;
    private int age;
    private String email;
    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {return this.name;}
    public int getAge() { return this.age; }
    public String getEmail() { return this.email; }

}

class Main{

    public static void main(String[] args) {

        Validate<User> validation_obj = new Validate<User>()
                .add((p)->!(p.getAge() >= 18 && p.getAge()<=60), "Age Should be getter than 18")
                .add((p)->!p.getEmail().contains("@"), "Provide valid email")
                .add((p)->p.getName().equalsIgnoreCase("null") || p.getName().isEmpty(),"Name is null or empty");

        validation_obj.validate(new User("null", 17,"badmail"));

        validation_obj.validate(new User("Ajay", 18,"ajaymail.com"));
    }

}