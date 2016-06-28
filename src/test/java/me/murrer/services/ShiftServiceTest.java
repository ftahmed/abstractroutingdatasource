package me.murrer.services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import me.murrer.DistributedDataSourceApplication;
import me.murrer.models.Shift;

import static org.junit.Assert.assertNotNull;


/**
 * Tests for {@link ShiftService}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DistributedDataSourceApplication.class)
@WebAppConfiguration
public class ShiftServiceTest {

	@Autowired
	ShiftService shiftService;

	@Test
	public void testAutowired() {
		assertNotNull(shiftService);
	}

	@Test
	public void doSaveShifts() {
		Shift shiftCustomerA = new Shift("CUSTOMER-A");
		Shift shiftCustomerB = new Shift("CUSTOMER-B");
		Shift shiftCustomerC = new Shift("CUSTOMER-C");
		Shift shiftCustomerD = new Shift("CUSTOMER-D");

		shiftService.storeCustomer(shiftCustomerA);
		shiftService.storeCustomer(shiftCustomerB);
		shiftService.storeCustomer(shiftCustomerC);
		shiftService.storeCustomer(shiftCustomerD);
	}
}
