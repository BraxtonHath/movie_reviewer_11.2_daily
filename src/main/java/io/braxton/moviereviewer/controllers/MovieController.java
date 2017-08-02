package io.braxton.moviereviewer.controllers;


import io.braxton.moviereviewer.interfaces.MovieRepository;
import io.braxton.moviereviewer.interfaces.ReviewRepository;
import io.braxton.moviereviewer.models.Movie;
import io.braxton.moviereviewer.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    @Autowired
    MovieRepository repo;

    @Autowired
    ReviewRepository reviewRepo;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Movie> movies = repo.findAll();
        model.addAttribute("movies", movies);
        return "index";
    }


    @RequestMapping(value = "moviepost", method = RequestMethod.POST)
    public String moviepost(@RequestParam ("title") String title,
                           @RequestParam ("genre") String genre,
                           @RequestParam ("imdb") String imdb,
                           @RequestParam ("releaseDate") String releaseDate){
        Movie newMovie = new Movie(title, genre, imdb, releaseDate);
        repo.save(newMovie);
        return "redirect:/";
    }

    @RequestMapping("/movie/{movieId}")
    public String movieReview(@PathVariable("movieId") long movieId, Model model){
        Movie movie = repo.findOne(movieId);
        model.addAttribute("movie", movie);
        return "movieReview";
    }

    @RequestMapping(value = "/movie/{movieId}/review", method = RequestMethod.POST)
    public String review(@PathVariable("movieId") long movieId,
                         @RequestParam("movietitle") String movietitle,
                         @RequestParam("name") String name,
                         @RequestParam("rating") int rating,
                         @RequestParam("age") String age,
                         @RequestParam("gender") String gender,
                         @RequestParam("occupation") String occupation){
        Movie movie = repo.findOne(movieId);
        Review newReview = new Review(movietitle, name, rating, age, gender, occupation, movie);
        reviewRepo.save(newReview);
        return "redirect:/movie/" + movieId;
    }

    @RequestMapping("/movie/edit/{movieId}")
    public String editMovie(@PathVariable("movieId") long movieId, Model model){
        Movie movie = repo.findOne(movieId);
        model.addAttribute("movie", movie);
        return "movieEdit";

    }

    @RequestMapping(value = "/movie/edit/{movieId}/movieEdit", method = RequestMethod.POST)
    public String editMovie(@PathVariable("movieId") long movieId,
                            @RequestParam("title") String title,
                            @RequestParam("genre") String genre,
                            @RequestParam("imdb") String imdb,
                            @RequestParam("releaseDate") String releaseDate){

        Movie movie = repo.findOne(movieId);
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setImdb(imdb);
        movie.setReleasedate(releaseDate);
        repo.save(movie);

        return "redirect:/movie/" + movieId;
    }

}

