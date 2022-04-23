package cinemahouse.controller;


import cinemahouse.service.MovieService;
import cinemahouse.model.Movie;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/bookMyShow")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/all")
    @ApiOperation("/active database")
    public List<Movie> getAll(){return movieService.listAllUser();}

    @GetMapping("/cities")
    @ApiOperation(value = "returns all city having movie shows")
    public Set<String> city() {return movieService.getAllCities();}

    @GetMapping("/movies/{city}")
    @ApiOperation(value = "returns all movies in the city")
    public Set<String> movies(@PathVariable String city) { return movieService.getAllMovies(city);}

    @GetMapping("/movies/{city}/{movies}")
    @ApiOperation(value = "returns all cinema and timeSlots showing that movie in that city")
    public ArrayList<ArrayList<String>> cinemaAndTimeSlot(@PathVariable String city, @PathVariable String movies) { return movieService.getAllCinemaAndTimeSlot(city,movies);}

    @PutMapping("/movies/{city}/{movies}/{timeSlot}/{seat}")
    @ApiOperation(value = "books seat")
    public void Book(@PathVariable String city, @PathVariable String movies, @PathVariable String timeSlot, @PathVariable int seat)
    {
        movieService.bookSeat(city,movies,timeSlot,seat);
    }

    @GetMapping("/moviesSeatAvailability/{city}/{movies}/{timeSlot}")
    @ApiOperation(value = "returns siting plan")
    public String[] seats(@PathVariable String city, @PathVariable String movies, @PathVariable String timeSlot)
    {
        return movieService.getSeats(city,movies,timeSlot);
    }
    @PostMapping("/")
    @ApiOperation(value = "add new movie")
    public void add(@RequestBody Movie movie) {
        movieService.saveUser(movie);
    }


    /*
    @PostMapping("/")
    public void add(@RequestBody Movie user) {
        movieService.saveUser(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Movie user, @PathVariable Integer id) {
        try {
            Movie existUser = movieService.getUser(id);
            user.setId(id);
            movieService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        movieService.deleteUser(id);
    }
}