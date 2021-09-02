import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Item2{
	
	private HashMap<String, String> Item2InfoHM;
	private String Item2Name = "";
	
	private ArrayList<Item2> children = new ArrayList<Item2>();
	
	public String getItem2Name(){ return Item2Name; }
	
	public void setItem2Name(String Item2Name){ this.Item2Name = Item2Name; }
	
	public Item2(String Item2Name){
		
		super();
		setItem2Name(Item2Name);
		Item2InfoHM = new HashMap<String, String>(20);
		
	}
	
	public void add(Item2 childNode){
		
		children.add(childNode);
		
	}
	
	public void addItem2Information(String infoName, String info){
		
		Item2InfoHM.put(infoName, info);
		
	}
	
	public String getItem2Information(String infoName){
		
		return Item2InfoHM.get(infoName);
		
	}
	
	public String toString() {
		
		StringBuffer Item2Info = new StringBuffer();
		
		addItem2InfoAndChildren(Item2Info);
		
		return Item2Info.toString();
		
	}
	
	private void addItem2InfoAndChildren(StringBuffer Item2Info){
		
		addItem2Information(Item2Info);
		
		addChildrenInformation(Item2Info);
		
	}
	
	private void addItem2Information(StringBuffer Item2Info){
		
		Item2Info.append("\n" + Item2Name + " ");
		
		// If Item2 info is available get it
		
		if (!Item2InfoHM.isEmpty()){
			
			Item2Info.append(displayProductInfo());
			
		}
		
	}
	
	private void addChildrenInformation(StringBuffer Item2Info){
		
		Iterator<Item2> it = children.iterator();
		
		// Attach all children for this Item2
		
		while (it.hasNext()) {
			
			Item2 node = (Item2)it.next();
			Item2Info.append(node.toString());
			
		}
		
	}
	
	public String displayProductInfo(){
		
		String productInfo = "";
		
		for(Map.Entry<String, String> entry : Item2InfoHM.entrySet()){
			
			productInfo += entry.getKey() + ": " + entry.getValue() + " ";
			
		}
		
		return productInfo;
		
	}
	
	public static void main(String[] args){
		
		Item2Builder products = new Item2Builder("Products");
		
		products.addChild("Produce");
		products.addChild("Orange");
		products.addItem2Information("Price", "$1.00");
		products.addItem2Information("Stock", "100");
		
		products.addSibling("Apple");
		products.addSibling("Grape");
		
		products.editThisItem2("Products");
		products.addChild("Cereal");
		products.addChild("Special K");
		products.addItem2Information("Price", "$4.00");
		products.addSibling("Raisin Bran");
		products.addItem2Information("Price", "$4.00");
		products.addSibling("Fiber One");
		
		products.addItem2Information("Price", "$4.00");
		
		products.displayAllItem2s();
		
		System.out.println("\n" + products.getItem2ByName("Cereal"));
		
		
		
		
		
	}
		
}

class Item2Builder {
	
	ArrayList<Item2> Item2s = new ArrayList<Item2>();
	
	private Item2 root;
	private Item2 current;
	private Item2 parent;
	
	public Item2Builder(String rootName){
		
		root = new Item2(rootName);
		
		addItem2ToArrayList(root);
		
		current = root;
		parent = root;
		
		root.addItem2Information("Parent", parent.getItem2Name());
		
	}
	
	public void addItem2Information(String name, String value){
		
		current.addItem2Information(name, value);
		
	}
	
	public void addChild(String child){
		
		Item2 childNode = new Item2(child);
		
		addItem2ToArrayList(childNode);
		
		current.add(childNode);
		parent = current;
		current = childNode;
		
		childNode.addItem2Information("Parent", parent.getItem2Name());
		
	}
	
	public void addSibling(String sibling){
		
		Item2 siblingNode = new Item2(sibling);
		
		addItem2ToArrayList(siblingNode);
		
		parent.add(siblingNode);
		current = siblingNode;
		
		siblingNode.addItem2Information("Parent", parent.getItem2Name());
		
	}
	
	public void addItem2ToArrayList(Item2 newItem2){
		
		Item2s.add(newItem2);
		
	}
	
	public String toString(){
		
		return root.toString();
		
	}
	
	public void displayAllItem2s(){
		
		for(Item2 Item2 : Item2s){
			
			System.out.println(Item2.getItem2Name() + ": " + 
			Item2.displayProductInfo());
			
		}
		
	}
	
	public void editThisItem2(String Item2Name){
		
		for(Item2 Item2 : Item2s){
			
			if(Item2.getItem2Name().equals(Item2Name)){
				
				current = Item2;
				
				setItem2sParent(current.getItem2Information("Parent"));
				
			}
			
		}
		
	}
	
	public void setItem2sParent(String parentItem2){
		
		for(Item2 Item2 : Item2s){
			
			if(Item2.getItem2Name().equals(parentItem2)){
				
				parent = Item2;
				
			}
			
		}
		
	}
	
	public Item2 getItem2ByName(String Item2ToGet){
		
		Item2 Item2ToReturn = null;
		
		for(Item2 Item2 : Item2s){
			
			if(Item2.getItem2Name().equals(Item2ToGet)){
				
				Item2ToReturn = Item2;
				
			}
			
		}
		
		return Item2ToReturn;
		
	}
	
}