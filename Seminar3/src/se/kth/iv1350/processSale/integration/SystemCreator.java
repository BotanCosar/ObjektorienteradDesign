package se.kth.iv1350.processSale.integration;

/**
 * This class is responsible for instantiating all external systems.
 */
public class SystemCreator {
	
	private InventorySystem inventorySystem;
	private AccountingSystem accountingSystem;
	
    /**
     * Creates a new instance.
     */
	public SystemCreator() {
		this.inventorySystem=new InventorySystem();
		this.accountingSystem=new AccountingSystem();
	}
	
	/**
	 * Get the value of inventorySystem.
	 * 
	 * @return the value of inventorySystem.
	 */
	public InventorySystem getInventorySystem() {
		return inventorySystem;
	}
	
	/**
	 * Get the value of accountingSystem.
	 * 
	 * @return the value of accountingSystem.
	 */
	public AccountingSystem getAccountingSystem() {
		return accountingSystem;
	}

}
