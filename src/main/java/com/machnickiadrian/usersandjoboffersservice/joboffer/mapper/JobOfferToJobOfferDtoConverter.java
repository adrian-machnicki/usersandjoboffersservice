package com.machnickiadrian.usersandjoboffersservice.joboffer.mapper;

import com.machnickiadrian.usersandjoboffersservice.joboffer.JobOffer;
import com.machnickiadrian.usersandjoboffersservice.joboffer.JobOfferDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JobOfferToJobOfferDtoConverter implements Converter<JobOffer, JobOfferDto> {

    @Synchronized
    @Override
    public JobOfferDto convert(JobOffer source) {
        if (source == null) {
            throw new IllegalArgumentException("JobOffer was null");
        }

        final JobOfferDto offerDto = new JobOfferDto();
        offerDto.setId(source.getId());
        offerDto.setTitle(source.getTitle());
        offerDto.setCompanyName(source.getCompanyName());
        offerDto.setUserName(source.getUserName());
        offerDto.setStartDate(source.getStartDate());
        offerDto.setEndDate(source.getEndDate());
        return offerDto;
    }
}
