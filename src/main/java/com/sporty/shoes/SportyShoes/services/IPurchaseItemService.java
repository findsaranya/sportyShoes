package com.sporty.shoes.SportyShoes.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.sporty.shoes.SportyShoes.entity.PurchaseItem;

public interface IPurchaseItemService {
	PurchaseItem createNewPurchaseItem(PurchaseItem purchaseItem);
	PurchaseItem updatePurchaseItem(PurchaseItem purchaseItem);
	List<PurchaseItem> getAllPurchaseItems();
	Optional<PurchaseItem> getPurchaseItemInfo(int itemId);
	String deletePurchaseItem(int itemId);
	List<PurchaseItem> saveAll(Set<PurchaseItem> items);
}
