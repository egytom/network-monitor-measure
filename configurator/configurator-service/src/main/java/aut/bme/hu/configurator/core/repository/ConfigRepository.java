package aut.bme.hu.configurator.core.repository;

import aut.bme.hu.configurator.core.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config, String> {

    // TODO - add missing method declarations

}
