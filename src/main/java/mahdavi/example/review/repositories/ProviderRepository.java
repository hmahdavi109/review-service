package mahdavi.example.review.repositories;

import mahdavi.example.review.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProviderRepository extends JpaRepository<Provider, Long> {

    boolean existsByName(String name);
}
