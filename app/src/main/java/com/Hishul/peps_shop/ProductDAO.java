package com.moringaschool.peps_shop;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface ProductDAO {

    @Query("select * from products")
    List<Product> getAllProducts();

    @Insert
    void addProduct(Product... product);

    @Update
    void update(Product... product);

    @Query("delete from products")
    void delete();
}
