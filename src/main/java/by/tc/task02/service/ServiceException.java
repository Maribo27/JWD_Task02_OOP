package by.tc.task02.service;

public class ServiceException extends Exception {
    public ServiceException(String exceptionMessage, Throwable throwable){
        super(exceptionMessage, throwable);
    }
}
