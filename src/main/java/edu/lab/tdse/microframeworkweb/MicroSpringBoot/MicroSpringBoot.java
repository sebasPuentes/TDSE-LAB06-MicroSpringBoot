package edu.lab.tdse.microframeworkweb.MicroSpringBoot;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MicroSpringBoot {

    public static Map<String, Method> controllerMethods = new HashMap<>();

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException, URISyntaxException {
        // Escanea el paquete y revisa si hay clases con anotaciones @RestController
        String packageToScan = MicroSpringBoot.class.getPackage().getName();
        System.out.println("Scanning package: " + packageToScan);

        List<Class<?>> classes = getClasses(packageToScan);

        for (Class<?> c : classes) {
            if (c.isAnnotationPresent(RestController.class)) { //si tiene RestController
                for (Method m : c.getDeclaredMethods()) {
                    if (m.isAnnotationPresent(GetMapping.class)) {
                        GetMapping a = m.getAnnotation(GetMapping.class);
                        System.out.println("  Adding controllerMethod to path: " + a.value());
                        controllerMethods.put(a.value(), m);
                    }
                }
            }
        }

        String executionPath = args[0];
        Method m = controllerMethods.get(executionPath);
        System.out.println("Invoking: " + m.getName());
        System.out.println(m.invoke(null));
    }

    private static List<Class<?>> getClasses(String packageName) throws IOException, URISyntaxException {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File directory = new File(resource.toURI());
            if (directory.exists()) {
                for (File file : directory.listFiles()) {
                    if (file.getName().endsWith(".class")) {
                        String className = packageName + "." + file.getName().replace(".class", "");
                        try {
                            classes.add(Class.forName(className));
                        } catch (ClassNotFoundException e) {
                            System.err.println("Could not load class: " + className);
                        }
                    }
                }
            }
        }

        return classes;
    }
}
