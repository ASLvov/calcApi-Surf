package app.repository;

import app.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT usr FROM UserEntity usr WHERE usr.userName = :userName")
    Optional<UserEntity> findByUserName(@Param("userName") String userName);
}
