package com.sporty.shoes.SportyShoes.services;

import java.util.List;
import java.util.Optional;

import com.sporty.shoes.SportyShoes.entity.PurchaseOrder;

public interface IPOService {
	PurchaseOrder createNewPurchaseOrder(PurchaseOrder po);
	PurchaseOrder updatePurchaseOrder(PurchaseOrder po);
	List<PurchaseOrder> getAllPurchaseOrders();
	Optional<PurchaseOrder> getPurchaseOrderInfo(int poId);
	String deletePurchaseOrder(int poId);
	
}
