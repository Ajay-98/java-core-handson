import java.util.Optional;

record Transaction(String id, double amount, String currency) {}
record User(String name, int age, boolean isVip) {}

public class SmartProcessor {


    public void processInput(Object input) {

        Optional.of(input).ifPresent( value -> {

            switch (value){
                case Transaction t -> {
                    if (t.amount() > 10000);
                    System.out.println("Flaggin large transaction: ["+ t.id()+"]");
                }
                case User u ->{
                    if(u.isVip()){
                        System.out.println("Welcome Back !!, [" + u.name()+"]");
                    } else if (u.age() < 18) {
                        System.out.println("Restricted access for " + u.name());
                    }
                }
                case String s -> { System.out.println("Length" + s.length()); }
                default -> {}
            }
                }

        );

        }
    }
