package com.machnickiadrian.usersandjoboffersservice.joboffer;

import java.util.Collection;

public interface JobOfferService {

    Collection<JobOfferDto> findAll();
    Collection<JobOfferDto> findAllByUserName(String userName);
    Collection<JobOfferDto> findAllByCategory(JobOfferCategory category);
    Collection<JobOfferDto> findAllByUserNameAndCategory(String userName, JobOfferCategory category);
    JobOfferDto create(JobOfferDto jobOfferDto);

}
