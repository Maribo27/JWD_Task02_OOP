package by.tc.task02.dao;

import by.tc.task02.entity.Entity;

import java.net.URL;

public interface DAO {
    Entity getRootEntity(URL fileURL);
}
