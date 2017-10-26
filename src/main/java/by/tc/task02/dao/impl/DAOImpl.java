package by.tc.task02.dao.impl;

import by.tc.task02.dao.DAO;
import by.tc.task02.dao.parser.FileParser;
import by.tc.task02.dao.parser.StringParser;
import by.tc.task02.entity.Entity;

import java.net.URL;
import java.util.List;

public class DAOImpl implements DAO {
    @Override
    public Entity getRootEntity(URL fileURL) {
        FileParser fileParser;
        fileParser = new FileParser(fileURL);

        String fileString;
        fileString = fileParser.fileToString();
        if (fileString == null){
            return null;
        }

        StringParser stringParser;
        stringParser = new StringParser();

        List<Entity> entities;
        entities = stringParser.getChild(fileString);

        boolean wrongData;
        wrongData = entities == null || entities.size() != 1;
        if (wrongData){
            return null;
        } else {
            return entities.get(0);
        }
    }
}
