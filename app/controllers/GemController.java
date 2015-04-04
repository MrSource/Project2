package controllers;


import java.util.ArrayList;

import models.Gem;
import models.GemList;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GemController extends Controller{
	@BodyParser.Of(BodyParser.Json.class)
	public static Result storeGem(){
		ObjectMapper mapper = new ObjectMapper();
		 try {
			 System.err.println("POST Data");
			 JsonNode json = request().body().asJson();
			 System.err.println("json payload: " + json);
			 Gem newGem = mapper.readValue(json.toString(), Gem.class);
			 GemList<Gem> gemList = GemList.getInstance(); 
			 gemList.add(newGem);
			 ObjectNode result = Json.newObject();
			 result.set("Gem", Json.toJson(newGem));
			 return created(result);
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 return badRequest("Missing information");
		 }
		
	}
	
	public static Result getGem(Integer id){
		// DEBUG 
		System.err.println("GET on id: "+ id);
		
		ObjectNode result = Json.newObject();
		GemList<Gem> theList = GemList.getInstance();
		Gem P = theList.get(id);
		if (P == null){
			return notFound(); // 404
		}
		else {
			result.set("Gem", Json.toJson(P));
			return ok(result);
	
		}
	}
	
	//Add more controllers i.e getAll Gems to populate page
	public static Result getGems(){
		System.err.println("Get all gems");
		ArrayList<Gem> newList = new ArrayList<Gem>();
		GemList<Gem> theList = GemList.getInstance();
		for(int i = 0; i<theList.size();i++){
			newList.add(theList.get(i));
		}
		JsonNode result = Json.toJson(newList);
		return ok(result);
	}
}
