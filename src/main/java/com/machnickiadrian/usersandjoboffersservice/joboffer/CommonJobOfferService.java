package com.machnickiadrian.usersandjoboffersservice.joboffer;

import com.machnickiadrian.usersandjoboffersservice.joboffer.mapper.JobOfferDtoToJobOfferConverter;
import com.machnickiadrian.usersandjoboffersservice.joboffer.mapper.JobOfferToJobOfferDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
public class CommonJobOfferService implements JobOfferService {

    private JobOfferRepository jobOfferRepository;
    private JobOfferToJobOfferDtoConverter jobOfferToJobOfferDtoConverter;
    private JobOfferDtoToJobOfferConverter jobOfferDtoToJobOfferConverter;

    @Autowired
    public CommonJobOfferService(JobOfferRepository jobOfferRepository, JobOfferToJobOfferDtoConverter jobOfferToJobOfferDtoConverter, JobOfferDtoToJobOfferConverter jobOfferDtoToJobOfferConverter) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobOfferToJobOfferDtoConverter = jobOfferToJobOfferDtoConverter;
        this.jobOfferDtoToJobOfferConverter = jobOfferDtoToJobOfferConverter;
    }

    @Override
    public Collection<JobOfferDto> findAll() {
        return jobOfferRepository.findAll().stream()
                .map(offer -> jobOfferToJobOfferDtoConverter.convert(offer))
                .collect(toList());
    }

    @Override
    public Collection<JobOfferDto> findAllByUserName(String userName) {
        return jobOfferRepository.findAllByUserName(userName).stream()
                .map(offer -> jobOfferToJobOfferDtoConverter.convert(offer))
                .collect(toList());
    }

    @Override
    public Collection<JobOfferDto> findAllByCategory(JobOfferCategory category) {
        return jobOfferRepository.findAllByCategory(category).stream()
                .map(offer -> jobOfferToJobOfferDtoConverter.convert(offer))
                .collect(toList());
    }

    @Override
    public Collection<JobOfferDto> findAllByUserNameAndCategory(String userName, JobOfferCategory category) {
        return jobOfferRepository.findAllByUserNameAndCategory(userName, category).stream()
                .map(offer -> jobOfferToJobOfferDtoConverter.convert(offer))
                .collect(toList());
    }

    @Override
    public JobOfferDto create(JobOfferDto jobOfferDto) {
        JobOffer offer = jobOfferDtoToJobOfferConverter.convert(jobOfferDto);
        JobOffer savedOffer = jobOfferRepository.save(offer);
        return jobOfferToJobOfferDtoConverter.convert(savedOffer);
    }
}
