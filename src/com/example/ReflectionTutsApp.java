package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTutsApp {
    public static void main(String[] args) {
        A a = new A(42);
        sun(a);
        System.out.println(a.fun());
    }

    public static void sun(Object o) {
        Class<?> clazz = o.getClass();

        // Running private constructor
        for (Constructor constructor : clazz.getConstructors()){
            if (constructor.getParameterCount() == 0){
                constructor.setAccessible(true);
                try {
                    constructor.newInstance(null);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    System.err.println(e);
                }
                constructor.setAccessible(false);
            }
        }

        // Changing privately defined field
        for (Field field : clazz.getDeclaredFields()){
            switch (field.getName()){
                case "x":
                    try {
                        field.setAccessible(true);
                        field.set(o, 108);
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        System.err.println(e);
                    }
                    break;
            }
            //System.out.println(field.getName());
        }

        // Invoking privately defined method
        for (Method method : clazz.getDeclaredMethods()){
            switch (method.getName()){
                case "gun":
                    try {
                        method.setAccessible(true);
                        method.invoke(o, null);
                        method.setAccessible(false);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.err.println(e);
                    }
                    break;
            }
        }
    }
}
