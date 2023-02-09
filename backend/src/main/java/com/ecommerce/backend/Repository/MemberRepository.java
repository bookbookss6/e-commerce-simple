package com.ecommerce.backend.Repository;

import com.ecommerce.backend.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {

    @Autowired
    Member findByEmailAndPassword(String email, String password);

}
