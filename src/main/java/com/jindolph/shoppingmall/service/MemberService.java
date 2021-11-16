package com.jindolph.shoppingmall.service;

import com.jindolph.shoppingmall.entity.Member;
import com.jindolph.shoppingmall.exception.DuplicatedEmailException;
import com.jindolph.shoppingmall.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    public Member create(final Member newMember) {
        final String email = newMember.getEmail();

        if (memberRepository.existsByEmail(email)) {
            throw new DuplicatedEmailException(email);
        }

        return memberRepository.save(newMember);
    }

    public Member getByCredentials(final String email, final String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User//
                .builder()//
                .username(member.getEmail())//
                .password(member.getPassword())//
                .roles("USER") //
                .build();
    }
}