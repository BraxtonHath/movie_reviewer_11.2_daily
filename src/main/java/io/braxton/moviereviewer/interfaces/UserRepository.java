package io.braxton.moviereviewer.interfaces;

import io.braxton.moviereviewer.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

