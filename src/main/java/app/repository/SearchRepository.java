package app.repository;

import app.domain.SearchRequest;
import app.domain.SearchResponse;
import app.domain.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<UserRequest, Long> {
    @Query("SELECT req FROM UserRequest req WHERE req.date>= :startDate AND req.date<= :endDate")
    List<UserRequest> findByDate(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);

    @Query("SELECT req FROM UserRequest req WHERE req.statement = :statement")
    List<UserRequest> findByStatement(@Param("statement") String Statement);
}
