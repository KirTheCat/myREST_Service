import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieAndSeriesRepository extends JpaRepository<MovieAndSeries, Integer> {
}