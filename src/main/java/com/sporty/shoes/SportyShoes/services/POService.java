package com.sporty.shoes.SportyShoes.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.SportyShoes.entity.PurchaseOrder;
import com.sporty.shoes.SportyShoes.entity.User;
import com.sporty.shoes.SportyShoes.repository.IPurchaseOrderRepo;
@Service
public class POService implements IPOService {

	@Autowired
	IPurchaseOrderRepo poRepo;
	@Override
	public PurchaseOrder createNewPurchaseOrder(PurchaseOrder po) {
		return poRepo.save(po);
	}

	@Override
	public PurchaseOrder updatePurchaseOrder(PurchaseOrder po) {
		return poRepo.save(po);
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return poRepo.findAll();
	}

	@Override
	public Optional<PurchaseOrder> getPurchaseOrderInfo(int poId) {
		return poRepo.findById(poId);
	}

	@Override
	public String deletePurchaseOrder(int poId) {
		poRepo.deleteById(poId);
		return "PO Id "+poId + " successfully deleted";
	}

	@Override
	public List<PurchaseOrder> getPOByUser(User user) {
		return poRepo.findByUser(user);
	}

	@Override
	public List<Object> searchPOBasedOnDateAndCategory(Date poDate, int catId) {
		List<Object> list=  poRepo.searchByPurchaseDateAndCategoryName(poDate, catId);
		return list;
}

}
