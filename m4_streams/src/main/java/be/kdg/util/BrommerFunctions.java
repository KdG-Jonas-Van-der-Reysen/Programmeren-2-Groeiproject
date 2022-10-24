package be.kdg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class BrommerFunctions {
    public static <T> List<T> filteredList(List<T> brommerList, Predicate<T> predicate) {
        List<T> passedFilterList = new ArrayList<>();

        for (T item: brommerList) {
            if (predicate.test(item)) {
                passedFilterList.add(item);
            }
        }

        return passedFilterList;
    }

    public static <T> Double average (List<T> brommerList, ToDoubleFunction<T> mapper) {
        double sum = 0;
        for (T item: brommerList) {
            sum += mapper.applyAsDouble(item);
        }
        return sum / brommerList.size();
    }

    public static <T> long countIf(List<T> brommerlist, Predicate<T> predicate) {
        long amountOfMatchingItems = 0;
        for (T item: brommerlist) {
            if (predicate.test(item)) {
                amountOfMatchingItems++;
            }
        }

        return amountOfMatchingItems;
    }

}
