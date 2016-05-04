package entities;
/**
 * 
 * This class contains all the status of orders
 *
 */
public enum OrderStatus {
	CREATED, PAYMENT_PENDING, NOT_PAID, PAID, CONFIRM_PENDING, POSTPONED, MONTHLY_ACTIVE, MONTHLY_LATE, PAYMENT_PENDING_AUTO;
}
