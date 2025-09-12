package com.shrivarshapoojary.in.springly.repository;

import com.shrivarshapoojary.in.springly.models.ClickEvent;
import com.shrivarshapoojary.in.springly.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ClickEventRepository extends JpaRepository<ClickEvent,Long> {
    List<ClickEvent>findByUrlMappingAndClickDateBetween(UrlMapping urlMapping, LocalDateTime startDate,LocalDateTime endDate);
    List<ClickEvent>findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMapping, LocalDateTime startDate,LocalDateTime endDate);

}
