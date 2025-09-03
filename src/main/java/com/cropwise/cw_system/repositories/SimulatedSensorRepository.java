package com.cropwise.cw_system.repositories;

import com.cropwise.cw_system.models.SimulatedSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulatedSensorRepository extends JpaRepository<SimulatedSensor, Long> {

}
