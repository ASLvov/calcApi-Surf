package app.repository;

import app.domain.UserRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequests, Long> {

    @Query("SELECT req FROM UserRequests req WHERE (req.userId = :userId OR :userId IS NULL) AND " +
            "(req.statement = :statement OR :statement IS NULL) AND " +
            "req.date>= :startDate  AND " +
            "req.date<= :endDate")
    List<UserRequests> findRequests(@Param("userId") Long userId,
                                    @Param("statement") String statement,
                                    @Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);
}
