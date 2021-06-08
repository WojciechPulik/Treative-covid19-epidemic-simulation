package pl.wpulik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.wpulik.model.DailyState;

@Repository
public interface DailyStateRepository extends JpaRepository<DailyState, Long> {

}
