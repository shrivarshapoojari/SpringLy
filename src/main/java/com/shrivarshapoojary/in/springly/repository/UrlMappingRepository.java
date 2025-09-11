package com.shrivarshapoojary.in.springly.repository;

import com.shrivarshapoojary.in.springly.models.UrlMapping;
import com.shrivarshapoojary.in.springly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlMappingRepository  extends JpaRepository<UrlMapping,Long>{
    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);
}
