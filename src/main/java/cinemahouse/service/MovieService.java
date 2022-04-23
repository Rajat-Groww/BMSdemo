package cinemahouse.service;




import cinemahouse.model.Movie;
import cinemahouse.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> listAllUser() {
        return movieRepository.findAll();
    }

    public void saveUser(Movie user) {
        movieRepository.save(user);
    }

    public Movie getUser(Integer id) {
        return movieRepository.findById(id).get();
    }

    public void deleteUser(Integer id) {
        movieRepository.deleteById(id);
    }

    public Set<String> getAllCities() {
        List<Movie> movies = movieRepository.findAll();
        Set<String> city = new HashSet<String>();
        for(Movie movie : movies)
        {
            city.add(movie.getCity());
        }
        return city;

    }

    public Set<String> getAllMovies(String city) {
        List<Movie> movies = movieRepository.findAll();
        Set<String> movieName = new HashSet<String>();
        for (Movie movie : movies)
        {
            if(city.equals(movie.getCity()))
                movieName.add(movie.getName());
        }
        return movieName;
    }

    public ArrayList<ArrayList<String>> getAllCinemaAndTimeSlot(String city, String movies) {

        List<Movie> Movies = movieRepository.findAll();
        ArrayList<ArrayList<String>> cinemaAndTimeSlot = new ArrayList<>();
        for (Movie movie : Movies)
        {
            if(city.equals(movie.getCity()) && movies.equals(movie.getName()))
            {
                ArrayList<String> t = new ArrayList<>();
                t.add(movie.getCinemaHouse());
                t.add(movie.getTimeSlot());
                cinemaAndTimeSlot.add(t);
            }

        }
        return cinemaAndTimeSlot;
    }

    public void bookSeat(String city, String movies, String timeSlot, int seat) {
        List<Movie> Movies = movieRepository.findAll();
        for (Movie movie : Movies)
        {
            if(city.equals(movie.getCity()) && movies.equals(movie.getName()) && timeSlot.equals(movie.getTimeSlot()))
            {
                    String seatsNew[] = {"Available","Available","Available","Available","Available"};
                    seatsNew[seat] = "Occupied";
                    movie.setSeats(seatsNew);
                    movieRepository.save(movie);
                    return;
            }

        }
    }

    public String[] getSeats(String city, String movies, String timeSlot) {
        List<Movie> Movies = movieRepository.findAll();
        for (Movie movie : Movies) {
            if (city.equals(movie.getCity()) && movies.equals(movie.getName()) && timeSlot.equals(movie.getTimeSlot())) {
                return movie.getSeats();
            }
        }
        return null;
    }
}
