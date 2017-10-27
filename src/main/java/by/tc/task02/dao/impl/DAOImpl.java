package by.tc.task02.dao.impl;

import by.tc.task02.dao.DAO;
import by.tc.task02.dao.DAOException;
import by.tc.task02.dao.parser.FileParser;
import by.tc.task02.dao.parser.StringParser;
import by.tc.task02.entity.Entity;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DAOImpl implements DAO {
    @Override
    public Entity getRootEntity(URL fileURL) throws DAOException {
        FileParser fileParser = new FileParser(fileURL);

        Entity entity;
        try {
            String fileString = fileParser.fileToString();
            StringParser stringParser = new StringParser();

            List<Entity> entities = stringParser.getChild(fileString);

            boolean wrongData;
            wrongData = entities == null || entities.size() != 1;
            if (wrongData){
                return null;
            }

            entity = entities.get(0);

            createTabulationLevel(entity, 0);

        } catch (IOException e) {
            throw new DAOException("IOException", e);
        }

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
