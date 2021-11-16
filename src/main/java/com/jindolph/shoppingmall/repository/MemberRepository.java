package com.jindolph.shoppingmall.repository;

import com.jindolph.shoppingmall.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Member findByEmail(String email);
    Boolean existsByEmail(String email);
    Member findByEmailAndPassword(String email, String password);
}