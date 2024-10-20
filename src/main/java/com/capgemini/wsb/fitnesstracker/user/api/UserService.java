package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created.
     * @return The created {@link User} instance.
     */
    User createUser(User user);

    /**
     * Deletes a user based on their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    void deleteUser(Long id);

    /**
     * Updates an existing user based on their ID.
     *
     * @param id   The ID of the user to be updated.
     * @param user The updated user information.
     */
    void updateUser(Long id, User user);
}
