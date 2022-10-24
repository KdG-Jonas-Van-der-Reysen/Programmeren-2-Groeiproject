package be.kdg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class BrommerFunctions {
    public static <T> List<T> filteredList(List<T> brommerList, Predicate<T> predicate) {
        return brommerList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static <T> Double average (List<T> brommerList, ToDoubleFunction<T> mapper) {
        return brommerList.stream()
                .collect(Collectors.averagingDouble(mapper));
    }

    public static <T> long countIf(List<T> brommerlist, Predicate<T> predicate) {
        return brommerlist.stream()
                .filter(predicate)
                .count();
    }

}
