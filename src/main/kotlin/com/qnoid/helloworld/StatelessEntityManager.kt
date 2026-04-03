package com.qnoid.helloworld

import jakarta.ejb.EJBException
import jakarta.ejb.Stateless
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext


@Stateless
open class StatelessEntityManager {

    @PersistenceContext(name = "foo")
    private lateinit var em: EntityManager

    open fun <T> persist(obj: T) {
        this.em.persist(obj)
    }

    @Throws(EJBException::class)
    open fun <T> getResultList(name: String, resultClass: Class<T>): List<T> {
        return this.em.createNamedQuery(name, resultClass).resultList
    }

}