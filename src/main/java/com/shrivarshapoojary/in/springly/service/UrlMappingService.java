package com.shrivarshapoojary.in.springly.service;


import com.shrivarshapoojary.in.springly.dto.UrlMappingDto;
import com.shrivarshapoojary.in.springly.models.UrlMapping;
import com.shrivarshapoojary.in.springly.models.User;
import com.shrivarshapoojary.in.springly.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UrlMappingService {


    private UrlMappingRepository urlMappingRepository;

    public UrlMappingDto createShortUrl(String originalUrl, User user) {


        String shortUrl=generateShortUrl();

        UrlMapping urlMapping=new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setUser(user);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setCreatedDate(LocalDateTime.now());

        UrlMapping savedMapping=urlMappingRepository.save(urlMapping);

        return converToDto(savedMapping);


    }
    private UrlMappingDto converToDto(UrlMapping urlMapping)
    {
        UrlMappingDto urlMappingDto=new UrlMappingDto();
        urlMappingDto.setOriginaUrl(urlMapping.getOriginalUrl());
        urlMappingDto.setShortUrl(urlMapping.getShortUrl());
        urlMappingDto.setId(urlMapping.getId());
        urlMappingDto.setUsername(urlMapping.getUser().getUsername());
        urlMappingDto.setCreatedDate(urlMapping.getCreatedDate());
        urlMappingDto.setClickCount(urlMapping.getClickCount());
        return  urlMappingDto
    }

    private String generateShortUrl() {
        return null;
    }
}
