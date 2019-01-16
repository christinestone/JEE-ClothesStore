package com.ait.clothes;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ClothesDataTable {
	private Integer sEcho;
	private String iTotalRecords;
	private String iTotalDisplayRecords;
	private List<ClothesStore> aaData;

    @PersistenceContext
    private EntityManager em;
    
    public String getiTotalRecords() {
        return iTotalRecords;
    }
 
    public void setiTotalRecords(String iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }
    
    public String getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }
 
    public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }
    
    public List<ClothesStore> getAaData() {
        return aaData;
    }
 
    public void setAaData(List<ClothesStore> aaData) {
        this.aaData = aaData;
    }
    public Integer getsEcho() {
        return sEcho;
    }
 
    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }
	  
}
