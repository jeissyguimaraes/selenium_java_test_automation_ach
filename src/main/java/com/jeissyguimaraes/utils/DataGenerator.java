package com.jeissyguimaraes.utils;

import com.github.javafaker.Faker;
import com.jeissyguimaraes.models.User;

public class DataGenerator {

    private static final Faker faker = new Faker();

    public static User generateUser() {
        User user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setPostalCode(faker.address().zipCode());
        return user;
    }
}
