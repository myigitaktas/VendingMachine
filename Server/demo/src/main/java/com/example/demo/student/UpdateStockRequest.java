package com.example.demo.student;

public class UpdateStockRequest {
	private Integer newStockValue;
	private Boolean transaction;
	public Integer getNewStockValue()
	{
		return newStockValue;
	}
	public void setNewStockValue(Integer newStockValue)
	{
		this.newStockValue=newStockValue;
	}
	public Boolean getTransaction() {
		return transaction;
	}
	public void setTransaction(Boolean transaction) {
		this.transaction = transaction;
	}
	

}
