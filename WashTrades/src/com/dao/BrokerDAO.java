package com.dao;

import com.pojos.Broker;


public interface BrokerDAO {   

	
	int addBroker(Broker broker);
    Broker findBrokerByID(int brokerID);
    Broker findBrokerByName(String name);
    Broker deleteByBrokerID(int brokerID);
}
