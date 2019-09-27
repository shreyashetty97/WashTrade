package com.dao;

import java.util.List;


import com.pojos.Broker;


public interface BrokerDAO {   

	
	int addBroker(Broker broker);
    Broker findBrokerByID(int brokerID);
    Broker findBrokerByName(String name);
    Broker deleteByBrokerID(int brokerID);
    boolean deleteAllBrokers();
    List<Broker> findAllBrokers();
    
}
