package com.yeldi.web.dao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Properties;
import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.SQLQuery;


import com.yeldi.web.bean.User;

public class UserDAO {
	
	// 1. configuring hibernate
    Configuration configuration = new Configuration().configure();

    // 2. create sessionfactory
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    // 3. Get Session object
    //Session session = sessionFactory.openSession();
    
    
    //Add user details
    public boolean addUserDetails(String userName, String password, String email,String phone, String city) 
	{
    	Session session = sessionFactory.openSession();
    	if(isExistingUser(userName)) return false;
    	try 
        {
            
            System.out.println(":::::"+session);
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setUserName(userName);
            user.setPassword1(password);
            user.setEmail(email);
            user.setCity(city);
            user.setPhone(phone);
            session.save(user);
            transaction.commit();
            System.out.println("\n\n Details Added \n");
 
        } 
        catch (HibernateException e) 
        {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
        
        finally
        {
        	session.flush();
        	session.close();
        }
        
        return true;
	}
    
    //getProperties
    public void getProp()
    {
    	Properties configFile = new Properties();
		try {
			configFile.load(UserDAO.class.getClassLoader().getResourceAsStream("config.properties"));
			String name = configFile.getProperty("name");
			String company = configFile.getProperty("company");
			System.out.println(name);
			System.out.println(company);
		} catch (IOException e) {
 
			e.printStackTrace();
		}
    }
    
    
    
    //checking already user
    public boolean isExistingUser(String userName )
    {
        // int id=3;
    	
    	String username=null;
    	Session session = sessionFactory.openSession();
    	 boolean result=false;
    	 Transaction tx=null;
    	 try
    	 {
    		/*User us=new User();
    		us.setId(id);
    	     tx=session.getTransaction();
    		 tx.begin();
    		 System.out.println("::::::"+us.getId());
    		 User use=(User)session.load(User.class,new Integer(us.getId()));
    		 tx.commit();*/
    		 User us=new User();
     		 us.setUserName(userName);
     	     tx=session.getTransaction();
     		 tx.begin();
     		 username=us.getUserName();
     		 System.out.println("::::::"+username);
     		 Query query=session.createQuery("from user where USERNAME='"+username+"'");
     		 List<User> use=(List) query.uniqueResult();
     		 tx.commit();
    		 
    		  if(use!=null)
    		 {
    			 result=true;
    		 }
    		 
    	}
    	  catch (HibernateException e) 
         {
             System.out.println(e.getMessage());
             System.out.println("error");
         }
     		finally
         	{
     			session.flush();
     			session.close();
         	}
    	 
    	 return result;
 	}
     
    	
    
    
    
    //update user
    public void updateUserDetails(int id,String userName,String empid, String password, String email,String phone, String city) 
	{
        
            
 
            // 4. Starting Transaction
        	User us=null;
    		Transaction tx=null;
    		Session session = sessionFactory.openSession();
    		try
    		{
    			tx=session.beginTransaction();
    			 us=(User)session.load(User.class,new Integer(id));
    			 us.setId(id);
    			 us.setUserName(userName);
    			 us.setEmployeetype(empid);
    	         us.setPassword1(password);
    	         us.setEmail(email);
    	         us.setPhone(phone);
    	         us.setCity(city);
    	            session.update(us);
    	            session.getTransaction().commit();
    	            System.out.println("\n\n Details Added \n");
    		}
    		
        catch (HibernateException e) 
        {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    		finally
        	{
    			session.flush();
    			session.close();
        	}
	}
    
    
   //list service method
     public  List<User> getListUser()
     {
    	 Session session = sessionFactory.openSession();
    	 List<User> list=new ArrayList<User>();
    	 Transaction tx=null;
    	 
    	try {
    		 tx=session.getTransaction();
    		 tx.begin();
    		 list=session.createQuery("from User").list();
    		 tx.commit();
    	    }
    	catch(Exception e)
    	{
    		if(tx!=null)
    		{
    			tx.rollback();
    		}
    		e.printStackTrace();
    	}
    	finally
    	{
    		session.flush();
    		session.close();
    	}
    	return list;
     }
     
  
     //delete service method
     public void deleteUserDetails(int id)
   {
    	 Session session = sessionFactory.openSession();
    	 Transaction tx=null;
	   try
	   {
		 tx=session.beginTransaction();
		 User use=(User)session.load(User.class,new Integer(id));
		 session.delete(use);
		 session.getTransaction().commit();
	   }
	   catch(Exception e)
	   {
		   if(tx!=null)
		   {
			   tx.rollback();
		   }
		   e.printStackTrace();
		   
	   }
	   finally
	   {
		   session.flush();
		   session.close();
	   }
   }
     
     
     //set id for view by customer id
    /* public List<User> getUserByid(int id)
     {
    	 List<User> list=new ArrayList<User>();
    	// User us=null;
    	 Transaction tx=null;
    	 try
    	 {   tx=session.getTransaction();
    		 tx.begin();
    		 list=(List<User>)session.load(User.class, new Integer(id));
    		 tx.commit();
    		
         }
    	 catch(Exception e)
    	 {
    		 if(tx!=null)
    		 {
    			 tx.rollback();
    		 }
    		 e.printStackTrace();
    	 }
    	 
    	 finally
  	   {
  		   session.flush();
  		   session.close();
  	   }
    	 return list;
    	
     }*/
     
     
    @SuppressWarnings("null")
	public List<User >getUserByid(int id)
     {
    	//Session session = sessionFactory.openSession();
    	List<User> u=new ArrayList<User>();
    	User us=null;
    	try
    	{
    		Session session = sessionFactory.openSession(); 
    	  us= (User) session.get(User.class, new Integer(id));
    	 // u.add(us);
          if(us!=null)
          {
    	  u.add(us);
          }
          else
          {
        	  User er=new User();
        	  er.setId(id);
        	  er.setStatus("Not available");
        	  u.add(er);
          }
    	  /*if(us!=null)
    	  {
    	  u.add(us);
    	  System.out.println(u);
    	  }
    	  else
    	  {
    		 us.setId(0);
    		  us.setUserName("nodata");
    		  us.setPassword1("nodata");
    		  us.setCity("nodata");
    		  us.setPhone("nodata");
    		  us.setEmail("nodata");
    		  u.add(us);
    	  }*/
    	  System.out.println(u);
    	 
    	}
    	catch(Exception e)
		   {
			  
			 e.printStackTrace();
			   
		   }
    	/*finally
		   {
			   session.flush();
			   session.close();
		   }*/
 		
 		
    	
    	return u;
    	
     }
    
    
    
    	
    	
    	
    	
    	
    	//---------------------
    	
    	//-----------------login------
    	public User getLoginDetails(int id)
    	{
    		//List<User> list=new ArrayList<User>();
    		Session session = sessionFactory.openSession();
    		User us=null;
    		Transaction tx=null;
    		try
    		{
    			tx=session.beginTransaction();
    			 us=(User)session.load(User.class,new Integer(id));
    			System.out.println(us);
    		}
    		
    		catch(Exception e)
 		   {
 			   if(tx!=null)
 			   {
 				   tx.rollback();
 			   }
 			   e.printStackTrace();
 			   
 		   }
    		
    	finally
 		   {
 			   session.flush();
 			   session.close();
 		   }
    		
    	return us;
    		
    	}
    	
    	
    	
    	 //get all the details and do all operation in local object
        
        public List<User> getAlltheDetails()
        {
        	Session session = sessionFactory.openSession();
        	List<User> list=new ArrayList<User>();
        	 Transaction tx=null;
        	 
        	try {
        		 tx=session.getTransaction();
        		 tx.begin();
        		 list=session.createQuery("from User").list();
        		 tx.commit();
        	    }
        	catch(Exception e)
        	{
        		if(tx!=null)
        		{
        			tx.rollback();
        		}
        		e.printStackTrace();
        	}
        	finally
        	{
        		 session.flush();
  			   session.close();
        	}
        	return list;
        	
        	
       }
}
