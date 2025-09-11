package com.shrivarshapoojary.in.springly.service;


import com.shrivarshapoojary.in.springly.dto.UrlMappingDto;
import com.shrivarshapoojary.in.springly.models.UrlMapping;
import com.shrivarshapoojary.in.springly.models.User;
import com.shrivarshapoojary.in.springly.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

        return convertToDto(savedMapping);


    }






    private UrlMappingDto convertToDto(UrlMapping urlMapping)
    {
        UrlMappingDto urlMappingDto=new UrlMappingDto();
        urlMappingDto.setOriginaUrl(urlMapping.getOriginalUrl());
        urlMappingDto.setShortUrl(urlMapping.getShortUrl());
        urlMappingDto.setId(urlMapping.getId());
        urlMappingDto.setUsername(urlMapping.getUser().getUsername());
        urlMappingDto.setCreatedDate(urlMapping.getCreatedDate());
        urlMappingDto.setClickCount(urlMapping.getClickCount());
        return  urlMappingDto;
    }

    private String generateShortUrl() {
        Random random=new Random();
        StringBuilder shortUrl=new StringBuilder(8);

        String characters="ABCDEFGEHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        for(int i=0;i<8;i++)
        {
            shortUrl.append(characters.charAt(random.nextInt(characters.length()-1)));
        }

        return  shortUrl.toString();

    }


    public List<UrlMappingDto> gerUrlsByUser(User user) {

        return urlMappingRepository.findByUser(user).stream().map(this::convertToDto).collect(Collectors.toUnmodifiableList());
    }
}
