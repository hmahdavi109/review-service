package mahdavi.example.review.repositories;

import mahdavi.example.review.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    Page<Product> findByIsVisible(Boolean isVisible , Pageable pageable);
}
