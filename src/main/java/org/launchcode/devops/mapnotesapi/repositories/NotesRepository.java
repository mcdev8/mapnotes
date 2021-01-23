package org.launchcode.devops.mapnotesapi.repositories;

import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Long> {
}
