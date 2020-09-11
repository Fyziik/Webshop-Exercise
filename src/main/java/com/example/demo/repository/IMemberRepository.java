package com.example.demo.repository;

import com.example.demo.model.Member;

import java.util.List;

public interface IMemberRepository {

    boolean create(Member m);

    Member read(String email);

    List<Member> readAll();

}
