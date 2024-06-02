package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 구현체를 자동으로 만들어서 스프링 빈에 자동으로 등록한다
    // 스프링 데이터 JPA가 알아서 등록해준다.
    Optional<Member> findByName(String name);
}
