package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dao.MemberDAO;
import com.makersacademy.acebook.model.Member;
import com.makersacademy.acebook.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberDAO memberDAO;

    @GetMapping("/login")
    public String showLoginForm(){
        return "loginForm";
    }


    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("member", new Member());
        return "registerForm";
    }
    @PostMapping("/register")
    public String registerMember(@Valid Member member, Model model){
        String email = member.getEmail();
        if (memberDAO.findByEmail(email) != null){
            model.addAttribute("exist",true);
            return "registerForm";
        }
        memberService.createMember(member);
        model.addAttribute("success", true);
        return "loginForm";
    }
}
