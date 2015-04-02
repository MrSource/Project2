package models;

import java.util.ArrayList;

public class GemList {

	private ArrayList<Gem> GemList;
	//private SortedCircularDoublyLinkedList<Person> personList1;

	int counter = 1;
	
	private GemList(){
		this.GemList = new ArrayList<Gem>(10);
	}
	
	public Gem addGem(Gem obj){
		long id = this.counter++;
		obj.setId(id);
		this.GemList.add(obj);
		return obj;
	}
	
	public Gem getGemById(long id){
		for (Gem g : this.GemList){
			if (g.getId() == id){
				return g;
			}
		}
		return null;
	}
	
	public Gem[] getAllGems(){
		Gem result[] = new Gem[this.GemList.size()];
		for (int i=0; i < this.GemList.size(); ++i){
			result[i] = this.GemList.get(i);
		}
		return result;
	}
	
 
	
	public boolean deleteGem(long id){
		int target = -1;
		
		for (int i=0; i< this.GemList.size(); ++i){
			if (this.GemList.get(i).getId() == id){
				target = i;
				break;
			}
		}
		if (target == -1){
			return false;
		}
		else {
			this.GemList.remove(target);
			return true;
		}
	}
	
	public Gem updateGem(Gem obj){
		int target = -1;
		
		for (int i=0; i< this.GemList.size(); ++i){
			if (this.GemList.get(i).getId() == obj.getId()){
				target = i;
				break;
			}
		}
		if (target == -1){
			return null;
		}
		else {
			Gem G = this.GemList.get(target);
			G.setColor(obj.getColor());
			G.setDescriptioin(obj.getDescriptioin());
			G.setFaces(obj.getFaces());
			G.setPrice(obj.getPrice());
			G.setRarity(obj.getRarity());
			G.setShine(obj.getShine());
			G.setSold(obj.isSold());
			return G;
		}
	}
	
	private static GemList singleton= new GemList();
	
	public static GemList getInstance(){
		return singleton;
	}
}
