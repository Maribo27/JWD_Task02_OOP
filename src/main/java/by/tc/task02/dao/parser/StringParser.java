package by.tc.task02.dao.parser;

import by.tc.task02.entity.Entity;

import java.util.*;

public class StringParser {
    private static final char TAG_START = '<';
    private static final char TAG_END = '>';
    private static final char SPACE = ' ';
    private static final char EQUAL = '=';
    private static final char CLOSE_TAG_END = '/';
    private static final String CLOSE_TAG_START = "</";
    private static final String END_OF_TAG = "\"[ ]+";
    private static final String TAB = "\t";
    private static final String TAB_REPLACEMENT = "\"\t";

    public StringParser() { }

    public List<Entity> getChild(String partOfXMLDocument){

        if (partOfXMLDocument.isEmpty()){
            return null;
        }

        if (partOfXMLDocument.charAt(0) != TAG_START){
            return null;
        }
        List<Entity> children = new ArrayList<>();
        parse(partOfXMLDocument, children);
        return children;
    }

    private void parse(String partOfXMLDocument, List<Entity> children) {
        StringBuilder stringBuffer = new StringBuilder();
        Entity entity = new Entity();
        int substringEnd;
        int substringStart = 0;
        while (substringStart < partOfXMLDocument.length()){
            char currentChar = partOfXMLDocument.charAt(substringStart);
            if (currentChar == TAG_START){
                checkStringBuffer(stringBuffer, entity);
                entity = new Entity();
                stringBuffer = new StringBuilder();
                substringStart++;
                continue;
            }

            char nextChar = partOfXMLDocument.charAt(substringStart + 1);
            boolean theEndOfFile = substringStart + 1 >= partOfXMLDocument.length();
            boolean tagEnd = currentChar == CLOSE_TAG_END && nextChar == TAG_END;
            if (tagEnd && !theEndOfFile) {
                substringStart = parseToAttribute(children, stringBuffer, entity, substringStart);
                continue;
            }

            if (currentChar == TAG_END) {
                createEntityAttributes(stringBuffer, entity);
                String closeTag = CLOSE_TAG_START + entity.getElement() + TAG_END;
                substringEnd = partOfXMLDocument.indexOf(closeTag,substringStart);
                substringStart++;

                createChild(partOfXMLDocument, entity, substringStart, substringEnd);
                stringBuffer = new StringBuilder();
                substringStart = substringEnd + closeTag.length();
                children.add(entity);
                continue;
            }
            stringBuffer.append(partOfXMLDocument.charAt(substringStart++));
        }
    }

    private int parseToAttribute(List<Entity> children, StringBuilder stringBuffer, Entity entity, int substringStart) {
        createEntityAttributes(stringBuffer, entity);
        substringStart += 2;
        children.add(entity);
        return substringStart;
    }

    private void createChild(String partOfXMLDocument, Entity entity, int substringStart, int substringEnd) {
        String substring = partOfXMLDocument.substring(substringStart, substringEnd).trim();
        List<Entity> tempEntity = getChild(substring);
        if (tempEntity == null) {
            entity.setText(substring);
        } else {
            entity.setChildren(tempEntity);
        }
    }

    private void createEntityAttributes(StringBuilder stringBuilder, Entity entity) {
        List<String> stringList = getAttributeList(stringBuilder.toString());
        entity.setElement(stringList.get(0));
        entity.setAttributes(getMap(stringList));
    }

    private void checkStringBuffer(StringBuilder stringBuilder, Entity entity) {
        boolean emptyString = stringBuilder.toString().isEmpty();
        if (!emptyString){
            entity.setText(stringBuilder.toString());
        }
    }

    private List<String> getAttributeList(String string){
        List<String> attributes = new ArrayList<>();

        int engOfTagName = string.indexOf(SPACE);
        if (engOfTagName == -1){
            attributes.add(string);
            return attributes;
        }

        String tagName = string.substring(0, engOfTagName);
        attributes.add(tagName);

        string = string.substring(engOfTagName + 1);
        string = string.replaceAll(END_OF_TAG, TAB_REPLACEMENT);

        String attributeArray[] = string.split(TAB);
        attributes.addAll(Arrays.asList(attributeArray));

        return attributes;
    }

    private Map<String, String> getMap(List<String> stringList){
        if (stringList.size() == 1){
            return null;
        }

        Map<String, String> map = new HashMap<>();

        stringList.remove(0);
        for (String attribute: stringList) {
            int index = attribute.indexOf(EQUAL);
            String attributeName = attribute.substring(0, index).trim();
            String attributeValue = attribute.substring(index + 1).trim();
            map.put(attributeName, attributeValue);
        }
        return map;
    }
}
