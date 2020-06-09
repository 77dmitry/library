package com.burst.library.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao {

    @PersistenceContext
    EntityManager entityManager;

}
