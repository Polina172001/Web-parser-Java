package com.equestrian.clubs.repository;

import com.equestrian.clubs.model.Clubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubsRepository extends JpaRepository<Clubs,Long> {
    @Query("SELECT p FROM Clubs p WHERE p.title LIKE %?1% " +
            "or p.section LIKE %?1%" +
            "or p.subway LIKE %?1%")
    public List<Clubs> search(String keyword);
}
