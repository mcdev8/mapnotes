package org.launchcode.devops.mapnotesapi.controllers;

import java.util.List;
import java.util.Optional;

import org.launchcode.devops.mapnotesapi.models.Note.InboundNoteRepresentation;
import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;
import org.launchcode.devops.mapnotesapi.models.Note.OutboundNoteRepresentation;
import org.launchcode.devops.mapnotesapi.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/notes")
public class NotesController {

  @Autowired
  NotesRepository notesRepository;

  @GetMapping
  public ResponseEntity<Object> getNotes() {
    // curl -X GET http://localhost:8080/notes
    List<NoteEntity> notes = notesRepository.findAll();
    return ResponseEntity.status(200).body(
      OutboundNoteRepresentation.buildDtosFromNoteEntityList(notes)
    );
  }

  @PostMapping
  public ResponseEntity<Object> createNote(@RequestBody InboundNoteRepresentation noteDto) {
    // curl -X POST http://localhost:8080/notes -H 'Content-type: application/json' -d '{"title":"This is a title", "body":"This is a body"}'
    NoteEntity newNote = noteDto.toNoteEntity();
    notesRepository.save(newNote);
    return ResponseEntity.status(201).body(noteDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OutboundNoteRepresentation> getNote(@PathVariable long id) {
    // curl -X GET http://localhost:8080/notes/1
    Optional<NoteEntity> optionalNote = notesRepository.findById(id);
    // If note does not exist, return a 404:
    if (optionalNote.isEmpty()) { return ResponseEntity.status(404).build(); }
    // If note exists, get the contents of the note:
    NoteEntity note = optionalNote.get();
    // Return a new outbound note DTO with a 200 status code:
    return ResponseEntity.status(200).body(
      new OutboundNoteRepresentation(
        note.getId(),
        note.getTitle(),
        note.getBody()
      )
    );
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteNote(@PathVariable long id) {
    // curl -X DELETE http://localhost:8080/notes
    Optional<NoteEntity> optionalNote = notesRepository.findById(id);
    // If note does not exist, return a 404:
    if (optionalNote.isEmpty()) { return ResponseEntity.status(404).build(); }
    // If note exists, delete the note:
    notesRepository.deleteById(id);
    // Return a 204 status code:
    return ResponseEntity.status(204).build();
  }
}