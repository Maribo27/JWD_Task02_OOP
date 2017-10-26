package by.tc.task02.service.impl;

import by.tc.task02.dao.DAO;
import by.tc.task02.dao.DAOFactory;
import by.tc.task02.entity.Entity;
import by.tc.task02.service.EntityService;
import by.tc.task02.service.validation.Validator;

import java.net.URL;

public class EntityServiceImpl implements EntityService {

    @Override
    public Entity getRootEntity(String filename) {

        if (!Validator.fileFound(this.getClass(), filename)) {
            return null;
        }

        URL fileURL = this.getClass().getClassLoader().getResource(filename);

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        return entityDAO.getRootEntity(fileURL);
    }

}
