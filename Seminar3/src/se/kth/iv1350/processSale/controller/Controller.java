package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.AccountingSystem;
import se.kth.iv1350.processSale.integration.CashRegister;
import se.kth.iv1350.processSale.integration.InventorySystem;
import se.kth.iv1350.processSale.integration.ReceiptPrinter;
import se.kth.iv1350.processSale.integration.SystemCreator;
import se.kth.iv1350.processSale.model.Payment;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.dto.ItemDTO;
import se.kth.iv1350.processSale.model.dto.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.dto.ItemResponseDTO;
import se.kth.iv1350.processSale.model.dto.ItemRetrievalDTO;
import se.kth.iv1350.processSale.model.dto.ReceiptDTO;
import se.kth.iv1350.processSale.model.dto.StoreDTO;

/**
 * Handles all communication between view, model, and integration layer classes.
 */
public class Controller {
	
	private ReceiptPrinter receiptPrinter;
	private InventorySystem inventorySystem;
	private AccountingSystem accountingSystem;
	private CashRegister cashRegister;
	private StoreDTO store;
	private Sale sale;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param systemCreator responsible for getting classes that handle calls to external systems
	 * @param receiptPrinter responsible for handling calls to the external printer
	 */
	public Controller(SystemCreator systemCreator,ReceiptPrinter receiptPrinter) {
		this.receiptPrinter=receiptPrinter;
		this.inventorySystem=systemCreator.getInventorySystem();
		this.accountingSystem=systemCreator.getAccountingSystem();
		this.cashRegister=new CashRegister();
		this.store=new StoreDTO();
	}
	
	/**
	 * Creates a new sale with related initialization steps. 
	 */
	public void startSale() {
		this.sale=new Sale();
	}
	
	/**
	 * Uses an {@link ItemRetrievalDTO} to find an item from the inventory system, add a specific quantity of it to the current sale, 
	 * and return complete information of the retrieval.
	 *  
	 * @param itemRetrieval An object containing the item's identifier as well as the quantity to add to the sale.
	 * @return information of this item and its retrieval. Includes price, VAT rate, description, as well as running total of the sale.
	 */
	public ItemResponseDTO addItem(ItemRetrievalDTO itemRetrieval) {
		ItemDTO item=inventorySystem.getItem(itemRetrieval);
		ItemRegistrationDTO itemToRegister=new ItemRegistrationDTO(itemRetrieval,item);
		ItemResponseDTO itemInfo=sale.addItem(itemToRegister);
		return itemInfo;
	}
	
	
	/**
	 * Ends the current sale and returns the total price.
	 * @return the total price of the sale.
	 */
	public double endSale() {
		double totalPrice=sale.getRunningTotal();
		return totalPrice;
	}
	
	
	/**
	 * Enters an amount of money to pay for the sale. The payment is added to the cash register. A log of the transaction is sent to
	 * the inventory and accounting systems, and a receipt is printed. The excess is returned as change.
	 * @param amountPaid The amount of money paid for the sale.
	 * @return The amount of change to receive back.
	 */
	public double enterPayment(double amountPaid) {
		Payment payment=new Payment(amountPaid);
		double change=sale.pay(payment);
		cashRegister.addPayment(payment);
		ReceiptDTO receipt=payment.createReceipt(store);
		inventorySystem.updateInventory(receipt);
		accountingSystem.updateAccounting(receipt);
		receiptPrinter.print(receipt);
		return change;
	}

}
