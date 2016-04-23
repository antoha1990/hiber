package org.daoImp;

import org.dao.GeneralDAO;
import org.dao.GeneralDAO;
import org.hibernate.Session;
import org.media.HibernateUtil;
import org.media.Video;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by anton on 19.04.2016.
 */
public class GeneralDAOIml<E> implements GeneralDAO<E> {

    private Class<E> elementClass;//если E=Video то elementClass= Video.class

    public GeneralDAOIml(Class<E> elementClass){
        this.elementClass = elementClass;
    }//elementClass=Video.class

    public void addElement(E e) throws SQLException{

        Session session=null;

        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(e);
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(session!=null && session.isOpen()) session.close();
        }


    }

    public void deleteElement(E e) throws SQLException{
        Session session=null;

        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(e);
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(session!=null && session.isOpen()) session.close();
        }
    }

    public E getElement(int id)throws SQLException{

        Session session=null;
        E result=null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result= (E) session.get(elementClass,id);//load(Video.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null && session.isOpen()) session.close();
        }

        return result;
    }

    public List<E> getElements()throws SQLException{
        Session session=null;
        List<E> result=null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result=  session.createCriteria(elementClass).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null && session.isOpen()) session.close();
        }

        return result;
    }

}
