package com.jindolph.shoppingmall.repository;

import java.util.List;

import com.jindolph.shoppingmall.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{
    List<Item> findByName(String name);
}