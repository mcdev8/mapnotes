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
public class GetNotesTests {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  NotesRepository notesRepository;

  @Test
  @DisplayName("[empty state] GET /notes: an empty JSON list")
  public void getNotesEmpty() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/notes"))
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
  }

  @Test
  @DisplayName("[populated state] GET /notes: a JSON list of Note entities")
  public void getNotesPopulated() throws Exception {
    // new note
    NoteEntity testNote = new NoteEntity("Test Title", "Test Body");
    // save to repo
    notesRepository.save(testNote);
    // check for note in response 
    mockMvc.perform(MockMvcRequestBuilders.get("/notes"))
    .andExpect(MockMvcResultMatchers.jsonPath("[0].title").value(testNote.getTitle()))
    .andExpect(MockMvcResultMatchers.jsonPath("[0].body").value(testNote.getBody()))
    .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(testNote.getId()))
    .andExpect(MockMvcResultMatchers.jsonPath("[0].features").doesNotExist()) // Should not contain features
    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
    .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
