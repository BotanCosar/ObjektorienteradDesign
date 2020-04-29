package se.kth.iv1350.processSale.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.model.dto.ItemDTO;
import se.kth.iv1350.processSale.model.dto.ItemRetrievalDTO;

class InventorySystemTest {
	
	private ItemRetrievalDTO itemRetrieval;
	private InventorySystem instance;
	
	@BeforeEach
	void setUp() throws Exception {
		instance=new InventorySystem();
	}

	@AfterEach
	void tearDown() throws Exception {
		instance=null;
	}



	@Test
	void testGetItem() {
		int itemIdentifier=245;
		String name="Mellanmjölk";
		double vatRate=6;
		double price=15;
		
		itemRetrieval=new ItemRetrievalDTO(itemIdentifier, 10);
		ItemDTO result=instance.getItem(itemRetrieval);
		assertEquals(itemIdentifier, result.getItemDescription().getId(),"Wrong id");
		assertEquals(name, result.getItemDescription().getName(),"Wrong name");
		assertEquals(vatRate, result.getItemDescription().getVatRate(),"Wrong vatRate");
		assertEquals(price, result.getItemDescription().getPrice(),"Wrong price");
	}

}
