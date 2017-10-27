package by.tc.task02.service.impl;

import by.tc.task02.dao.DAO;
import by.tc.task02.dao.DAOException;
import by.tc.task02.dao.DAOFactory;
import by.tc.task02.entity.Entity;
import by.tc.task02.service.EntityService;
import by.tc.task02.service.ServiceException;
import by.tc.task02.service.validation.Validator;

import java.net.URL;

public class EntityServiceImpl implements EntityService {

    @Override
    public Entity getRootEntity(String filename) throws ServiceException {

        if (!Validator.fileFound(this.getClass(), filename)) {
            throw new ServiceException("File not found", null);
        }

        URL fileURL = this.getClass().getClassLoader().getResource(filename);

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        Entity entity;
        try {
            entity = entityDAO.getRootEntity(fileURL);
        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }
        return entity;
    }

}
