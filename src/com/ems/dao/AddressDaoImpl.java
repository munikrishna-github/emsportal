package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ems.domain.Address;

//Remind me as how to handle transactions.
public class AddressDaoImpl implements AddressDao {
	public int create(Address address){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			//First get connection object ...
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not oroceed.");
				return 0;
			}
			
			String sql 	= 	"INSERT INTO ADDRESS(STREET1, STREET12, CITY, STATE, ZIP, COUNTRY, ACTIVE_FL, CREATE_DATETIME, UPDATE_DATETIME) "
						+	"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			//pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, address.getStreet1());
			pstmt.setString(2, address.getStreet2());
			pstmt.setString(3, address.getCity());
			pstmt.setString(4, address.getState());
			pstmt.setInt(5, address.getZip());
			pstmt.setString(6, address.getCountry());
			pstmt.setString(7, "Y");
			pstmt.setDate(8, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setDate(9, new java.sql.Date(System.currentTimeMillis()));
			int addressId = 0;
			int count = pstmt.executeUpdate();
			if(count > 0){
				//Getting auto generated keys.
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs != null){
					if(rs.next()){
						addressId = rs.getInt(1);
					}
				}
			}
			System.out.println("Generated Address ID = "+addressId);
			
			return addressId;
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		return 0;
	}
	
	public Address getAddressById(long addressId) {
		System.out.println("Loading address for addressId = "+addressId);

		String sql = "Select address_id, STREET1, STREET12, CITY, STATE, ZIP, COUNTRY, ACTIVE_FL from ADDRESS where address_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//First get connection object ...
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not oroceed.");
				return null;
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, addressId);
			rs = pstmt.executeQuery();
			Address address = null;
			while (rs.next()) {
				address = new Address();
				address.setAddressId(rs.getLong("address_id"));
				address.setStreet1(rs.getString("STREET1"));
				address.setStreet2(rs.getString("STREET12"));
				address.setCity(rs.getString("CITY"));
				address.setCountry(rs.getString("COUNTRY"));
				address.setState(rs.getString("STATE"));
				address.setZip(rs.getInt("ZIP"));
			}
			return address;
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(rs);
			ConnectionManager.close(con);
		}
		//Vicky to implement
		return null;
	}
	
	public boolean update(Address address) {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			String sql = "UPDATE ADDRESS SET STREET1 = ?, STREET12 = ?, CITY = ?, STATE = ?, ZIP = ?, COUNTRY = ?, UPDATE_DATETIME = ? where address_id = ?";
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not oroceed.");
				return false;
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, address.getStreet1());
			pstmt.setString(2, address.getStreet2());
			pstmt.setString(3, address.getCity());
			pstmt.setString(4, address.getState());
			pstmt.setInt(5, address.getZip());
			pstmt.setString(6, address.getCountry());
			pstmt.setDate(7, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setLong(8, address.getAddressId());
			int count =  pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("Employee updation successfulll...");
				return true;
			} else {
				System.out.println("Employee updation failed ...");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		return false;
	}
	
	public int delete(long addressId) {
		System.out.println(">>>> DELETE ADDRESS for ID ="+addressId);
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			String sql = "DELETE FROM ADDRESS where address_id = ?";
			con = ConnectionManager.getConnection();
			if(con == null){
				System.out.println("Connection is not established - do not proceed.");
				return 0;
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, addressId);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
			ConnectionManager.close(con);
		}
		
		return 0;
	}
	
	public static void main(String args[]){
		try {
			Address address = new Address();
			address.setStreet1("39152 Guardino dr");
			address.setStreet2("Apt 208");
			address.setCity("Fremont");
			address.setState("CA");
			address.setZip(94538);
			address.setCountry("USA");
			AddressDao addressDao = new AddressDaoImpl();
			addressDao.create(address);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}