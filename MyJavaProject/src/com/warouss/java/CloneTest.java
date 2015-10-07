package com.warouss.java;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CloneTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CloneTest().execute();
	}
	
	private List<Produit> list1 = new ArrayList<Produit>();
	private Map<Integer, Produit> map1 = new HashMap<Integer, Produit>(); 
	
	private List<Produit> list3 = new ArrayList<Produit>();
	private Map<Integer, Produit> map3 = new HashMap<Integer, Produit>(); 
	
	private void execute() {
		List<Produit> list = new ArrayList<Produit>();
		Map<Integer, Produit> map = new HashMap<Integer, Produit>();
		for (int i=0; i<5; i++) {
			list.add(new Produit(i, "sku2_"+i, "name2_"+i, "desc2_"+i, 30.5));
			int j=i+5;
			map.put(i, new Produit(i, "sku2_"+j, "name2_"+j, "desc2_"+j, 30.5));
		}
		
		remplirList(list);
		System.out.println("Product.equals="+list1.get(1).equals(list.get(1)));
		System.out.println(list1.get(1).getSku()+" : "+list.get(1).getSku()+" : Parent.equals="+list1.get(1).getSku().equals(list.get(1).getSku()));
		System.out.println(list1.get(1).getDesc().getName()+" : "+list.get(1).getDesc().getName()+" : Desc.equals="+list1.get(1).getDesc().equals(list.get(1).getDesc()));
		System.out.println(list1.get(1).getItem().getPrix()+" : "+list.get(1).getItem().getPrix()+" : Item.equals="+list1.get(1).getItem().equals(list.get(1).getItem()));
		System.out.println("------------------------");
		
		remplirMap(map);
		System.out.println("Product.equals="+map1.get(1).equals(map.get(1)));
		System.out.println(map1.get(1).getSku()+" : "+map.get(1).getSku()+" : Parent.equals="+map1.get(1).getSku().equals(map.get(1).getSku()));
		System.out.println(map1.get(1).getDesc().getName()+" : "+map.get(1).getDesc().getName()+" : Desc.equals="+map1.get(1).getDesc().equals(map.get(1).getDesc()));
		System.out.println(map1.get(1).getItem().getPrix()+" : "+map.get(1).getItem().getPrix()+" : Item.equals="+map1.get(1).getItem().equals(map.get(1).getItem()));
		System.out.println("------------------------");
		
		remplirListWithClone(list);
		System.out.println("Product.equals="+list3.get(1).equals(list.get(1)));
		System.out.println(list3.get(1).getSku()+" : "+list.get(1).getSku()+" : Parent.equals="+list3.get(1).getSku().equals(list.get(1).getSku()));
		System.out.println(list3.get(1).getDesc().getName()+" : "+list.get(1).getDesc().getName()+" : Desc.equals="+list3.get(1).getDesc().equals(list.get(1).getDesc()));
		System.out.println(list3.get(1).getItem().getPrix()+" : "+list.get(1).getItem().getPrix()+" : Item.equals="+list3.get(1).getItem().equals(list.get(1).getItem()));
		System.out.println("------------------------");
		
		remplirMapWithClone(map);
		System.out.println("Product.equals="+map3.get(1).equals(map.get(1)));
		System.out.println(map3.get(1).getSku()+" : "+map.get(1).getSku()+" : Parent.equals="+map3.get(1).getSku().equals(map.get(1).getSku()));
		System.out.println(map3.get(1).getDesc().getName()+" : "+map.get(1).getDesc().getName()+" : Desc.equals="+map3.get(1).getDesc().equals(map.get(1).getDesc()));
		System.out.println(map3.get(1).getItem().getPrix()+" : "+map.get(1).getItem().getPrix()+" : Item.equals="+map3.get(1).getItem().equals(map.get(1).getItem()));
		System.out.println("------------------------");
	}
	
	private void remplirList(List<Produit> list2) {
		for (Produit p : list2) {
			p.setSku("sku");
			p.getDesc().setName("name");
			p.getItem().setPrix(50.00);
			list1.add(p);
		}
	}
	
	private void remplirMap(Map<Integer, Produit> map2) {
		for (Integer id : map2.keySet()) {
			Produit p = map2.get(id);
			p.setSku("sku");
			p.getDesc().setName("name");
			p.getItem().setPrix(60.00);
			map1.put(p.getId(), p);
		}
	}
	
	private void remplirListWithClone(List<Produit> list2) {
		for (Produit p2 : list2) {
			Produit p = (Produit) p2.clone();
			p.setSku("sku_clone");
			p.getDesc().setName("name_clone");
			p.getItem().setPrix(99.99);
			list3.add(p);
		}
	}
	
	private void remplirMapWithClone(Map<Integer, Produit> map2) {
		for (Integer id : map2.keySet()) {
			Produit p = (Produit) map2.get(id).clone();
			p.setSku("sku_clone");
			p.getDesc().setName("name_clone");
			p.getItem().setPrix(88.88);
			map3.put(p.getId(), p);
		}
	}
	
	class Produit extends Parent implements Cloneable {
		private int id=0;
		
		private Desc desc=null;
		private Item item=null;
		
		public Produit(int id, String sku, String name, String description, Double prix) {
			super(sku);
			this.id = id;
			this.desc = new Desc(name, description);
			this.item = new Item(id, prix);
		}
		
		public Object clone() {
			Produit p = null;
	    	try {
	      		p = (Produit) super.clone();
	      		p.setDesc((Desc) desc.clone());
	    	} catch(CloneNotSupportedException cnse) {
	      		cnse.printStackTrace(System.err);
		    }
		    return p;
	  	}
		
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}
		public Desc getDesc() {return desc;}
		public void setDesc(Desc desc) {this.desc = desc;}
		public Item getItem() {return item;}
		public void setItem(Item item) {this.item = item;}
		
	}
	
	class Parent {
		private String sku=null;

		public Parent(String sku) {
			this.sku = sku;
		}

		public String getSku() {return sku;}
		public void setSku(String sku) {this.sku = sku;}
	}
	
	class Desc implements Cloneable {
		private String name=null;
		private String description=null;
		
		public Desc(String name, String description) {
			this.name = name;
			this.description = description;
		}
		
		public Object clone() {
	    	Object o = null;
	    	try {
	      		o = super.clone();
	    	} catch(CloneNotSupportedException cnse) {
	      		cnse.printStackTrace(System.err);
		    }
		    return o;
	  	}
		
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
		public String getDescription() {return description;}
		public void setDescription(String description) {this.description = description;}
	}
	
	class Item {
		private int id;
		private Double prix = 0.0;
		
		public Item(int id, Double prix) {
			this.id = id;
			this.prix = prix;
		}
		
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}
		public Double getPrix() {return prix;}
		public void setPrix(Double prix) {this.prix = prix;}
	}

}
