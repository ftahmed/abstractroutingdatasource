package me.murrer.configurations;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * CustomRoutingDatatasource
 */
public class CustomRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		Object o = ShiftContextHolder.getCustomer();
		return o;
	}


}
