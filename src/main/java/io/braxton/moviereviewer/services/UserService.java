package io.braxton.moviereviewer.services;

import io.braxton.moviereviewer.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}

