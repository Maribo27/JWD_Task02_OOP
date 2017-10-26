package by.tc.task02.dao.parser;

import by.tc.task02.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringParser {
    private static final int ONE_ELEMENT_IN_LIST = 1;
    private static final char TAG_START = '<';
    private static final char TAG_END = '>';
    private static final char SPACE = ' ';
    private static final char EQUAL = '=';
    private static final String CLOSE_TAG_START = "</";

    public StringParser() { }

    public List<Entity> getChild(String partOfXMLDocument){

        List<Entity> children = new ArrayList<>();
        StringBuilder stringBuffer = new StringBuilder();
        Entity entity = new Entity();
        int substringStart = 0;
        int substringEnd;

        if (partOfXMLDocument.charAt(0) != TAG_START){
            return null;
        }

        while (substringStart < partOfXMLDocument.length()){
            if (partOfXMLDocument.charAt(substringStart) == TAG_START){
                checkStringBuffer(stringBuffer, entity);
                entity = new Entity();
                stringBuffer = new StringBuilder();
                substringStart++;
                continue;
            }

            if (partOfXMLDocument.charAt(substringStart) == TAG_END) {

                createEntityAttributes(stringBuffer, entity);
                String closeTag = CLOSE_TAG_START + entity.getElement() + TAG_END;
                substringEnd = partOfXMLDocument.indexOf(closeTag,substringStart);
                substringStart++;

                stringBuffer = createChild(partOfXMLDocument, entity, substringStart, substringEnd);
                substringStart = substringEnd + closeTag.length();
                children.add(entity);
                continue;
            }
            stringBuffer.append(partOfXMLDocument.charAt(substringStart++));
        }
        return children;
    }

    private StringBuilder createChild(String partOfXMLDocument, Entity entity, int substringStart, int substringEnd) {
        StringBuilder stringBuffer;
        String substring = partOfXMLDocument.substring(substringStart, substringEnd);
        List<Entity> tempEntity = getChild(substring);
        if (tempEntity == null) {
            entity.setText(substring);
        } else {
            entity.setChildren(tempEntity);
        }
        stringBuffer = new StringBuilder();
        return stringBuffer;
    }

    private void createEntityAttributes(StringBuilder stringBuilder, Entity entity) {
        List<String> stringList;
        stringList = getStrings(stringBuilder.toString());
        entity.setElement(stringList.get(0));
        entity.setAttributes(getMap(stringList));
    }

    private void checkStringBuffer(StringBuilder stringBuilder, Entity entity) {
        if (!stringBuilder.toString().isEmpty()){
            entity.setText(stringBuilder.toString());
        }
    }

    private List<String> getStrings(String string){
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();

        List<String> attributes;
        attributes = new ArrayList<>();

        for (int charNumber = 0; charNumber < string.length(); charNumber++){
            stringBuilder = getCharacterProcessingResult(string, stringBuilder, attributes, charNumber);
        }
        attributes.add(stringBuilder.toString());
        return attributes;
    }

    private StringBuilder getCharacterProcessingResult(String string, StringBuilder stringBuilder, List<String> attributes, int charNumber) {
        if (string.charAt(charNumber) == SPACE){
            attributes.add(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        } else {
            stringBuilder.append(string.charAt(charNumber));
        }
        return stringBuilder;
    }

    private Map<String, String> getMap(List<String> stringList){
        if (stringList.size() == ONE_ELEMENT_IN_LIST){
            return null;
        }

        Map<String, String> map;
        map = new HashMap<>();

        stringList.remove(0);
        for (String attribute: stringList) {
            int index = attribute.indexOf(EQUAL);
            map.put(attribute.substring(0, index), attribute.substring(index + 1));
        }
        return map;
    }
}
