package io.member;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;


    public interface MemberRepository {
        void add(Member member);

        List<Member> findAll();
}
