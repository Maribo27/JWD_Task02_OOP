package by.tc.task02.main;

import by.tc.task02.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class PrintTreeStructureInfo {
    static void print(Entity entity, int level) {
        if (entity == null){
            System.out.println("tree is not found");
            return;
        }

        printAttributes(entity, level);

        if (entity.getChildren().size() == 0) {
            return;
        }

        level++;
        printChild(entity, level);
    }

    private static void printChild(Entity entity, int level) {
        List<String> text = new ArrayList<>();
        for (Entity child : entity.getChildren()) {
            if (!child.getText().isEmpty()) {
                text.add(child.getText());
            }
        }

        printText(text);

        for (Entity child : entity.getChildren()) {
            print(child, level);
        }
    }

    private static void printText(List<String> text){
        if (text.size() == 0){
            return;
        }

        System.out.print(text.get(0));
        if (text.size() == 1){
            return;
        }
        text.remove(0);

        for (String str: text) {
            System.out.print(", " + str);
        }
        System.out.println();
    }

    private static void printAttributes(Entity entity, int level){
        Map<String, String> attributes = entity.getAttributes();
        if (attributes == null){
            return;
        }

        addTabulation(level);

        Iterator key = attributes.keySet().iterator();
        String attribute = attributes.get(key.next().toString());
        attribute = attribute.substring(1, attribute.length() - 1);
        System.out.print(attribute);

        if (attributes.size() == 1){
            System.out.print(". ");
            return;
        }

        while (key.hasNext()){
            System.out.print(", ");
            attribute = attributes.get(key.next().toString());
            attribute = attribute.substring(1, attribute.length() - 1);
            System.out.print(attribute);
        }
        System.out.print(". ");
    }

    private static void addTabulation(int level) {
        for (int iterator = 0; iterator <= level; iterator++) {
            System.out.print('\t');
        }
    }
}
