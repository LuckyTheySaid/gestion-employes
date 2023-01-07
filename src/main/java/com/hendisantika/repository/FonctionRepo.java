package com.hendisantika.repository;

import com.hendisantika.entity.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FonctionRepo extends JpaRepository<Fonction, Long> {

    @Query("SELECT count(f) FROM Fonction f")
    Integer getcountfonction();

}
