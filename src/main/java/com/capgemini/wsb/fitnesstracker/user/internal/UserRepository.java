package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Searches for users whose email contains the given substring, case-insensitive.
     *
     * @param email The substring to search for in users' email addresses.
     * @return A {@link List} of users whose email contains the specified part.
     */
    default List<User> findUsersByEmailPart(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * Finds all users older than a specified date based on their birthdate.
     *
     * @param localDate The cutoff date for the user's birthdate.
     * @return A {@link List} of users who were born before the specified date.
     */
    default List<User> findAllOlderThan(LocalDate localDate) {
        return findAll()
                .stream()
                .filter(user -> user.getBirthdate().isBefore(localDate))
                .toList();
    }
}