package repository;

import model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
    List<Authorities> findByUsername(String username);
}
