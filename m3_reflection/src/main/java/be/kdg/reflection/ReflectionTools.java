package be.kdg.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTools {
    public static void classAnalysis(Class<?> aClass) {
        // De bedoeling is dat je in deze methode introspection doet en de klasse analyseert die
        // als parameter binnenkomt (aClass).

        String className = aClass.getName();
        String superClassName = aClass.getSuperclass().getName();
        String packageName = aClass.getPackageName();

        // Private fields
        Field[] privateProperties = aClass.getDeclaredFields();

        // All methods
        Method[] methods = aClass.getMethods();

        // Print analysis
        System.out.println("Analyse van de klasse: " + aClass.getSimpleName());
        System.out.println("===============================================");
        System.out.printf("%-24s: %s\n", "Fully qualified name", className);
        System.out.printf("%-24s: %s\n", "Naam van de superklasse",superClassName);
        System.out.printf("%-24s: %s\n", "Naam van de package", packageName);

        // Print interfaces
        System.out.print("Interfaces: ");
        for(Class i : aClass.getInterfaces()) {
            System.out.print(i.getSimpleName() + " ");
        }
        System.out.println();

        // Print constructors
        System.out.print("Constructors: ");
        for(Constructor c : aClass.getConstructors()) {
            System.out.print(c.toGenericString() + " ");
        }
        System.out.println();

        // Print attributes with value types
        System.out.println("Attributen:");
        for (Field f: privateProperties) {
            System.out.printf("%s (%s) ", f.getName(), f.getType());
        }
        System.out.println();

        // Print getters
        System.out.printf("%-16s: ","Getters");
        for (Method m: methods) {
            if (m.getName().startsWith("get")) {
                System.out.printf(m.getName() + " ");
            }
        }
        System.out.println();

        // Print setters
        System.out.printf("%-16s: ","Setters");
        for (Method m: methods) {
            if (m.getName().startsWith("set")) {
                System.out.printf(m.getName() + " ");
            }
        }

        // Print the rest of the methods
        System.out.println();
        System.out.printf("%-16s: ","Andere methoden");
        for (Method m: methods) {
            if (!m.getName().startsWith("get") && !m.getName().startsWith("set")) {
                System.out.printf(m.getName() + " ");
            }
        }
        System.out.println("\n\n");
    }
    public static void classAnalysis(Class<?> ...classes) {
        for (Class aClass : classes) {
            classAnalysis(aClass);
        }
    }

    public static Object runAnnotated (Class<?> aClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object object = aClass.getDeclaredConstructor().newInstance();
        // Loop over all methods in the class and check if they are annotated with @CanRun
        for (Method m : aClass.getMethods()) {
            System.out.println("m.getAnnotations() = " + m.getAnnotations());
            if (m.isAnnotationPresent(CanRun.class)) {
                CanRun canRun = m.getAnnotation(CanRun.class);
                m.invoke(canRun.value());
            }
        }

        return object;
    }
}
