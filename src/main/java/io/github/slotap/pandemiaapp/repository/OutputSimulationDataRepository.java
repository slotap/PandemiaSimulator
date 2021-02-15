package io.github.slotap.pandemiaapp.repository;

import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OutputSimulationDataRepository extends CrudRepository<OutputSimulationData,Long > {
    List<OutputSimulationData> findAll();
    Optional<OutputSimulationData> findById(Long id);
    void deleteById(Long id);
}
