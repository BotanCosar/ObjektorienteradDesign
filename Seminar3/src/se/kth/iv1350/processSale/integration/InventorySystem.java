package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.model.dto.ItemDTO;
import se.kth.iv1350.processSale.model.dto.ItemDescriptionDTO;
import se.kth.iv1350.processSale.model.dto.ItemRetrievalDTO;
import se.kth.iv1350.processSale.model.dto.ReceiptDTO;

/**
 *	Handles all class calls to the external inventory system. Since there is no external inventory system, dummy code has been added
 *	instead.
 */
public class InventorySystem {
	
	private List<ItemData> items=new ArrayList<>();
	
	/**
	 * Creates new instance. Exists only to demonstrate how calls to the external inventory system might work. Otherwise this
	 * constructor can be omitted.
	 */
	InventorySystem(){
		addItems();
	}
	
	/**
	 * Finds item from the inventory system and returns its name, VAT rate, id, and price.
	 * 
	 * @param itemRetrievalDTO Used to match the identifier entered by the user with the id of an item in the inventory system.
	 * @return	name, VAT rate, id, and price of the item.
	 */
	public ItemDTO getItem(ItemRetrievalDTO itemRetrievalDTO) {
				
		// code that checks if identifier is valid goes here.
		
		boolean validIdentifier=true;
		
		ItemData foundItem=findItemById(itemRetrievalDTO);
		ItemDescriptionDTO itemDescription=new ItemDescriptionDTO(foundItem.vatRate,foundItem.name,foundItem.id,foundItem.price);
		ItemDTO item=new ItemDTO(validIdentifier,itemDescription);
		return item;			
	}
	
	/**
	 * Sends sale information to the inventory system.
	 * @param receipt Holds all necessary data related to the sale.
	 */
	public void updateInventory(ReceiptDTO receipt) {
		// code that updates the external inventory system goes here.
	}
	
	private void addItems() {
		items.add(new ItemData(6,"Mellanmjölk",245,15));
		items.add(new ItemData(6,"Ost",112,20));
		items.add(new ItemData(12,"Makaroner",45,25));
		items.add(new ItemData(25,"Köttbullar",123,35));
		items.add(new ItemData(25,"Ketchup",432,15));
	}
	
	private ItemData findItemById(ItemRetrievalDTO itemRetrievalDTO) {
		for (ItemData item : items) {
            if (item.id==itemRetrievalDTO.getItemIdentifier()) {
                return item;
            }
        }
        return null;
	}
	
	private static class ItemData{
		private double vatRate;
		private String name;
		private int id;
		private double price;
		
		public ItemData(double vatRate,String name, int id, double price) {
			this.vatRate=vatRate;
			this.name=name;
			this.id=id;
			this.price=price;
		}
	}

}
