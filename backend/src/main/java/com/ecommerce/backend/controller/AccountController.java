package com.ecommerce.backend.controller;

import com.ecommerce.backend.Repository.MemberRepository;
import com.ecommerce.backend.dto.MemberDto;
import com.ecommerce.backend.entity.Member;
import com.ecommerce.backend.service.JwtService;
import com.ecommerce.backend.service.JwtServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class AccountController{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(
            @RequestBody Map<String, String> params
            , HttpServletResponse res)

    {
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        if(member != null) {
            int id = member.getId();
            String token = jwtService.getToken("id",id);
            Cookie cookie = new Cookie("token",token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setSecure(true);
            res.addCookie(cookie);

            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/account/create-user")
    public ResponseEntity createUser(@RequestBody MemberDto memberDto)
    {
        Member newMember = new Member();
        newMember.setEmail(memberDto.getEmail());
        newMember.setPassword(memberDto.getPassword());
        memberRepository.save(newMember);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false)String token)
    {
        System.out.println("request token:"+token);
        Claims claims = jwtService.getClaims(token);

        if(claims!=null)
        {
            int id =Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

