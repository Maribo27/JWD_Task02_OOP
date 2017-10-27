package by.tc.task02.main;

import by.tc.task02.entity.Entity;
import by.tc.task02.service.EntityService;
import by.tc.task02.service.ServiceException;
import by.tc.task02.service.ServiceFactory;

public class Main {
    public static void main(String[] args) {
        Entity entity;

        ServiceFactory factory = ServiceFactory.getInstance();
        EntityService service = factory.getEntityService();

        try {
            entity = service.getRootEntity("store.xml");
            PrintTreeStructureInfo.print(entity);
        } catch (ServiceException exception){
            System.err.println(exception.getMessage());
            System.exit(-1);
        }


    }
}