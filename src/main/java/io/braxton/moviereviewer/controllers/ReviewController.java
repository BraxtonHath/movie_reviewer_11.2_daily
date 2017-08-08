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
public class ReviewController {


    @Autowired
    MovieRepository repo;

    @Autowired
    ReviewRepository reviewRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/movie/{movieId}")
    public String movieReview(@PathVariable("movieId") long movieId, Model model){
        Movie movie = repo.findOne(movieId);
        model.addAttribute("movie", movie);
        return "movieReview";
    }

    @RequestMapping(value = "/User/movie/{movieId}/review", method = RequestMethod.POST)
    public String review(@PathVariable("movieId") long movieId,
                         @RequestParam("name") String name,
                         @RequestParam("movietitle") String movietitle,
                         @RequestParam("rating") int rating,
                         @RequestParam("age") String age,
                         @RequestParam("gender") String gender,
                         @RequestParam("occupation") String occupation,
                         Principal principal){
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        Movie movie = repo.findOne(movieId);
        Review newReview = new Review(name, movietitle, rating, age, gender, occupation, movie);
        newReview.setUser(user);
        reviewRepo.save(newReview);
        return "redirect:/movie/" + movieId;
    }

    @RequestMapping("/User/movie/edit/{movieId}")
    public String editMovie(@PathVariable("movieId") long movieId, Model model){
        Movie movie = repo.findOne(movieId);
        model.addAttribute("movie", movie);
        return "movieEdit";

    }

    @RequestMapping(value = "/User/movie/edit/{movieId}/movieEdit", method = RequestMethod.POST)
    public String editMoviePost(@PathVariable("movieId") long movieId,
                            @RequestParam("title") String title,
                            @RequestParam("genre") String genre,
                            @RequestParam("imdb") String imdb,
                            @RequestParam("releaseDate") String releaseDate,
                            Principal principal){
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        Movie movie = repo.findOne(movieId);
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setImdb(imdb);
        movie.setReleasedate(releaseDate);
        movie.setUser(user);
        repo.save(movie);

        return "redirect:/";
    }

}

