package com.ems.dao;

import com.ems.domain.Address;

public interface AddressDao {
	public int create(Address address);
	public Address getAddressById(long addressId);
}
