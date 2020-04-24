package com.infosys.sos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.sos.exception.RecordNotFoundException;
import com.infosys.sos.model.SalesOrder;
import com.infosys.sos.service.SalesOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/so/v1/")
@Api(value="Sales Order Management System")
public class SalesOrderController {

	@Autowired
	SalesOrderService service;

	@GetMapping("salesorders/")
	@ApiOperation(value = "View all Sales Orders", response = List.class)
	public ResponseEntity<List<SalesOrder>> getAllOrders() {
		List<SalesOrder> list = service.getAllOrders();
		return new ResponseEntity<List<SalesOrder>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("salesorders/{salesOrderId}")
	@ApiOperation(value = "View a Sales Order", response = List.class)
	public ResponseEntity<SalesOrder> getSalesOrderById(@PathVariable("salesOrderId") Long salesOrderId) throws RecordNotFoundException {
		SalesOrder entity = service.getSalesOrderById(salesOrderId);

		return new ResponseEntity<SalesOrder>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("salesorders/")
	@ApiOperation(value = "Add or Update Sales Order", response=SalesOrder.class)
	public ResponseEntity<SalesOrder> createOrUpdateOrder(SalesOrder order) throws RecordNotFoundException {
		SalesOrder updated = service.createOrUpdateOrder(order);
		return new ResponseEntity<SalesOrder>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("salesorders/{salesOrderId}")
	@ApiOperation(value = "Delete a Sales Order")
	public HttpStatus deleteOrderById(@PathVariable("salesOrderId") Long id) throws RecordNotFoundException {
		service.deleteOrderById(id);
		return HttpStatus.OK;
	}

}
