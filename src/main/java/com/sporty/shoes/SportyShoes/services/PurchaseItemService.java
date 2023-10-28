package com.sporty.shoes.SportyShoes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.SportyShoes.entity.PurchaseItem;
import com.sporty.shoes.SportyShoes.repository.IPurchaseItemsRepo;
@Service
public class PurchaseItemService implements IPurchaseItemService {

	@Autowired
	IPurchaseItemsRepo itemRepo;
	
	@Override
	public PurchaseItem createNewPurchaseItem(PurchaseItem purchaseItem) {
		return itemRepo.save(purchaseItem);
	}

	@Override
	public PurchaseItem updatePurchaseItem(PurchaseItem purchaseItem) {
		return itemRepo.save(purchaseItem);
	}

	@Override
	public List<PurchaseItem> getAllPurchaseItems() {
		return itemRepo.findAll();
	}

	@Override
	public Optional<PurchaseItem> getPurchaseItemInfo(int itemId) {
		return itemRepo.findById(itemId);
	}

	@Override
	public String deletePurchaseItem(int itemId) {
		itemRepo.deleteById(itemId);
		return "Item id "+itemId+" successfully deleted";
	}

	@Override
	public List<PurchaseItem> saveAll(Set<PurchaseItem> items) {
		List<PurchaseItem> list = new ArrayList<PurchaseItem>();
		list = items.stream().collect(Collectors.toList());
		return itemRepo.saveAll(list);
	}

}
