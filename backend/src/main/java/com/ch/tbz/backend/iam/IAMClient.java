package com.ch.tbz.backend.iam;

import com.ch.tbz.backend.entity.Book;
import com.ch.tbz.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "iamClient", url = "http://localhost:8081/auth")
public interface IAMClient {

    @GetMapping("/validate")
    Boolean validateToken(@RequestParam("token") String token);
}

