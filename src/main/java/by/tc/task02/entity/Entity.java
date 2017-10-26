package by.tc.task02.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity implements Serializable {
    private String element;
    private Map<String, String> attributes;
    private List<Entity> children;
    private String text;
    private int level;

    public Entity(){
        element = "";
        attributes = new HashMap<>();
        children = new ArrayList<>();
        text = "";
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (level != entity.level) return false;
        if (element != null ? !element.equals(entity.element) : entity.element != null) return false;
        if (attributes != null ? !attributes.equals(entity.attributes) : entity.attributes != null) return false;
        if (children != null ? !children.equals(entity.children) : entity.children != null) return false;
        return text != null ? text.equals(entity.text) : entity.text == null;
    }

    @Override
    public int hashCode() {
        int result = element != null ? element.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "Entity:" + "\n\t" +
                "element='" + element + "\n\t" +
                ", attributes=" + attributes + "\n\t" +
                ", children=" + children + "\n\t" +
                ", text='" + text + "\n\t" +
                ", level=" + level + '\n';
    }
}
