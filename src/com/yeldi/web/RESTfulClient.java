package com.yeldi.web;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class RESTfulClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client=Client.create();
		WebResource resource=client.resource("http://localhost:8085/nyfirstjersey/rest/customers/100");
		ClientResponse response=resource.accept("application/json").get(ClientResponse.class);
        if(response.getStatus()==200)
        {
        	String output=response.getEntity(String.class);
        	System.out.println(output);
        }
	}

}
