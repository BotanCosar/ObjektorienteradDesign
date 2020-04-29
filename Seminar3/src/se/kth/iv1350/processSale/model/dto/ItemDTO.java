package se.kth.iv1350.processSale.model.dto;

/**
 * Contains information on the identifier's validity, as well as the item description of one particular item.
 */
public class ItemDTO {
	
	private final boolean validIdentifier;
	private final ItemDescriptionDTO itemDescription;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param validIdentifier The validity of the identifier. <code>true</code> if identifier is valid.
	 * @param itemDescription The item description of the item.
	 */
	public ItemDTO(boolean validIdentifier, ItemDescriptionDTO itemDescription) {
		this.validIdentifier=validIdentifier;
		this.itemDescription=itemDescription;
	}
	
	/**
	 * Get the value of validIdentifier.
	 * 
	 * @return the value of validIdentifier.
	 */
	public boolean getValidIdentifier() {
		return validIdentifier;
	}
	
	/**
	 * Get the value of itemDescription.
	 * 
	 * @return the value of itemDescription.
	 */
	public ItemDescriptionDTO getItemDescription() {
		return itemDescription;
	}

}
