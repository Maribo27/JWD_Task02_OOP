package by.tc.task02.service.validation;

import java.net.URL;

public class Validator {
    public static boolean fileFound(Class currentClass, String filename) {
        URL fileURL = currentClass.getClassLoader().getResource(filename);
        return fileURL != null;
    }
}
