package io.braxton.moviereviewer.controllers;


import io.braxton.moviereviewer.interfaces.MovieRepository;
import io.braxton.moviereviewer.interfaces.ReviewRepository;
import io.braxton.moviereviewer.interfaces.UserRepository;
import io.braxton.moviereviewer.models.Movie;
import io.braxton.moviereviewer.models.Review;
import io.braxton.moviereviewer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MovieController {

    @Autowired
    MovieRepository repo;

    @Autowired
    ReviewRepository reviewRepo;

    @Autowired
    UserRepository userRepo;


    @RequestMapping("User/moviepost")
    public String movieCreate(Model model) {
        Iterable<Movie> movies = repo.findAll();
        model.addAttribute("movies", movies);
        return "movieCreate";
    }



    @RequestMapping(value = "/moviepost", method = RequestMethod.POST)
    public String moviepost(@RequestParam ("title") String title,
                           @RequestParam ("genre") String genre,
                           @RequestParam ("imdb") String imdb,
                           @RequestParam ("releaseDate") String releaseDate){
        Movie newMovie = new Movie(title, genre, imdb, releaseDate);
        repo.save(newMovie);
        return "redirect:/";
    }
}

