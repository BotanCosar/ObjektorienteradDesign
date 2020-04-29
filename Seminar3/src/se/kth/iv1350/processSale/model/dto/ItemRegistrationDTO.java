package se.kth.iv1350.processSale.model.dto;

/**
 * Contains the item returned from the inventory system, as well as the initial user input.
 */
public class ItemRegistrationDTO {
	
	private final ItemRetrievalDTO itemRetrieval;
	private final ItemDTO item;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param itemRetrieval The item retrieval input entered by the user.
	 * @param item	The item to register.
	 */
	public ItemRegistrationDTO(ItemRetrievalDTO itemRetrieval,ItemDTO item) {
		this.itemRetrieval=itemRetrieval;
		this.item=item;
	}
	
	/**
	 * Get the value of itemRetrieval.
	 * 
	 * @return the value of itemRetrieval.
	 */
	public ItemRetrievalDTO getItemRetrieval() {
		return itemRetrieval;
	}
	
	/**
	 * Get the value of item.
	 * 
	 * @return the value of item.
	 */
	public ItemDTO getItem() {
		return item;
	}

}
