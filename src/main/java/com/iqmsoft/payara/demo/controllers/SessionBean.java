package com.iqmsoft.payara.demo.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import com.iqmsoft.payara.demo.models.MemEntity;

import java.util.Collection;


@Stateless
public class SessionBean {

    @PersistenceContext(unitName = "TeamInfoPU")
    EntityManager entityManager;

    public void createTeamMember(MemEntity memberEntity) {
        try {
            entityManager.persist(memberEntity);
        } catch (ConstraintViolationException e) {
            System.out.println("Could not create team member:" + e);
        }
    }

    public MemEntity getTeamMemberById(Long Id) {
        return entityManager.find(MemEntity.class, Id);
    }

    public Collection<MemEntity> getTeamMemberByName(String name) {
        Query query = entityManager.createQuery("SELECT m FROM MemEntity m WHERE m.name = '" + name + "'");
        return (Collection<MemEntity>) query.getResultList();
    }

    public void removeTeamMember(Long Id) {
        MemEntity member = entityManager.find(MemEntity.class, Id);
        if (member != null) {
            //entityManager.getTransaction().begin();
            entityManager.remove(member);
            //entityManager.getTransaction().commit();
        }
    }

    public void updateTeamMemberName(Long Id, String name) {
        MemEntity member = entityManager.find(MemEntity.class, Id);
        if (member != null) {
            //entityManager.getTransaction().begin();
            member.setName(name);
            //entityManager.getTransaction().commit();
        }
    }

    public void  updateTeamMemberBio(Long Id, String bio) {
        MemEntity member = entityManager.find(MemEntity.class, Id);
        if (member != null) {
            //entityManager.getTransaction().begin();
            member.setBio(bio);
            //entityManager.getTransaction().commit();
        }
    }

    public void updateTeamMemberEmail(Long Id, String email) {
        MemEntity member = entityManager.find(MemEntity.class, Id);
        if (member != null) {
            //entityManager.getTransaction().begin();
            member.setEmail(email);
            //entityManager.getTransaction().commit();
        }
    }

}
