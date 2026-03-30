import java.util.List;
import java.util.function.Predicate;

public class predicate1 {

    public static void main(String[] args) {

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThan10 = n -> n > 10;

        Predicate<Integer> isEvenAndLessThan10 = isEven.and(isGreaterThan10);
        Predicate<Integer> isEvenOrLessThan10 = isEven.or(isGreaterThan10);
        Predicate<Integer> noteven = isEven.negate();

        List<Integer> numbers = List.of(2, 5, 12, 7, 20, 3, 14);
        // AND Expected : [12, 20, 14]
        // OR  Expected : [2, 12, 20, 14]
        // NOT Expected : [5, 7, 3]

        numbers
                .stream()
                .filter(isEvenAndLessThan10)
                .toList().forEach(System.out::println);
    }
}
