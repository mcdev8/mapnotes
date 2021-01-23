package org.launchcode.devops.mapnotesapi.NotesController;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.devops.mapnotesapi.TestUtils.IntegrationTestConfig;
import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;
import org.launchcode.devops.mapnotesapi.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@IntegrationTestConfig
public class DeleteNoteTests {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  NotesRepository notesRepository;

  @Test
  @DisplayName("[empty state] DELETE /notes/{noteId}: 404 status")
  public void deleteNoteEmpty() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/notes/-1"))
    .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  @DisplayName("[populated state] DELETE /notes/{noteId}: 204 status")
  public void deleteNotePopulated() throws Exception {
    // new note:
    NoteEntity testNote = new NoteEntity("Test Title", "Test Body");
    // save to repo:
    notesRepository.save(testNote);
    // get id of note
    long id = testNote.getId();
    // check for note in repositoy:
    assertTrue(notesRepository.existsById(id));
    // make mock request to Delete /notes/{id} endpoint:
    mockMvc.perform(MockMvcRequestBuilders.delete("/notes/" + id))
    // assert that a 204 http status code was returned:
    .andExpect(MockMvcResultMatchers.status().isNoContent());
    // assert that the note was infact deleted:
    assertFalse(notesRepository.existsById(id));
  }
}
