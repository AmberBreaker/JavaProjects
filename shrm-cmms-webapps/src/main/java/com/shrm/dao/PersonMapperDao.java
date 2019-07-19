package com.shrm.dao;

import com.shrm.bean.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapperDao {

    Person findPersonById(long id);

}
