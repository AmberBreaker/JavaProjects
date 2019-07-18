package com.shrm.service.impl;

import com.shrm.bean.Person;
import com.shrm.dao.PersonMapperDao;
import com.shrm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapperDao personMapperDao;

    public Person findPersonById(long id) {
        return personMapperDao.findPersonById(id);
    }
}
