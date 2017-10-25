package by.tc.task02.dao;

import by.tc.task02.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    private static final int ONE_ELEMENT_IN_LIST = 1;
    private static final String NOT_SYMBOL_REGEX = "[\\n\\t\\r]";
    private static final String START_OF_XML_FILE = "<\\?.*\\?>";
    private static final char TAG_START = '<';
    private static final char TAG_END = '>';
    private static final char SPACE = ' ';
    private static final char EQUAL = '=';
    private String path;

    public Parser() {
        path = this.getClass().getClassLoader().getResource("test2.xml").getPath();
    }

    public void parse(){
        BufferedReader reader;
        try {
            path = path.replaceAll("%5b", "[");
            path = path.replaceAll("%5d", "]");
            reader = new BufferedReader(new FileReader(path));

            StringBuilder stringBuilder;
            stringBuilder = new StringBuilder();

            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            String string;
            string = stringBuilder.toString();
            string = getStringAfterConversion(string);

            Entity entity;
            List<Entity> entities = getChild(string);
            boolean wrongData;
            wrongData = entities == null || entities.size() != 1;

            if (wrongData){
                System.out.println("unknown error");
            } else {
                entity = entities.get(0);
                System.out.println(entity.toString());
            }

            reader.close();
        } catch (IOException e) {
            System.exit(-1);
        }

    }

    private String getStringAfterConversion(String string) {
        Pattern xmlStartPattern;
        xmlStartPattern = Pattern.compile(START_OF_XML_FILE);

        Pattern notSymbolRegex;
        notSymbolRegex = Pattern.compile(NOT_SYMBOL_REGEX);

        string = string.replaceAll(xmlStartPattern.toString(),"");
        string = string.replaceAll(notSymbolRegex.toString(),"");

        return string;
    }

    private List<Entity> getChild(String partOfXMLDocument){
        List<Entity> children;
        children = new ArrayList<>();

        StringBuilder stringBuffer;
        stringBuffer = new StringBuilder();

        Entity entity;
        entity = new Entity();

        List<String> attributes;

        int substringStart;
        substringStart = 0;

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

                attributes = getListOfAttributes(stringBuffer, entity);
                String close = "</" + attributes.get(0) + ">";

                substringEnd = partOfXMLDocument.indexOf(close,substringStart);
                substringStart++;

                List<Entity> tempEntity;
                tempEntity = getChild(partOfXMLDocument.substring(substringStart, substringEnd));
                setEntityChild(partOfXMLDocument, entity, substringStart, substringEnd, tempEntity);
                stringBuffer = new StringBuilder();
                substringStart = substringEnd + close.length();
                children.add(entity);
                continue;
            }
            stringBuffer.append(partOfXMLDocument.charAt(substringStart++));
        }
        return children;
    }

    private void setEntityChild(String string, Entity entity, int subStart, int subEnd, List<Entity> tempEntity) {
        if (tempEntity == null) {
            entity.text = string.substring(subStart, subEnd);
        } else {
            entity.children.addAll(tempEntity);
        }
    }

    private List<String> getListOfAttributes(StringBuilder stringBuilder, Entity entity) {
        List<String> stringList;
        stringList = getStrings(stringBuilder.toString());
        entity.element = stringList.get(0);
        entity.attributes = getMap(stringList);
        return stringList;
    }

    private void checkStringBuffer(StringBuilder stringBuilder, Entity entity) {
        if (!stringBuilder.toString().isEmpty()){
            entity.text = stringBuilder.toString();
        }
    }

    private List<String> getStrings(String string){
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();

        List<String> attributes;
        attributes = new ArrayList<>();

        for (int charNumber = 0; charNumber < string.length(); charNumber++){
            if (string.charAt(charNumber) == SPACE){
                attributes.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(string.charAt(charNumber));
            }
        }
        attributes.add(stringBuilder.toString());
        return attributes;
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