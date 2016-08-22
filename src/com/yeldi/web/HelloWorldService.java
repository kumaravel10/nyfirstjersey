package com.yeldi.web;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.yeldi.web.bean.User;
import com.yeldi.web.dao.UserDAO;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

 
@Path("/customers")
public class HelloWorldService {
	public Customer cus;
	//total records
	List<User> tr=new ArrayList<User>();;
  @GET
  @Produces("text/html")
  public Response getLocalCust() 
  {
 
           String output = "I am from 'getLocalCust' method";
           return Response.status(200).entity(output).build();
  }
 
  @GET
  @Path("/nri")
  @Produces("text/html")
  public Response getNriCust() 
  {
 
            String output = "I am from 'getNriCust' method";
            return Response.status(200).entity(output).build();
  }
  
  
//USAGE OF @PATHPARAM
	/*1.Usage of @PathParam: To pass the URL with parameter values as below stated
	  2.http://localhost:8080/nyfirstjersey/rest/customers/details/kumar/usa
	  3.The injection error you receive is caused by Number parameters. Since complex objects like Number are not supported in @PathParam implementation. 
	  You could easily fix this by the following code (also add the @GET)
	*/
    @GET
	@Path("/details/{name}/{country}")
	@Produces(MediaType.TEXT_HTML)
	public Response getResultByPassingString(@PathParam("name") String name,@PathParam("country") String country)
	{
	
   	String output="customer name"+name+"country name"+country;
		return Response.status(200).entity(output).build() ;
	}
    
    
    //http://localhost:8080/nyfirstjersey/rest/customers/hello
    
    @GET
	 @Path("/hello")
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
         }
    //http://localhost:8080/nyfirstjersey/rest/customers/inch/10
    
    @GET
	@Path("/inch/{i}")
	@Produces(MediaType.TEXT_XML)
	public String covertInchtoFeet(@PathParam("i") int i)
	{
		int inch=i;
		double feet=0;
		feet=(double) inch/12;
		
		return "<inchtofeet>"+"<inch>"+inch+"</inch>"+"<feet>"+feet+"</feet>"+"</inchtofeet>";
	}
	
  
    //http://localhost:8080/nyfirstjersey/rest/customers/cusdetails/10
  @GET
  @Path("/cusdetails/{cusNo}")
  @Produces(MediaType.APPLICATION_JSON)
  public String produceCustomerDetailsinJSON(@PathParam("cusNo") int no) {

      /*
       * I AM PASSING CUST.NO AS AN INPUT, SO WRITE SOME BACKEND RELATED STUFF AND
       * FIND THE CUSTOMER DETAILS WITH THAT ID. AND FINALLY SET THOSE RETRIEVED VALUES TO
       * THE CUSTOMER OBJECT AND RETURN IT, HOWEVER IT WILL RETURN IN JSON FORMAT :-)
       * */
      
      Customer cust = new Customer();        
          cust .setCustNo(no);
          cust .setCustName("Java4s");
          cust .setCustCountry("India");
      return cust.toString();
  }
  
  
//USAGE OF @FORMPARAM
  /*
	 using @FormParam annotation, RESTful web service would accept HTML form parameters sent by the client in the POST request and bind them to the method variables. Generally @FormParam will come into picture when client send the data in POST request, if its the GET request @QueryParam would be the best choice.
Let me give you an example on usage of @FormParam in the JAX-RS.
	 */
  //http://localhost:8080/nyfirstjersey/rest/customers/feedback/10/kumar/india/good
  @POST
  @Path("/feedback")
  @Produces(MediaType.APPLICATION_JSON)
  public Customer feedback(@FormParam("custId") int custId,@FormParam("custName") String custName,@FormParam("custCountry") String custCountry,@FormParam("custFeedback") String custFeedback)
  {
	  Customer cus=new Customer();
	  cus.setCustNo(custId);
	  cus.setCustName(custName);
	  cus.setCustCountry(custCountry);
	  cus.setCustFeedback(custFeedback);
	 
	  return cus;
	  
	  
  }
  
  
  @POST
  @Path("/xmlfeedback")
  @Produces(MediaType.APPLICATION_XML)
  public Customer xmlfeedback(@FormParam("custId") int custId,@FormParam("custName") String custName,@FormParam("custCountry") String custCountry,@FormParam("custFeedback") String custFeedback)
  {
	  Customer cus=new Customer();
	  cus.setCustNo(custId);
	  cus.setCustName(custName);
	  cus.setCustCountry(custCountry);
	  cus.setCustFeedback(custFeedback);
	 
	  return cus;
	  
	  
  }
  
  
