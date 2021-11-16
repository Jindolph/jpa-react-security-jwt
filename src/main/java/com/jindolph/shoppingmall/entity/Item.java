package com.jindolph.shoppingmall.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jindolph.shoppingmall.constant.ItemStat;

import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class Item {
    @Column(name = "item_id")
    private @Id @GeneratedValue Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String detail;
    
    @Column(nullable = false)
    private int price;
    
    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ItemStat itemStat;

    private LocalDateTime regDateTime;

    private LocalDateTime updDateTime;
}
