package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.dto.ItemResponseDTO;
import se.kth.iv1350.processSale.model.dto.ItemRetrievalDTO;

/**
 * This program has no view, instead, this class is a placeholder for the entire view.
 *
 */
public class View {
	
	private Controller controller;

	/**
	 * Creates a new instance.
	 *
	 * @param controller The controller that is used for all operations.
	 */
	public View(Controller controller) {
		this.controller=controller;
	}
	
	private void sampleAddItem(int itemIdentifier,int quantity) {
		ItemRetrievalDTO itemRetrieval=new ItemRetrievalDTO(itemIdentifier,quantity);
		ItemResponseDTO itemInfo=controller.addItem(itemRetrieval);
		sampleItemInfoPrintout(itemInfo);
	}
	
	private void sampleItemInfoPrintout(ItemResponseDTO itemInfo) {
		System.out.println("--Item entered--"
				+"\nid: "+itemInfo.getItemToRegister().getItem().getItemDescription().getId()
				+"\nname: "+itemInfo.getItemToRegister().getItem().getItemDescription().getName()
				+"\nprice: "+itemInfo.getItemToRegister().getItem().getItemDescription().getPrice()
				+"\nVAT rate: "+itemInfo.getItemToRegister().getItem().getItemDescription().getVatRate()
				+"\nprice of all "+itemInfo.getItemToRegister().getItem().getItemDescription().getName()
				+": "+itemInfo.getPriceXQuantity()
				+"\nrunning total: "+String.format("%.2f", itemInfo.getRunningTotal())+"\n");
	}
	
	/**
	 * Simulates a user input that generates calls to all system operations. Uses hard coded data as inputs for the
	 * operations. Shows handling of already entered items as well as new items.
	 * Printouts of each item retrieval can be disabled by commenting out the call to <code>sampleItemInfoPrintout</code> 
	 * in the <code>sampleAddItem</code> method. Printout of the end of sale information can be disabled by commenting
	 * out the calls to <code>System.out</code>.
	 */
	public void executeExampleSale() {
		controller.startSale();
		System.out.println("--A new sale has started--\n");
		sampleAddItem(45, 2);
		sampleAddItem(45, 4);
		sampleAddItem(112, 3);
		sampleAddItem(245, 10);
		sampleAddItem(112, 1);
		
		double totalPrice=controller.endSale();
		System.out.println("---Sale ended---\nTotal price: "+String.format("%.2f", totalPrice)+"\n");
		double change=controller.enterPayment(500);
		System.out.println("---Payment entered---\nReceive change: "+String.format("%.2f", change)+"\n");
	}

}
