package by.zazybo.domain.service;

import by.zazybo.domain.service.exception.ServiceException;

import java.util.Map;

public interface Service {

    String getById(int id) throws ServiceException;
    String getAll(Map<String,String> params) throws ServiceException ;

}
