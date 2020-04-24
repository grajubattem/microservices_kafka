package com.infosys.sos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.sos.exception.RecordNotFoundException;
import com.infosys.sos.model.SalesOrder;
import com.infosys.sos.producer.SalesOrderProducer;
import com.infosys.sos.repository.SalesOrderRepository;

@Service
public class SalesOrderService {
	
	@Autowired
	SalesOrderRepository repository;
	
	@Autowired
	SalesOrderProducer salesOrderProducer;

	public List<SalesOrder> getAllOrders() {
        List<SalesOrder> orders = repository.findAll();
         
        if(orders.size() > 0) {
            return orders;
        } else {
            return new ArrayList<SalesOrder>();
        }
	}

	public void deleteOrderById(Long salesOrderId) throws RecordNotFoundException {
		Optional<SalesOrder> order = repository.findById(salesOrderId);

		if (order.isPresent()) {
			repository.deleteById(salesOrderId);
		} else {
			throw new RecordNotFoundException("order doesn't exist for given id");
		}
	}

	public SalesOrder createOrUpdateOrder(SalesOrder order) throws RecordNotFoundException{
        Optional<SalesOrder> savedOrder = repository.findById(order.getSalesOrderId());
        SalesOrder obj;
        if(savedOrder.isPresent()) {
        	SalesOrder newOrder = savedOrder.get();
        	newOrder.setCustomerName(order.getCustomerName());;
        	newOrder.setNumberOfItems(order.getNumberOfItems());
        	newOrder = repository.save(newOrder);
        	obj = newOrder;
        } else {
        	obj = repository.save(order);
        }
        ObjectMapper Obj = new ObjectMapper(); 
        String json = null;
		try {
			json = Obj.writeValueAsString(order);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        salesOrderProducer.sendMessage(json);
        return obj;
	}

	public SalesOrder getSalesOrderById(Long salesOrderId)  throws RecordNotFoundException{
        Optional<SalesOrder> order = repository.findById(salesOrderId);
        if(order.isPresent()) {
            return order.get();
        } else {
            throw new RecordNotFoundException("order doesn't exist for given id");
        }
	}
	
}
