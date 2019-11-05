package javademo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential; 

import org.json.*;

public class connectivity { 
   
   public static void main( String args[] ) throws JSONException {   
	   
	  System.out.println("Enter User name,Database name ,Password");
	  Scanner in= new Scanner(System.in);
	  String username=in.next();
	  String Database=in.next();
	  char[] pass=in.next().toCharArray();
	  java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
      MongoClient mongo = new MongoClient( "10.10.15.202"); 
      MongoCredential credential; 
      credential = MongoCredential.createCredential(username,Database,pass); 
      System.out.println("Connected to the database successfully");  
      System.out.println("Credentials ::"+ credential);   
    
      MongoDatabase database = mongo.getDatabase(Database);  
      MongoCollection<Document> collection= database.getCollection("sampleCollection1");
      
	  System.out.println("Enter 1 for simple Json Objects and 2 for Array of Objects");
	  int x=in.nextInt();
	  switch(x)
	  {
	    case 1:
	    	{
	    		JSONObject json=new JSONObject();
	    	      String option;
	    	      try
	    	      {
	    	    	  do{
	    	    		  System.out.println("Enter the key followed by the Value");
	    	    		  String key=in.next();
	    	    	      String value=in.next();
	    	    	      json.put(key,value);
	    	    	      System.out.println("Enter y to insert more");
	    	    	      option=in.next();
	    	    	  	}while(option.equals("y"));
	    	      }
	    	      catch(JSONException e)
	    	      {
	    	          System.out.println("Error occured");

	    	      }
	    	      
	    	      Document doc = Document.parse( json.toString() );
	    	      collection.insertOne(doc); 
	    	      FindIterable<Document> iterDoc = collection.find(); 
	    	      Iterator it = iterDoc.iterator(); 
	    	      while (it.hasNext()) 
	    	      {  
	    	         System.out.println(it.next());  
	    	     
	    	      }

	    		break;
	    	}
	    case 2:
	    	{
	    		JSONObject json=new JSONObject();
	    		json=getJsonResponse();
	    		Document doc = Document.parse( json.toString());
	    		collection.insertOne(doc);
	    		FindIterable<Document> iterDoc = collection.find(); 
	    	      Iterator it = iterDoc.iterator(); 
	    	      while (it.hasNext()) 
	    	      {  
	    	         System.out.println(it.next());  
	    	     
	    	      }
	    		
	    		/*System.out.println("Enter the number of docuuments you want to insert");
	    		int no=in.nextInt();
	    		List<Document> documents = new ArrayList<Document>();
    			JSONObject json=new JSONObject();

	    		for(int i=0;i<x;i++)
	    		{

		    	      String option;
		    	      try
		    	      {
		    	    	  do{
		    	    		  System.out.println("Enter the key followed by the Value");
		    	    		  String key=in.next();
		    	    	      String value=in.next();
		    	    	      json.put(key,value);
		    	    	      System.out.println("Enter y to insert more");
		    	    	      option=in.next();
		    	    	  	}while(option.equals("y"));
		    	      }
		    	      catch(JSONException e)
		    	      {
		    	          System.out.println("Error occured");

		    	      }
		    	      Document doc = Document.parse( json.toString() );
		    	      documents.add(doc);
	    		}
	    		collection.insertMany(documents); */
	    		break;
	    	}
	    default:
	    	{
	    		System.out.println("Enter valid option");
	    	}
	  }

         }
   static JSONObject  getPerson(String firstName, String lastName) throws JSONException{
	   JSONObject person = new JSONObject();
	   person .put("firstName", firstName);
	   person .put("lastName", lastName);
	   return person ;
	} 

	public static JSONObject getJsonResponse() throws JSONException{
		  

	    JSONArray alphabets = new JSONArray();
	    alphabets.put(getPerson("suraj","chavan"));
	    alphabets.put(getPerson("abc","def"));
	    alphabets.put(getPerson("ghi","jkl"));

	    JSONArray numbers = new JSONArray();
	    numbers.put(getPerson("mno","pqr"));
	    numbers.put(getPerson("stu","vwx"));
	    numbers.put(getPerson("yza","bcd"));
		  

	    JSONObject response= new JSONObject();
	    response.put("alphabets", alphabets );
	    response.put("numbers", numbers );
	    return response;
	    
	  }
}
