package by.zazybo.api.service;

import by.zazybo.api.service.exception.ServiceException;

import java.util.Map;

public interface Service {

    String getById(int id) throws ServiceException;
    String getAll(Map<String,String> params) throws ServiceException ;

}
