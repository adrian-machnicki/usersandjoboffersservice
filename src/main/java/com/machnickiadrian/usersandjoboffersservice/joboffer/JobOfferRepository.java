package com.machnickiadrian.usersandjoboffersservice.joboffer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    Collection<JobOffer> findAllByCategory(JobOfferCategory jobOfferCategory);
    Collection<JobOffer> findAllByUserName(String userName);
    Collection<JobOffer> findAllByUserNameAndCategory(String userName, JobOfferCategory jobOfferCategory);

}
