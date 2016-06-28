package me.murrer.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.murrer.configurations.ShiftContextHolder;
import me.murrer.models.Shift;
import me.murrer.repositories.ShiftRepository;


/**
 * Service for handling {@link Shift} related actions.
 */
@Service
public class ShiftService {

	@Autowired
	private ShiftRepository shiftRepository;

	public void storeCustomer(Shift shift) {
		ShiftContextHolder.setCustomer(shift.getCustomer());
		shiftRepository.save(shift);
		ShiftContextHolder.clearCustomer();
	}
}
