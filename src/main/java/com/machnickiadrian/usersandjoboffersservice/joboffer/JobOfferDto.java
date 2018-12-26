package com.machnickiadrian.usersandjoboffersservice.joboffer;

import lombok.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull
    @FutureOrPresent
    private ZonedDateTime startDate;

    @NotNull
    @FutureOrPresent
    private ZonedDateTime endDate;
}
