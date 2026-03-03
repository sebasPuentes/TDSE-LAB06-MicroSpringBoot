package edu.lab.tdse.microframeworkweb.MicroSpringBoot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MicroSpringBoot {

    public static Map<String,Method> controllerMethods = new HashMap<>();

    //Loading controllers
    public static void main(String [] args) throws ClassNotFoundException, IllegalAccessException,InvocationTargetException{
        Class<?> c = Class.forName(args[0]);
        if(c.isAnnotationPresent(RestController.class)){
            for(Method m: c.getDeclaredMethods()){
                if(m.isAnnotationPresent(GetMapping.class)){
                    GetMapping a = m.getAnnotation(GetMapping.class);
                    System.out.println("Adding controllerMethod to path:" + a.value());
                    controllerMethods.put(a.value(),m);

                }
            }
        }

        //Executing controller methods
        String executionPath = args[1];
        Method m = controllerMethods.get(executionPath);
        System.out.println("Invoking: " + m.getName());
        System.out.println(m.invoke(null));

    }
    
}
