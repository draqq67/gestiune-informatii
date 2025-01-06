    package com.dragos.gestiune_informatii.repository;

    import com.dragos.gestiune_informatii.model.CompetitiiMain;
    import com.dragos.gestiune_informatii.model.Organizatori;
    import jakarta.transaction.Transactional;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.jpa.repository.Modifying;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.Date;
    import java.util.List;

    @Repository
    public interface CompetitiiMainRepository extends CrudRepository<CompetitiiMain, Integer> {

        // Corrected method to find organizer by name (assuming 'name' is the correct column)
        @Query("SELECT o.id FROM Organizatori o WHERE o.name = :organizerName")
        Integer findOrganizerIdByName(@Param("organizerName") String organizerName);

        // Method to find an organizer by name (for checking if it exists)
    //    @Modifying
    //    @Transactional
    //    @Query("INSERT INTO CompetitiiMain (nume, data_start, data_end, descriere, detalii, site, status, type, organizator_id) " +
    //            "VALUES (:competitionName, :dataStart, :dataEnd, :descriere, :detalii, :site, :status, :type, :organizerId)")
    //    void insertCompetition(String competitionName, Date dataStart, Date dataEnd, String descriere,
    //                           String detalii, String site, boolean status, String type, Integer organizerId);
       // Fetch all competitions
        @Query("SELECT c FROM CompetitiiMain c")
        List<CompetitiiMain> findAllCompetitions();

        @Query("SELECT c FROM CompetitiiMain c WHERE c.id = :id")
        CompetitiiMain findCompetitionById(@Param("id") Integer id);
    }
