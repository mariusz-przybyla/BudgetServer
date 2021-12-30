package BudzetServer.BudzetServer.repository;

import BudzetServer.BudzetServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "UPDATE app_user SET is_enabled = true WHERE login = ?", nativeQuery = true)
    int enableUser(String login);
}
