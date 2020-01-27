package com.mykart.repository.orders;

import com.mykart.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query("from Orders where user_id=?1")
   public List<Orders> findAllByUser_id(int user_id);

    @Query("from Orders where order_id=?1")
   public Orders findAllByOrder_id(int order_id);

    @Query("from Orders where user_id=?1 and order_id=?2")
    Orders findAllByUser_idAndOrder_id (int user_id,int order_id);
}
