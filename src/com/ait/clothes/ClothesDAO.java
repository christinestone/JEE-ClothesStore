package com.ait.clothes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


@Stateless
@LocalBean
public class ClothesDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	public List<ClothesStore> getAllClothes() {
    	Query query = em.createQuery("SELECT c FROM ClothesStore c");
        return query.getResultList();
	}
	
	public List<ClothesStore> getBrand(String brand){
		Query query = em.createQuery("SELECT c FROM ClothesStore c WHERE c.brand=:brand");
    	query.setParameter("brand", brand);
    	System.out.println("List Size:"+query.getResultList().size());
		 return query.getResultList();
	}
	
	public List<ClothesStore> findClothes(int start, int amount, 
			String colName,String searchTerm,
			String sortDir){
		String query="SELECT c FROM ClothesStore c ORDER BY "+colName+" "+sortDir;
		
		if (!searchTerm.equals("")){
			String search=" WHERE (c.id LIKE '%"+searchTerm+"%'"
                    + " OR c.product LIKE '%"+searchTerm+"%'"
                    + " OR c.brand LIKE '%"+searchTerm+"%'"
                    + " OR c.size LIKE '%"+searchTerm+"%'"
                    + " OR c.price LIKE '%"+searchTerm+"%'"
                    + " OR c.colour LIKE '%"+searchTerm+"%'"
                    + " OR c.fabric LIKE '%"+searchTerm+"%'"
                    + " OR c.origin LIKE '%"+searchTerm+"%'"
                    + " OR c.description LIKE '%"+searchTerm+"%'"
                    + " OR c.colour LIKE '%"+searchTerm+"%')";
			query="SELECT c FROM ClothesStore c "+search+"ORDER BY "+colName+" "+sortDir;
			System.out.println(query);
		}
		Query q =em.createQuery(query);
		q.setFirstResult(start);
		q.setMaxResults(amount);
		return q.getResultList();
	}
	
	public long getCount() {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM ClothesStore c",Long.class);
		long count= query.getSingleResult();
		return count;
			
	}
	
//	public List<ClothesStore> getByName(String name){
//		Query query = em.createQuery("SELECT c FROM ClothesStore AS c WHERE c.name LIKE ?1");
//		query.setParameter(1, "%"+name.toUpperCase()+"%");
//		return query.getResultList();
//	}
	
	public ClothesStore getClothes(int id){
		return em.find(ClothesStore.class, id);
	}
	
	public void save(ClothesStore clothes){
		em.persist(clothes);
	}
	
	public void update(ClothesStore clothes){
		em.merge(clothes);
	}
	
	public void delete(int id){
		em.remove(getClothes(id));
	}
}

