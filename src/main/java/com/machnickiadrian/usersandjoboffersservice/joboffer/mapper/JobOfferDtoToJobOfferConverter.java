package com.machnickiadrian.usersandjoboffersservice.joboffer.mapper;

import com.machnickiadrian.usersandjoboffersservice.joboffer.JobOffer;
import com.machnickiadrian.usersandjoboffersservice.joboffer.JobOfferDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JobOfferDtoToJobOfferConverter implements Converter<JobOfferDto, JobOffer> {

    @Synchronized
    @Override
    public JobOffer convert(JobOfferDto source) {
        if (source == null) {
            throw new IllegalArgumentException("JobOfferDto was null");
        }

        final JobOffer offer = new JobOffer();
        offer.setId(source.getId());
        offer.setTitle(source.getTitle());
        offer.setCompanyName(source.getCompanyName());
        offer.setUserName(source.getUserName());
        offer.setCategory(source.getCategory());
        offer.setStartDate(source.getStartDate());
        offer.setEndDate(source.getEndDate());
        return offer;
    }
}
