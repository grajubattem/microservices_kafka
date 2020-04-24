package com.infosys.sos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_SALESORDERS")
public class SalesOrder {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sales_order_id")
	private Long salesOrderId;
	
	@Column(name="customer_name")
	private String customerName;
	 
	@Column(name="no_of_items")
	private int numberOfItems;
	
	public Long getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(Long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
	@Override
    public String toString() {
        return "SalesOrder [salesOrderId=" + salesOrderId + ", customerName=" + customerName + 
                ", numberOfItems=" + numberOfItems + "]";
    }

}
