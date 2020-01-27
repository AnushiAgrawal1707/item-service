package com.mykart.repository.cart;

import com.mykart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart , Integer> {

    @Query("from Cart where cart_id=?1 and is_ordered=?2")
    List<Cart> findByCartIdAndOrdered(String cart_id, int check);


    @Query("from Cart where cart_id=?1 and item_id=?2 and is_ordered=?3")
    Cart findByCartIdAndItemIdAndIsOrdered(String cart_id, int item_id, int check);
}
