package com.sporty.shoes.SportyShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.SportyShoes.entity.PurchaseItem;

@Repository
public interface IPurchaseItemsRepo extends JpaRepository<PurchaseItem, Integer> {

}
