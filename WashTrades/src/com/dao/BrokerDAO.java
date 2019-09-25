package com.dao;

import com.pojos.Broker;


public interface BrokerDAO {

	
	int addBroker(Broker broker);
    Broker getBrokerID(int brokerID);
    Broker deleteByBrokerID(int brokerID);
}
