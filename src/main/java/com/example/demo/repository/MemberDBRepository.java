package com.example.demo.repository;

import com.example.demo.model.Member;

import java.util.List;

public class MemberDBRepository implements IMemberRepository {

    @Override
    public boolean create(Member m) {
        return false;
    }

    @Override
    public Member read(String email) {
        return null;
    }

    @Override
    public List<Member> readAll() {
        return null;
    }
}
