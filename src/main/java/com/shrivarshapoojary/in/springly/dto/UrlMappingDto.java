package com.shrivarshapoojary.in.springly.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDto {
    private Long id;
    private  String originaUrl;
    private String shortUrl;
    private int clickCount;
    private LocalDateTime createdDate;
    private String  username;

}
