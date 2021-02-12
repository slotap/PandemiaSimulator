package io.github.slotap.pandemiaapp.repository;


import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface OutputSimulationDataRepository extends CrudRepository<OutputSimulationData,Long > {
}
