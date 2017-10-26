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
        FileParser fileParser = new FileParser(fileURL);

        String fileString = fileParser.fileToString();
        if (fileString == null){
            return null;
        }

        StringParser stringParser = new StringParser();

        List<Entity> entities = stringParser.getChild(fileString);

        boolean wrongData;
        wrongData = entities == null || entities.size() != 1;
        if (wrongData){
            return null;
        }

        Entity entity = entities.get(0);

        createTabulationLevel(entity, 0);
        return entity;
    }

    private void createTabulationLevel(Entity entity, int level){
        List<Entity> entities = entity.getChildren();
        if (entities.size() == 0){
            entity.setLevel(level);
            return;
        }
        for (Entity child : entities){
            createTabulationLevel(child, level + 1);
        }
        entity.setLevel(level);
    }
}
