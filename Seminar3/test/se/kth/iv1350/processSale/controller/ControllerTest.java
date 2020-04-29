package se.kth.iv1350.processSale.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.ReceiptPrinter;
import se.kth.iv1350.processSale.integration.SystemCreator;
import se.kth.iv1350.processSale.model.dto.ItemResponseDTO;
import se.kth.iv1350.processSale.model.dto.ItemRetrievalDTO;

class ControllerTest {
	
	private SystemCreator systemCreator;
	private ReceiptPrinter receiptPrinter;
	private Controller instance;
	
	@BeforeEach
	void setUp() throws Exception {
		systemCreator=new SystemCreator();
		receiptPrinter=new ReceiptPrinter();
		instance=new Controller(systemCreator,receiptPrinter);
	}

	@AfterEach
	void tearDown() throws Exception {
		systemCreator=null;
		receiptPrinter=null;
		instance=null;
	}
	
	@Test
	void testAddItem() {
		instance.startSale();
		int itemIdentifier=45;
		int quantity=20;
		ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
		ItemResponseDTO itemInfo=instance.addItem(itemRetrieval);
		int expResult=45;
		int result=itemInfo.getItemToRegister().getItem().getItemDescription().getId();
		assertEquals(expResult,result,"Correct item could not be retrieved");
	}
	
	@Test
	void testAddItemBeforeStartingSale() {
		try {
			int itemIdentifier=45;
			int quantity=20;
			ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
			instance.addItem(itemRetrieval);
			fail("Successfully added an item without starting a new sale");
		}
		catch(Exception e) {
		}
	}
	
	@Test
	void testAddItemInvalidIdentifier() {
		try {
			instance.startSale();
			int itemIdentifier=Integer.MAX_VALUE;
			int quantity=20;
			ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
			instance.addItem(itemRetrieval);
			fail("Successfully added invalid item");
		}
		catch(Exception e) {
		}
		
	}
	
	@Test
	void testAddItemZeroIdentifier() {
		try {
			instance.startSale();
			int itemIdentifier=0;
			int quantity=20;
			ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
			instance.addItem(itemRetrieval);
			fail("Successfully added item with identifier 0");
		}
		catch(Exception e) {
		}
		
	}
	
	@Test
	void testAddItemNegativeIdentifier() {
		try {
			instance.startSale();
			int itemIdentifier=-45;
			int quantity=20;
			ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
			instance.addItem(itemRetrieval);
			fail("Successfully added item with negative identifier");
		}
		catch(Exception e) {
		}
		
	}
	
	@Test
	void testEndSaleNoItemsAdded() {
		instance.startSale();
		double expResult=0;
		double result=instance.endSale();
		assertEquals(expResult,result,"Wrong total price");
	}
	
	@Test
	void testEndSaleOneItemAdded() {
		instance.startSale();
		int itemIdentifier=45;
		int quantity=20;
		ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
		ItemResponseDTO itemInfo=instance.addItem(itemRetrieval);
		double expResult=itemInfo.getRunningTotal();
		double result=instance.endSale();
		assertEquals(expResult,result,"Wrong total price");
	}
	
	@Test
	void testEndSaleMultipleItemsAdded() {
		instance.startSale();
		int itemIdentifier=45;
		int quantity=20;
		ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
		ItemResponseDTO itemInfo=null;
		
		for(int i=0;i<3;i++) {
			itemInfo=instance.addItem(itemRetrieval);
		}
		double expResult=itemInfo.getRunningTotal();
		double result=instance.endSale();
		assertEquals(expResult,result,"Wrong total price");
	}

	@Test
	void testEnterPaymentNoItemNoPayment() {
		double amountPaid=0;
		instance.startSale();
		double totalPrice=instance.endSale();
		
		double expResult=amountPaid-totalPrice;
		double result=instance.enterPayment(amountPaid);

		assertEquals(expResult, result);
	}
	
	@Test
	void testEnterPaymentNoItemPayment() {
		double amountPaid=500;
		instance.startSale();
		double totalPrice=instance.endSale();
		
		double expResult=amountPaid-totalPrice;
		double result=instance.enterPayment(amountPaid);

		assertEquals(expResult, result);
	}
	
	@Test
	void testEnterPaymentOneItem() {
		double amountPaid=500;
		
		instance.startSale();
		int itemIdentifier=45;
		int quantity=20;
		ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
		instance.addItem(itemRetrieval);
		
		double totalPrice=instance.endSale();
		
		double expResult=amountPaid-totalPrice;
		double result=instance.enterPayment(amountPaid);

		assertEquals(expResult, result);
	}
	
	@Test
	void testEnterPaymentMultipleItems() {
		double amountPaid=500;
		
		instance.startSale();
		int itemIdentifier=45;
		int quantity=20;
		ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier, quantity);
		for(int i=0;i<3;i++) {
			instance.addItem(itemRetrieval);
		}
		double totalPrice=instance.endSale();
		
		double expResult=amountPaid-totalPrice;
		double result=instance.enterPayment(amountPaid);

		assertEquals(expResult, result);
	}

}
