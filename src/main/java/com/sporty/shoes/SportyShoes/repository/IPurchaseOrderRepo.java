package com.sporty.shoes.SportyShoes.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.SportyShoes.entity.PurchaseOrder;
import com.sporty.shoes.SportyShoes.entity.User;
@Repository
public interface IPurchaseOrderRepo extends JpaRepository<PurchaseOrder, Integer> {
 List<PurchaseOrder> findByUser(User user);
 @Query("From Product prod JOIN Category cat ON prod.category.catId=cat.catId JOIN PurchaseItem pitem ON pitem.product.poductId=prod.poductId JOIN PurchaseOrder po ON po.poId=pitem.po.poId WHERE prod.category.catId=?1 AND po.purchaseDate >= ?2")
List<Object> searchByPurchaseDateAndCategoryName(Date poDate,int catId);
}
