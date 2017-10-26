package by.tc.task02.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity implements Serializable {
    private String element;
    private Map<String, String> attributes = new HashMap<>();
    private List<Entity> children = new ArrayList<>();
    private String text = "";

    public Entity(){}

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<Entity> getChildren() {
        return children;
    }

    public void setChildren(List<Entity> children) {
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
