import be.kdg.model.BrommerFactory;

import java.util.stream.Stream;

public class Demo_8 {
    public static void main(String[] args) {
        /*Stream.generate(BrommerFactory::newRandomBrommer)
                .limit(30)
                .forEach(System.out::println);*/

        PerformanceTester.compareArrayListAndLinkedList(20000);
    }
}
