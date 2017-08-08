package io.braxton.moviereviewer.controllers;

import io.braxton.moviereviewer.interfaces.MovieRepository;
import io.braxton.moviereviewer.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @Autowired
    MovieRepository repo;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Movie> movies = repo.findAll();
        model.addAttribute("movies", movies);
        return "index";
    }
}
