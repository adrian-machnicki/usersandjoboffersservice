package com.machnickiadrian.usersandjoboffersservice.joboffer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "job_offers")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "company_name")
    private String companyName;

    @Enumerated(value = EnumType.STRING)
    private JobOfferCategory category;

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

}