//USAGE OF @FORMPARAM
  /*
	 using @FormParam annotation, RESTful web service would accept HTML form parameters sent by the client in the POST request and bind them to the method variables. Generally @FormParam will come into picture when client send the data in POST request, if its the GET request @QueryParam would be the best choice.
Let me give you an example on usage of @FormParam in the JAX-RS.
	 */
  
  @POST
  @Path("/feedbackForm")
  @Produces(MediaType.TEXT_HTML)
  public Response feedbackF(@FormParam("custId") int custId,@FormParam("custName") String custName,@FormParam("custCountry") String custCountry,@FormParam("custFeedback") String custFeedback)
  {
	  cus=new Customer();
	  cus.setCustNo(custId);
	  cus.setCustName(custName);
	  cus.setCustCountry(custCountry);
	  cus.setCustFeedback(custFeedback);
	  
	  return Response.status(200).entity("updated:::"+"customer id::"+cus.getCustNo()+"customer Name::"+cus.getCustName()+"customer country::"+cus.getCustCountry()+"customer feedback::"+cus.getCustFeedback()).build();
  }
  
  @GET
  @Path("/queryparam")
  @Produces(MediaType.TEXT_HTML)
  //URL:http://localhost:8080/nyfirstjersey/rest/customers/queryparam?nameKey=kumar&countryKey=india
  
  public Response getByPassingValue(@QueryParam("nameKey") String name,@QueryParam("countryKey") String country)
  {
	   String output="customer name--- "+name+"country name--- "+country;
	   return Response.status(200).entity(output).build();
  }
  
  @GET  
  @Path("/getfeedback")
  @Produces(MediaType.APPLICATION_JSON)
  public Customer getfeedback()
  {
	  
	  return cus;
	  
	  
  }
  
  //USAGE OF @MATRIXPARAM
  /*
  here how a RESTful web services would accept multiple parameters sent by the client in the HTTP URL as Matrix Params. 
  So what are matrix parameters ? let me give you the syntax.
  
  Consider this URL
http://localhost:2013/<projectRoot>/rest/customers;nameKey=Java4s;countryKey=USA
If you observe the URL, i am passing 2 parameters nameKey=Java4s & countryKey=USA.  
One parameter is separated from another with a semicolon, similarly you can pass any number of parameters. 
These type of parameters are called as Matrix Parameters. I will explain more about matrix parameters in this example.
 http://localhost:8080/Mywebservices/rest/master;nameKey=kumar;idKey=01
  */
  
  
  @GET
  @Path("/matrixParam")
  @Produces(MediaType.TEXT_HTML)
  public Response getbyPassingByvalue(@MatrixParam("nameKey") String name,@MatrixParam("idKey") String id)
  {
	   String output="customer name--- "+name+"custemer id--- "+id;
	   return Response.status(200).entity(output).build();
  }
  
  @DELETE
  @Produces("text/plain")
  @Path("/cusdelete/{id}")
  public Response deleteCustomer(@PathParam("id") String custId) {
      
       //CODE TO DELETE CUSTOMER DETAILS USING CUSTOMER ID
      String output = "Customer With ID "+custId+" Has Been Deleted From the Database.";
      return Response.status(200).entity(output).build();
   }
  
  
  @PUT
  @Produces("text/plain")
  @Path("/update/{id}")
  public Response updateCustomerDetails(@PathParam("id") String custId) {
      
       //CODE TO UPDATE CUSTOMER DETAILS USING CUSTOMER ID
      String output = "Customer Data With ID "+custId+" Has Been Updated Successfully";
      return Response.status(200).entity(output).build();
   }
  
  
  @POST
  @Path("/userinert")
  @Produces(MediaType.TEXT_HTML)
  public Response userinsert(@FormParam("userName") String userName,@FormParam("password1") String password,@FormParam("email") String email,@FormParam("phone") String phone,@FormParam("city") String city)
  {
	  UserDAO us=new UserDAO();
	  us.addUserDetails(userName, password, email,phone, city);
	  
	  
	  return Response.status(200).entity("updated").build();
  }
  
  @GET
  @Path("/userinertpathParam/{userName}/{password1}/{email}/{phone}/{city}")
  @Produces(MediaType.APPLICATION_XML)
  public Response userinsertPathParam(@PathParam("userName") String userName,@PathParam("password1") String password,@PathParam("email") String email,@PathParam("phone") String phone,@PathParam("city") String city)
  {
	  UserDAO us=new UserDAO();
	  us.addUserDetails(userName, password, email,phone, city);
	  
	  
	  return Response.status(200).entity("updated").build();
  }
  
  @GET  
  @Path("/getUserDetails")
  @Produces(MediaType.APPLICATION_JSON)
  public List<User> getUser()
  {
	  List<User> li=new ArrayList<User>();
	  UserDAO ud=new UserDAO();
	  //User us=new User();
	  li=ud.getListUser();
	  
	  return li;
	  
	}
  
  
  @GET  
  @Path("/getUserDetailsXml")
  @Produces(MediaType.APPLICATION_XML)
  public List<User> getUserXMl()
  {
	  
	  UserDAO ud=new UserDAO();
	  //User us=new User();
	  tr=ud.getListUser();
	  
	  return tr;
	  
	  
	  
  }
  
  @GET  
  @Path("/getUserDetailsXmlsr")
  @Produces(MediaType.APPLICATION_XML)
  public List<User> getUserXMlsr()
  {
	  
	  List<User> sr=tr;
	  List<User> sp=new ArrayList<User>();
	  
	  Iterator<User> itr=sr.iterator();
	  while(itr.hasNext())
	  {
		  User ur=(User)itr.next();
		  User uup=new User();
			 uup.setUserName(ur.getUserName());
			 uup.setPassword1(ur.getPassword1());
	      sp.add(uup); 
		  
		  
		  
		  
	  }
	  
	  return sp;
	  
	  
	  
  }
  
  @GET  
  @Path("/getUserDetailsXmlbyid/{id}")
  @Produces(MediaType.APPLICATION_XML)
  public List<User> getUserXMlbyid(@PathParam("id") int id)
  {
	System.out.println(id); 
	  UserDAO ud=new UserDAO();
	  List<User> li=new ArrayList<User>();
	  
	  //User us=new User();
	  li=ud.getUserByid(id);
	 
	 
	  
	  return li;
	  
	 
	  
	  
	  
  }
  
  @SuppressWarnings("null")
  @GET  
  @Path("/getUserDetailsJsonbyidup/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<User> getUserXMlbyUP(@PathParam("id") int id)
  {
	System.out.println(id); 
	  UserDAO ud=new UserDAO();
	  List<User> li=new ArrayList<User>();
	  List<User> ld=new ArrayList<User>();
	  
	  //User us=new User();
	  if(li!=null)
	  {
	  li=ud.getUserByid(id);
	  Iterator<User> itr=li.iterator();
	  while(itr.hasNext())
	  {
		 User us=(User)itr.next();
		 User uup=new User();
		 uup.setUserName(us.getUserName());
		 uup.setPassword1(us.getPassword1());
		 
		 
		 ld.add(uup);
		 
		 
	  }
	  }
	  
      return ld;
	  
	 
	  
	  
	  
  }
  
  
  
  
  
  
  
}