package by.evgen.productservice.repository;

import by.evgen.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
