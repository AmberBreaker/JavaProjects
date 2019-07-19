package com.shrm.service;

import com.shrm.bean.Person;
import org.springframework.stereotype.Service;

public interface PersonService {

    Person findPersonById(long id);

}
