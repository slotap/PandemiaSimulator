package io.github.slotap.pandemiaapp.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface OutputSimulationDataRepository extends CrudRepository<OutputSimulationDataRepository,Long > {
}
