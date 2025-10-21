package services.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import services.interfaces.ICRUD;
import util.JPAUtil;

import java.util.List;

public class MyDao implements ICRUD {
    @Override
    public <T> List<T> getAll(String nameQuery, Class<T> clazz) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            TypedQuery<T> query = em.createNamedQuery(nameQuery, clazz);
            return query.getResultList();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public <T> void insert(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
    }

    @Override
    public <T> void update(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }

    @Override
    public <T> void delete(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }

    @Override
    public <T> T findById(Integer id, Class<T> clazz) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            T entity = em.find(clazz, id);
            return entity;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return null;
    }
}
