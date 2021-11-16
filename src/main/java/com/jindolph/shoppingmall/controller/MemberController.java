package com.jindolph.shoppingmall.controller;

import com.jindolph.shoppingmall.dto.MemberDTO;
import com.jindolph.shoppingmall.entity.Member;
import com.jindolph.shoppingmall.security.TokenProvider;
import com.jindolph.shoppingmall.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody MemberDTO memberDTO) {
        try {
            Member member = new Member();

            member.setName(memberDTO.getName());
            member.setEmail(memberDTO.getEmail());
            member.setPassword(memberDTO.getPassword());

            Member registeredMember = memberService.create(member);

            MemberDTO registeredMemberDTO = MemberDTO.builder().id(registeredMember.getId())
                    .email(registeredMember.getEmail()).name(registeredMember.getName()).build();

            return ResponseEntity.ok().body(registeredMemberDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody MemberDTO memberDTO) {
        Member searchedMember = memberService.getByCredentials(memberDTO.getEmail(), memberDTO.getPassword());

        if (searchedMember != null) {
            final String token = tokenProvider.create(searchedMember);
            final MemberDTO responseMemberDTO = MemberDTO.builder()
                    .email(searchedMember.getEmail())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseMemberDTO);
        } else {
            return ResponseEntity.badRequest().body("login is failed");
        }
    }
}