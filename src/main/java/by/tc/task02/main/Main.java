package by.tc.task02.main;

import by.tc.task02.entity.Entity;
import by.tc.task02.service.EntityService;
import by.tc.task02.service.ServiceFactory;

public class Main {
    public static void main(String[] args) {
        Entity entity;

        ServiceFactory factory = ServiceFactory.getInstance();
        EntityService service = factory.getEntityService();

        entity = service.getRootEntity("test1.xml");

        PrintTreeStructureInfo.print(entity, 0);
    }
}