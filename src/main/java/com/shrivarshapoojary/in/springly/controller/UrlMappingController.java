package com.shrivarshapoojary.in.springly.controller;

import com.shrivarshapoojary.in.springly.dto.UrlMappingDto;
import com.shrivarshapoojary.in.springly.models.User;
import com.shrivarshapoojary.in.springly.service.UrlMappingService;
import com.shrivarshapoojary.in.springly.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {

    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("shroturl")
    @PreAuthorize("hasRole('USER)")
    public ResponseEntity<UrlMappingDto> createShortUrl(
            @RequestBody Map<String,String> req,
            Principal principal
            ) throws Exception {

        String originalUrl=req.get("originalUrl");
       User user= userService.findByUsername(principal.getName());

        UrlMappingDto urlMappingDto=urlMappingService.createShortUrl(originalUrl,user);

        return  ResponseEntity.ok(urlMappingDto);


    }
}
