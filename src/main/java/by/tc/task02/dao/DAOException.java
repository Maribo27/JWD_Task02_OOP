package by.tc.task02.dao;

public class DAOException extends Exception{
    public DAOException(String exceptionMessage, Throwable throwable){
        super(exceptionMessage, throwable);
    }
}
