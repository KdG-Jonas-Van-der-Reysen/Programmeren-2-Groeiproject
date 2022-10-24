import be.kdg.model.*;
import be.kdg.reflection.ReflectionTools;

import java.lang.reflect.InvocationTargetException;

public class Demo_3 {
    public static void main(String[] args) {
        ReflectionTools.classAnalysis(Voertuig.class, Brommer.class, Brommers.class);

        try {
            Object obj = ReflectionTools.runAnnotated(Voertuig.class);
            System.out.println("Aangemaakt object door runAnnotated: ");
            System.out.println(obj);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
