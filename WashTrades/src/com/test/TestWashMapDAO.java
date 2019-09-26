package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dao.WashDAO;
import com.dao.WashMapDAO;
import com.dao.WashMapDAOImpl;

class TestWashMapDAO {

	@Test
	void testAddWashTradeIDs() {
		
		WashMapDAO washMapDAO = new WashMapDAOImpl();
		int r=washMapDAO.addWashTradeIDs(3,Arrays.asList(87,90,987,876));
		assertEquals(4,r );
	}
	
	@Test
	void testFindTradeIDsByWashID() {
		
		WashMapDAO washMapDAO = new WashMapDAOImpl();
        List<Integer> r = washMapDAO.findTradeIDsByWashID(2);
        System.out.println(r);
        assertEquals(3, r.size());
		
		
	}

}
