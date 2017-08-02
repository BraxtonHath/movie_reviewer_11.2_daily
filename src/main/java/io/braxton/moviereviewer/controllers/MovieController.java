package io.braxton.moviereviewer.controllers;


import io.braxton.moviereviewer.interfaces.MovieRepository;
import io.braxton.moviereviewer.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    @Autowired
    MovieRepository repo;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Movie> movies = repo.findAll();
        model.addAttribute("movies", movies);
        return "index";
    }

    @RequestMapping(value = "/moviePost", method = RequestMethod.GET)
    public String createMovie() {
        return "moviePost";
    }

    @RequestMapping(value = "moviePost", method = RequestMethod.POST)
    public String moviePost(@RequestParam ("title") String title,
                           @RequestParam ("genre") String genre,
                           @RequestParam ("imdb") String imdb,
                           @RequestParam ("releasedate") String releasedate){
        Movie newMovie = new Movie(title, genre, imdb, releasedate);
        repo.save(newMovie);
        return "redirect:/";
    }
}
