package com.nguyenvando.Dao;

import java.util.Date;
import java.util.List;

public interface MyAppDao {
	
	public <T> List<T> getList(Class<T> entityClass);
	
	public <T> List<T> getDistinctList(Class<T> entityClass);
	
	public <T> List<T> getListByColumn(Class<T> entityClass,String searchColum,Integer searchId);
	
	public <T> void insertOrUpdate(final T entity);
	
	public <T> boolean IsValidObject(final T entity,String searchColum,String searchValue);
	
	public <T> T getEntityById(Class<T> entityClass,int id);
	
	public <T> int getMaxId(T entity,String column);	
	
	public <T> void deleteEntity(final T entity);
	
	public <T> long countAllEntities(Class<T> entityClass);
	
	public <T> List<T> getListBetween(Class<T> entityClass,String property, Date beginDate, Date endDate);
	
	
}
