package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Payment;

/**
 * Represents a cash register. There shall be one instance of this class for
 * each register.
 */
public class CashRegister {
	
	private double balance;
	
	/**
	 * Increases the amount of money stored in the register by the amount entered in the payment.
	 * 
	 * @param payment The amount to add to the balance.
	 */
	public void addPayment(Payment payment) {
		balance+=payment.getAmountPaid()-payment.getChange();
	}
	
	/**
	 * Get the value of balance.
	 * 
	 * @return the value of balance.
	 */
	public double getBalance() {
		return balance;
	}

}
