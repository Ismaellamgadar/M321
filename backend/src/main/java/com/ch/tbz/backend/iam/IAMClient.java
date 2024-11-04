package com.ch.tbz.backend.iam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "iamClient", url = "http://iam:8081/auth")
public interface IAMClient {

    @GetMapping("/validate")
    Boolean validateToken(@RequestParam("token") String token);
}
