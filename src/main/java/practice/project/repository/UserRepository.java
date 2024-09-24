package practice.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import practice.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
