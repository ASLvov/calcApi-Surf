package app.repository;

import app.domain.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {

    @Query("SELECT req FROM UserRequest req WHERE req.userId = :userId AND " +
            "req.statement LIKE :statement AND req.date>= :startDate AND req.date<= :endDate")
    List<UserRequest> findRequests(@Param("userId") Long userId,
                                   @Param("statement") String statement,
                                   @Param("startDate") LocalDateTime startDate,
                                   @Param("endDate") LocalDateTime endDate);
}
