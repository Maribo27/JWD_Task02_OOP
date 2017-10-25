package by.tc.task02.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity {
    public String element;
    public Map<String, String> attributes = new HashMap<>();
    public List<Entity> children = new ArrayList<>();
    public String text = "";

    public Entity(){}

    @Override
    public String toString() {
        String output;
        output = "\nEntity: " + element + "\n\t";
        if (attributes != null) {
            output += "attributes: " + attributes + "\n\t";
        }
        if (children != null) {
            output += "children: " + children + "\n\t";
        }
        if (!text.isEmpty()){
            output += text;
        }
        return output;
    }
}
