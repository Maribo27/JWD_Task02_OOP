package by.tc.task02.dao.impl;

import by.tc.task02.dao.DAO;
import by.tc.task02.dao.Parser;
import by.tc.task02.entity.Entity;

import java.net.URL;

public class DAOImpl implements DAO {
    @Override
    public Entity getRootEntity(URL fileURL) {
        Parser parser = new Parser(fileURL);
        String fileString = parser.fileToString();
        if (fileString == null){
            return null;
        }
        return parser.getEntity(fileString);
    }

}
