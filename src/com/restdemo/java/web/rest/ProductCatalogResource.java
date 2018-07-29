package com.restdemo.java.web.rest;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.restdemo.java.bean.Product;

@Path("productcatalog")
public class ProductCatalogResource {
	
	
private static Map<Integer, Product> productCatalog;


    
   /* public ProductCatalogResource() {
        
    }
*/
    
    @POST
    @Path("addCatalog")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Product product) {
        
    	Map<Integer, Product> p = MyRESTServices.getProductCatalog();
    	
    	Double catalogId = Math.random() * 10000 + 1 ;
    	
    	p.put(catalogId.intValue(), product);
    	
        return Response.ok(p.get(catalogId.intValue())).build();
    }
    
    
   
	@GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search() {
        
        return Response.ok(MyRESTServices.getProductCatalog()).build();
    }
	
	
	
	
	
	
	@PUT
	@Path("{catalogId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("catalogId") Integer catalogId,@FormParam("unitPrice") Double unitPrice){
		
		Map<Integer, Product> p = MyRESTServices.getProductCatalog();
		
		boolean catalogExists = p.containsKey(catalogId);
		
		if(!catalogExists){
			return Response.status(Response.Status.NOT_FOUND).entity("{'message':'catalogId not found'}").build();
		}
		
		//Double unitPrice = formParams.getFirst("unitPrice");
		
		Product prd = p.get(catalogId);
		
		if(unitPrice !=null){
			prd.setUnitPrice(unitPrice);
		}
		
		p.replace(catalogId, prd);
		
		return Response.ok(prd).build();
		
		
	}
	
	
	
	
	@DELETE
	@Path("{catalogId}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response deleteCatalog(@PathParam("catalogId") Integer catalogId){
		
		Map<Integer, Product> p = MyRESTServices.getProductCatalog();
		
		boolean catalogExists = p.containsKey(catalogId);
		
		if(!catalogExists){
			return Response.status(Response.Status.NOT_FOUND)
					.entity("{\"message\":\"catalogId not found\"}")
					.build();
		}
		
		p.remove(catalogId);
		
		return Response.status(Response.Status.OK)
				.entity("{\"message\":\"catalog deleted successfully\"}")
				.build();
		
	}
	
}
	
	/*@PUT
	@Path("{catalogId}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response update(Product product,@PathParam("catalogId") Integer catalogId){
		
		Map<Integer, Product> p = MyRESTServices.getProductCatalog();
		
		boolean catalogExists = p.containsKey(catalogId);
		
		if(!catalogExists){
			return Response.status(Response.Status.NOT_FOUND).entity("{'message':'catalogId not found'}").build();
		}
		
		Double unitPrice = product.getUnitPrice();
		
		Product prd = p.get(catalogId);
		
		if(unitPrice !=null){
			prd.setUnitPrice(unitPrice);
		}
		
		p.replace(catalogId, prd);
		
		return Response.ok(prd).build();
		
		
	}*/
    
    
    

 
    
    
    
    
    //-----------------------------------------------------------------------------------------------
	
    /*private static List<Product> productCatalog;
    
    public ProductCatalogResource() {
        initializeProductCatalog();
    }
    
    @SuppressWarnings("unchecked")
	@GET
    @Path("search/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product[] searchByCategory(@PathParam("category") String category) {
        @SuppressWarnings("rawtypes")
		List products = new ArrayList();
        for (Product p : productCatalog) {
            if (category != null && category.equalsIgnoreCase(p.getCategory())) {
            	products.add(p);
            }
        }
        return (Product[]) products.toArray(new Product[products.size()]);
    }
    
    
    @SuppressWarnings("unchecked")
	@GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Product[] searchByName(@QueryParam("name") String name) {
        @SuppressWarnings("rawtypes")
		List products = new ArrayList();
        for (Product p : productCatalog) {
            if (name != null && name.toLowerCase().startsWith(p.getName().toLowerCase())) {
                products.add(p);
            }
        }
        return (Product[]) products.toArray(new Product[products.size()]);
    }
    
    
    @POST
    @Path("insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Status insert(Product product) {
        productCatalog.add(product);
        return new Status("SUCCESS", "Inserted " + product.getName());
    }
    
    
    
    @PATCH
    @Path("/updateUnitPrice/{category}/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Status patchRow(@PathParam("category") String categorynew, @PathParam("name") String nameUpdated,Product product) 
    throws Exception{
    	
    	System.out.println("Inside PATCH");
    	//List products = new ArrayList();
        for (Product p : productCatalog) {
            if (nameUpdated != null && p.getName().equalsIgnoreCase(nameUpdated)) {
            	p.setUnitPrice(p.getUnitPrice());
            	productCatalog.add(p);
            }
        }
    	//product.setUnitPrice(unitPrice);
    	return new Status("SUCCESS", "updated unit price of " + product.getName());
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeProductCatalog() {
        if (productCatalog == null) {
            productCatalog = new ArrayList();
            productCatalog.add(new Product(1, "Keyboard", "Electronics", 29.99D));
            productCatalog.add(new Product(2, "Mouse", "Electronics", 9.95D));
            productCatalog.add(new Product(3, "17\" Monitor", "Electronics", 159.49D));
            productCatalog.add(new Product(4, "Hammer", "Hardware", 9.95D));
            productCatalog.add(new Product(5, "Screwdriver", "Hardware", 7.95D));
            productCatalog.add(new Product(6, "English Dictionary", "Books", 11.39D));
            productCatalog.add(new Product(7, "A House in Bali", "Books", 15.99D));
            productCatalog.add(new Product(8, "An Alaskan Odyssey", "Books", 799.99D));
            productCatalog.add(new Product(9, "LCD Projector", "Electronics", 1199.19D));
            productCatalog.add(new Product(10, "Smart Thermostat", "Electronics", 1199.19D));
        }
    }*/
