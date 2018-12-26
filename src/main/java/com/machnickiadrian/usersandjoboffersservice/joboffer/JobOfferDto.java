package com.machnickiadrian.usersandjoboffersservice.joboffer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class JobOfferDto {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String companyName;

    @NotBlank
    private String userName;

    @NotNull
    private JobOfferCategory category;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
}
