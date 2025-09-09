package com.shrivarshapoojary.in.springly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="click_event")
public class ClickEvent {

    private Long id;
    private LocalDateTime clickDate;

    @ManyToOne
    @JoinColumn(name="url_mapping_id")
    private UrlMapping urlMapping;


}
