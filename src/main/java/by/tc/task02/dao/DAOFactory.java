package by.tc.task02.dao;

import by.tc.task02.dao.impl.DAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final DAO entityDAO = new DAOImpl();

    private DAOFactory() {}

    public DAO getEntityDAO() {
        return entityDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}