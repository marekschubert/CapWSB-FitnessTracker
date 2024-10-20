package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Retrieves users who are older than a given date.
     *
     * @param time The cutoff date for user age.
     * @return A {@link List} of users whose age is older than the specified date.
     */
    List<User> getUsersOlderThan(LocalDate time);

    /**
     * Retrieves users whose email contains the given substring.
     *
     * @param email The substring to search for within users' email addresses.
     * @return A {@link List} of users whose email contains the specified part.
     */
    List<User> getUsersByEmailPart(String email);
}
