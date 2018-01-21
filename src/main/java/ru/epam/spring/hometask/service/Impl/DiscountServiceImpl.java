package ru.epam.spring.hometask.service.Impl;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.DiscountService;
import ru.epam.spring.hometask.service.UserService;
import ru.epam.spring.hometask.service.dicount.Discount;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private UserService userService;
    private List<Discount> discounts;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event,
                              @Nonnull LocalDateTime airDateTime, long numberOfTickets) {

        User userFromDB = userService.getById(user.getId());
        User userToCountDiscount = checkIfRegisteredUser(userFromDB, user);

        byte currentDiscount = 0;
        for (Discount discount : discounts) {
            byte obtainedDiscount = discount.getDiscount(userToCountDiscount, event, airDateTime, numberOfTickets);
            if (currentDiscount < obtainedDiscount) {
                currentDiscount = obtainedDiscount;
            }
        }

        return currentDiscount;
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    private User checkIfRegisteredUser(User userFromDB, User passeUser) {
        if (userFromDB != null) {
            userFromDB.setRegistered(true);
            return userFromDB;
        }
        return passeUser;
    }
}