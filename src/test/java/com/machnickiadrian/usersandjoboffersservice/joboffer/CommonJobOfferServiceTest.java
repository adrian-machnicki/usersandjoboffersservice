package com.machnickiadrian.usersandjoboffersservice.joboffer;

import com.machnickiadrian.usersandjoboffersservice.UsersAndJobOffersServiceApplication;
import com.machnickiadrian.usersandjoboffersservice.joboffer.exception.UserDoesNotExistException;
import com.machnickiadrian.usersandjoboffersservice.user.CommonUserService;
import com.machnickiadrian.usersandjoboffersservice.user.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.stream.IntStream;

import static com.machnickiadrian.usersandjoboffersservice.GenerateUtils.login;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UsersAndJobOffersServiceApplication.class})
public class CommonJobOfferServiceTest {

    @Autowired
    CommonJobOfferService jobOfferService;

    @Autowired
    CommonUserService userService;

    @Test
    public void createOfferTest() {
        // given
        UserDto user = UserDto.of(login(), "password");
        user = userService.create(user);

        JobOfferDto offerToSave = createJobOffer(user.getLogin(), JobOfferCategory.COURIER);

        // when
        JobOfferDto savedOffer = jobOfferService.create(offerToSave);

        // then
        assertThat(savedOffer.getId()).isNotNull();
        assertThat(savedOffer.getTitle()).isEqualTo(offerToSave.getTitle());
        assertThat(savedOffer.getCompanyName()).isEqualTo(offerToSave.getCompanyName());
        assertThat(savedOffer.getUserName()).isEqualTo(offerToSave.getUserName());
        assertThat(savedOffer.getCategory()).isEqualTo(offerToSave.getCategory());
        assertThat(savedOffer.getStartDate()).isEqualTo(offerToSave.getStartDate());
        assertThat(savedOffer.getEndDate()).isEqualTo(offerToSave.getEndDate());
    }

    public void createOffer_whenUserWithLoginDoesNotExist() {
        // given
        JobOfferDto offerToSave = createJobOffer(login(), JobOfferCategory.COURIER);

        // when
        // then
        assertThatThrownBy(() -> jobOfferService.create(offerToSave))
                .isInstanceOf(UserDoesNotExistException.class)
                .hasFieldOrPropertyWithValue("login", offerToSave.getUserName());
    }

    @Test
    public void findTest() {
        // given
        String userName = login();
        UserDto user = UserDto.of(userName, "password");
        userService.create(user);

        int foodAndDrinksOffersNumber = 2;
        int officeOffersNumbers = 3;
        int userOffersNumber = foodAndDrinksOffersNumber + officeOffersNumbers;

        createAndSaveJobOffers(foodAndDrinksOffersNumber, userName, JobOfferCategory.FOOD_AND_DRINKS);
        createAndSaveJobOffers(officeOffersNumbers, userName, JobOfferCategory.OFFICE);

        // when
        Collection<JobOfferDto> foundByCategory = jobOfferService.findAllByCategory(JobOfferCategory.FOOD_AND_DRINKS);
        Collection<JobOfferDto> foundByUserAndCategory = jobOfferService.findAllByUserNameAndCategory(userName, JobOfferCategory.OFFICE);
        Collection<JobOfferDto> foundByUser = jobOfferService.findAllByUserName(userName);

        // then
        assertThat(foundByCategory.size()).isEqualTo(foodAndDrinksOffersNumber);
        assertThat(foundByUserAndCategory.size()).isEqualTo(officeOffersNumbers);
        assertThat(foundByUser.size()).isEqualTo(userOffersNumber);
    }

    private void createAndSaveJobOffers(int offersNumber, String username, JobOfferCategory category) {
        IntStream.rangeClosed(1, offersNumber)
                .forEach(i -> {
                    JobOfferDto offer = createJobOffer(username, category);
                    jobOfferService.create(offer);

                });
    }

    private JobOfferDto createJobOffer(String username, JobOfferCategory category) {
        return JobOfferDto.builder()
                .title("title")
                .companyName("company")
                .userName(username)
                .category(category)
                .startDate(ZonedDateTime.now())
                .endDate(ZonedDateTime.now().plusDays(1))
                .build();
    }

}