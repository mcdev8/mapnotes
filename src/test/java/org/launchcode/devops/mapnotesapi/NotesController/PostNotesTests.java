package org.launchcode.devops.mapnotesapi.NotesController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.devops.mapnotesapi.TestUtils.IntegrationTestConfig;
import org.launchcode.devops.mapnotesapi.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@IntegrationTestConfig
public class PostNotesTests {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  NotesRepository notesRepository;

  @Test
  @DisplayName("[valid data] POST /notes with body (NewNote JSON): 201 status and body (JSON Note entity)")
  public void createNoteValid() throws Exception {
    long numberOfNotesBeforePost = notesRepository.count();
    mockMvc.perform(MockMvcRequestBuilders.post("/notes").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"test title\",\"body\":\"test body\"}"))
    .andExpect(MockMvcResultMatchers.status().isCreated())
    .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("test title"))
    .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("test body"))
    .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
    .andExpect(MockMvcResultMatchers.jsonPath("$.features").doesNotExist());
    // Check for new note in repository:
    assertEquals(numberOfNotesBeforePost, notesRepository.count(), 1);
  }

  // BONUS:
  // @Test
  // @DisplayName("[invalid data] POST /notes (NewNote JSON): 400 status and body (JSON field errors)")
  // public void createNoteInvalid() throws Exception {
  //   mockMvc.perform(MockMvcRequestBuilders.post("/notes/1").contentType(MediaType.APPLICATION_JSON).content("{}"))
  //   .andExpect(MockMvcResultMatchers.status().isBadRequest());
  //   // TODO: This should fail until additional logic is added to the controller
  // }
}
