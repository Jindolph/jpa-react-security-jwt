package com.jindolph.shoppingmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class Member {
    @Column(name = "member_id")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private @Id @GeneratedValue(generator = "system-uuid") String id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
}
