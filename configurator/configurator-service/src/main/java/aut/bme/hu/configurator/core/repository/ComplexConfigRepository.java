package aut.bme.hu.configurator.core.repository;

import aut.bme.hu.configurator.core.model.ComplexConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplexConfigRepository extends JpaRepository<ComplexConfig, String> {
}
