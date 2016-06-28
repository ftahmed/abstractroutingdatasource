package me.murrer.configurations;


import org.springframework.util.Assert;


/**
 * ShiftContextHolder that holds
 */
public class ShiftContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomer(String customer) {
		Assert.notNull(customer, "customer cannot be null");
		contextHolder.set(customer);
	}

	public static String getCustomer() {
		return (String) contextHolder.get();
	}

	public static void clearCustomer() {
		contextHolder.remove();
	}

}
