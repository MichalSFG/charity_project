package pl.coderslab.charity.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    Institution findInstById(Long id);

    @Query("select i from Institution i where i.isActivated=true")
    List<Institution> findAllByActivated();

}
