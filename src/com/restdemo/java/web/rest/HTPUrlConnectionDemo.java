package com.restdemo.java.web.rest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTPUrlConnectionDemo {
	
	
	public static void getResponse() throws IOException{
		
		String url = "http://localhost:8080/SampleProject/restservices/productcatalog/addCatalog";
		
		String payload = "{\n\t\"id\":100,\n\t\"name\":\"Book\",\n\t\"category\":\"Dummy\",\n\t\"unitPrice\":\"200\"\n}";
		
		URL endPointURL = null;
		try {
			endPointURL = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpURLConnection con = (HttpURLConnection) endPointURL.openConnection();
		
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("_soa_session_id", "");
		
		
		con.setRequestMethod("POST");
		
		OutputStreamWriter oStream = new OutputStreamWriter(con.getOutputStream());
		oStream.write(payload);
		oStream.close();
		
		String Response = null;
		StringBuffer sB = new StringBuffer();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		
		while ((Response=br.readLine()) != null){
			
			sB.append(Response);
			
		}
		
		
		/*
		 * String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
		 */
		System.out.println(con.getResponseCode());
		
		System.out.println(""+sB.toString());
	}
	
	public static void main(String args[])
	{
		
		try {
			getResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
