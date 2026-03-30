import java.util.List;
import java.util.function.Predicate;

public class Predicate3 {

    public static void main(String[] args) {
        Predicate<String> startsWithA = startsWithLetter("A");
        Predicate<String> startsWithC = startsWithLetter("C");

        List<String> names = List.of("Alice", "Bob", "Charlie", "Andrew", "Clara");
        System.out.println( names
                .stream()
                .filter(startsWithA)
                .toList());
        System.out.println( names
                .stream()
                .filter(startsWithC)
                .toList());
    }


    public static Predicate<String> startsWithLetter(String letter) {

        return obj->obj.startsWith(letter);
    }


}
