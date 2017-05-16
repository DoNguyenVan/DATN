package com.nguyenvando.DaoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvando.Dao.MyAppDao;

@Repository
@Transactional
public class MyAppDaoImpl implements MyAppDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(Class<T> entityClass) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		return criteria.list();
	}

	@Override
	public <T> void insertOrUpdate(T entity) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
		}catch(Exception e){
			if(sessionFactory.getCurrentSession().getTransaction().isActive()){
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}		
		}
	}

	@Override
	public <T> boolean IsValidObject(T entity, String searchColumn, String searchValue) {
		try{
			Session session = sessionFactory.getCurrentSession();	
			Criteria crit = session.createCriteria(entity.getClass()) ;
			crit.add(Restrictions.eq(searchColumn, searchValue));
			if(crit.uniqueResult() != null){
				return true;
			}
			return false;
		}catch(Exception e){
			if(sessionFactory.getCurrentSession().getTransaction().isActive()){
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}		
			return false;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntityById(Class<T> entityClass, int id) {
		return (T) sessionFactory.getCurrentSession().get(entityClass,id);
	}

	@Override
	public <T> int getMaxId(T entity,String colum) {
		Session session = sessionFactory.getCurrentSession();	
		Criteria criteria = session.createCriteria(entity.getClass()).setProjection(Projections.max(colum));
		if(criteria.uniqueResult() != null){			
			return (int)criteria.uniqueResult();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getDistinctList(Class<T> entityClass) {
		Session session = sessionFactory.getCurrentSession();	
		Criteria crit = session.createCriteria(entityClass) ;
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	@Override
	public <T> void deleteEntity(T entity) {	
		try{
			sessionFactory.getCurrentSession().delete(entity);
		}catch(Exception e){
			if(sessionFactory.getCurrentSession().getTransaction().isActive()){
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}		
		}
	}

	@Override
	public <T> long countAllEntities(Class<T> entityClass) {
		return (Long) sessionFactory.getCurrentSession().createCriteria(entityClass)
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByColumn(Class<T> entityClass, String searchColum, Integer searchValue) {
		Session session = sessionFactory.getCurrentSession();
		ArrayList<T> result = new ArrayList<T>();
		Criteria crit = session.createCriteria(entityClass) ;
		crit.add(Restrictions.eq(searchColum, searchValue));
		System.out.println(crit.list());
		if(crit.list() !=null){
			result = (ArrayList<T>) crit.list();
		}
		return result ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListBetween(Class<T> entityClass, String property, Date beginDate, Date endDate) {
		List<T> result = new ArrayList<T>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.between(property,beginDate, endDate));
		if(criteria.list()!=null) {
			result=criteria.list();
		}
		return result;
	}


}
