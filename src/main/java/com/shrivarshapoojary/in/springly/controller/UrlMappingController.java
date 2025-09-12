package com.shrivarshapoojary.in.springly.controller;

import com.shrivarshapoojary.in.springly.dto.ClickEventDto;
import com.shrivarshapoojary.in.springly.dto.UrlMappingDto;
import com.shrivarshapoojary.in.springly.models.UrlMapping;
import com.shrivarshapoojary.in.springly.models.User;
import com.shrivarshapoojary.in.springly.service.UrlMappingService;
import com.shrivarshapoojary.in.springly.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls/")
@AllArgsConstructor
public class UrlMappingController {

    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("shorturl")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDto> createShortUrl(
            @RequestBody Map<String,String> req,
            Principal principal
            ) throws Exception {

        String originalUrl=req.get("originalUrl");
       User user= userService.findByUsername(principal.getName());

        UrlMappingDto urlMappingDto=urlMappingService.createShortUrl(originalUrl,user);

        return  ResponseEntity.ok(urlMappingDto);


    }

    @GetMapping("myurl")
    @PreAuthorize("hasRole('USER')")
   public ResponseEntity<List<UrlMappingDto>> gerUserUrls(Principal principal) throws Exception {
       User user =userService.findByUsername(principal.getName());

       return  ResponseEntity.ok(urlMappingService.gerUrlsByUser(user));
   }


   @GetMapping("/analytics/{shortUrl}")
   @PreAuthorize("hasRole('USER)")
   public ResponseEntity<List<ClickEventDto>>analytics(@PathVariable
                                                       String shortUrl,
                                                       @RequestParam("startDate") String startDate,
                                                       @RequestParam("endDate") String endDate
                                                       )
   {
       DateTimeFormatter formatter=DateTimeFormatter.ISO_LOCAL_DATE_TIME;
       LocalDateTime start=LocalDateTime.parse(startDate,formatter);
       LocalDateTime end=LocalDateTime.parse(endDate,formatter);
       return ResponseEntity.ok(urlMappingService.getClickEventsByDate(shortUrl,start,end));

   }


    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(Principal principal,
                                                                     @RequestParam("startDate") String startDate,
                                                                     @RequestParam("endDate") String endDate) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        User user = userService.findByUsername(principal.getName());
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        Map<LocalDate, Long> totalClicks = urlMappingService.getTotalClicksByUserAndDate(user, start, end);
        return ResponseEntity.ok(totalClicks);
    }

}

