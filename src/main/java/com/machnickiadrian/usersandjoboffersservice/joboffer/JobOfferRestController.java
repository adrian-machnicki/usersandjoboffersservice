package com.machnickiadrian.usersandjoboffersservice.joboffer;

import com.machnickiadrian.usersandjoboffersservice.rest.ValidationErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/offers")
public class JobOfferRestController {

    private JobOfferService jobOfferService;

    public JobOfferRestController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping
    ResponseEntity<Collection<JobOfferDto>> findAll(
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "") String username) {

        Collection<JobOfferDto> foundOffers = null;
        if (category.equals("") && username.equals("")) {
            foundOffers = jobOfferService.findAll();
        }

        JobOfferCategory offerCategory = JobOfferCategory.valueOf(category.toUpperCase());

        if (!category.equals("") && !username.equals("")) {
            foundOffers = jobOfferService.findAllByUserNameAndCategory(username, offerCategory);
        }
        if (!category.equals("")) {
            foundOffers = jobOfferService.findAllByCategory(offerCategory);
        }
        if (!username.equals("")) {
            foundOffers = jobOfferService.findAllByUserName(username);
        }

        if (foundOffers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(foundOffers, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> createJobOffer(@RequestBody @Valid JobOfferDto jobOfferDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(ValidationErrorInfo.ofErrors(bindingResult), HttpStatus.BAD_REQUEST);
        }

        JobOfferDto savedOffer = jobOfferService.create(jobOfferDto);
        return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
    }

}
