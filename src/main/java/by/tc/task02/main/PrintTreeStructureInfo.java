package by.tc.task02.main;

import by.tc.task02.entity.Entity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

class PrintTreeStructureInfo {
    static void print(Entity entity) {
        if (entity == null){
            System.out.println("tree is not found");
            return;
        }

        printAttributes(entity);
        printText(entity);
    }

    private static void printText(Entity entity){

        List<Entity> childList = entity.getChildren();
        int entityLevel = entity.getLevel();

        if (entity.getAttributes() != null) {
            entityLevel++;
        }

        if (!entity.getText().isEmpty()) {
            addTabulation(entityLevel);
            System.out.print(entity.getText());
        }

        if (childList.size() == 0){
            return;
        }

        for (Entity child : childList){
            print(child);
        }
    }

    private static void printAttributes(Entity entity){
        Map<String, String> attributes = entity.getAttributes();
        if (attributes == null){
            return;
        }
        addTabulation(entity.getLevel());

        Iterator key = attributes.keySet().iterator();
        String attribute = attributes.get(key.next().toString());
        attribute = attribute.substring(1, attribute.length() - 1);
        System.out.print(attribute);



        while (key.hasNext()){
            System.out.print(", ");
            attribute = attributes.get(key.next().toString());
            attribute = attribute.substring(1, attribute.length() - 1);
            System.out.print(attribute);
        }

        System.out.print(": ");
    }

    private static void addTabulation(int level) {
        System.out.println();
        for (int iterator = 0; iterator <= level; iterator++) {
            System.out.print('\t');
        }
    }
}
