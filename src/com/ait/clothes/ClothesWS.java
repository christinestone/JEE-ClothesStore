package com.ait.clothes;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/clothes")
@Stateless
@LocalBean
public class ClothesWS {
	
	@EJB
	ClothesDAO clothesStoreDao = new ClothesDAO();
	@EJB
	private ClothesDataTable clothesDataTable;
	
	String[] cols={"id","product","brand","size","price","colour"};
//	
//	@GET
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response findAll() {
//		System.out.println("get all clothes");
//		List<ClothesStore> clothes = clothesStoreDao.getAllClothes();
//		return Response.status(200).entity(clothes).build();
//	}
	
	
	@GET
	@Path("/search/Needle&Thread")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findBrand() {
		System.out.println("get brand");
		List<ClothesStore> clothes = clothesStoreDao.getBrand("Needle & Thread");
		return Response.status(200).entity(clothes).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getData(
		 @QueryParam("sEcho") Integer sEcho,
         @QueryParam("iDisplayLength") Integer iDisplayLength,
        @QueryParam("iDisplayStart") Integer iDisplayStart,
       @QueryParam("sSearch") String search,
        @QueryParam("iSortCol_0") String iSortCol_0,
        @QueryParam("sSearch") String sSearch,
        @QueryParam("sSortDir_0") String sSortDir_0) {
		int col = Integer.parseInt(iSortCol_0);
	    String colName=cols[col];
		System.out.println("getData");
		System.out.println("invoking GET");
		System.out.println("sEcho"+sEcho);
		System.out.println("idisplayLength"+iDisplayLength);
		System.out.println("idisplayStart"+iDisplayStart);
		System.out.println("sSearch"+search);
		System.out.println("sSortDir_0"+sSortDir_0);
		System.out.println("sSortCol_0"+iSortCol_0);
		System.out.println("sSearch"+sSearch);
		
		List<ClothesStore> itemList= clothesStoreDao.findClothes(iDisplayStart,iDisplayLength,colName,sSearch,sSortDir_0);
		clothesDataTable.setAaData(itemList);
		 long totalRecords=clothesStoreDao.getCount();
		 clothesDataTable.setiTotalRecords(String.valueOf(totalRecords));
		 clothesDataTable.setiTotalDisplayRecords(String.valueOf(totalRecords));
		 clothesDataTable.setsEcho(sEcho);
		return Response.status(200).entity(clothesDataTable).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{id}")
	public Response findById(@PathParam("id") int id){
		ClothesStore clothes = clothesStoreDao.getClothes(id);
		return Response.status(200).entity(clothes).build();
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveProduct(ClothesStore clothes){
		clothesStoreDao.save(clothes);
		return Response.status(200).entity(clothes).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateProduct(ClothesStore clothes){
		clothesStoreDao.update(clothes);
		return Response.status(200).entity(clothes).build();	
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteClothes(@PathParam("id") int id){
		clothesStoreDao.delete(id);
		return Response.status(204).build();
	}
	
//	@GET
//	@Path("/search/{query}")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response findByName(@PathParam("query") String query) {
//		System.out.println("findByName: " +query);
//		List<ClothesStore> clothes = clothesStoreDao.getByName(query);
//		return Response.status(200).entity(clothes).build();
//	}



}
