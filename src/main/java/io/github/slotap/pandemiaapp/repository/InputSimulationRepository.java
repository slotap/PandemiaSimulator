package io.github.slotap.pandemiaapp.repository;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface InputSimulationRepository extends CrudRepository<InputSimulationData,Long > {
}
