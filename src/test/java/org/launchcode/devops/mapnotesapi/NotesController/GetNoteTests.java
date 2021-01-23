package org.launchcode.devops.mapnotesapi.NotesController;

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
public class GetNoteTests {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  NotesRepository notesRepository;
  
  @Test
  @DisplayName("[empty state] GET /notes/{noteId}: 404 status")
  public void getNoteEmpty() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/notes/-1"))
    .andExpect(MockMvcResultMatchers.status().isNotFound());
    // TODO: This should fail until controller logic is added.
  }

  @Test
  @DisplayName("[populated state] GET /notes/{noteId}: the corresponding Note entity")
  public void getNotePopulated() throws Exception {
    // new note:
    NoteEntity testNote = new NoteEntity("Test Title", "Test Body");
    // save to repo:
    notesRepository.save(testNote);
    // get the id:
    long id = testNote.getId();
    // check for note in response:
    mockMvc.perform(MockMvcRequestBuilders.get("/notes/" + id))
    .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(testNote.getTitle()))
    .andExpect(MockMvcResultMatchers.jsonPath("$.body").value(testNote.getBody()))
    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testNote.getId()))
    .andExpect(MockMvcResultMatchers.jsonPath("$.features").doesNotExist()) // Should not contain features
    .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
