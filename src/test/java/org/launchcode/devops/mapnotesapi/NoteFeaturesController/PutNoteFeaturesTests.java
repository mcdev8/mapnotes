package org.launchcode.devops.mapnotesapi.NoteFeaturesController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.devops.mapnotesapi.TestUtils.IntegrationTestConfig;
import org.launchcode.devops.mapnotesapi.TestUtils.NoteFeatureEntityUtil;
import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;
import org.launchcode.devops.mapnotesapi.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
@IntegrationTestConfig
public class PutNoteFeaturesTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    NotesRepository notesRepository;

    @Test
    @DisplayName("PUT /notes/{noteId}/features: 404")
    public void putNonNoteFeatures() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/notes/-1/features")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(NoteFeatureEntityUtil.getTestFeatureCollection())))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("PUT /notes/{noteId}/features: 201")
    public void putNoteFeatures() throws Exception {
        // create note:
        NoteEntity testNote = new NoteEntity("Test Title", "Test Body");
        // save note with features to repo:
        notesRepository.save(testNote);
        // confirm no features:
        assertEquals(0, testNote.getFeatures().size());
        // get the id:
        long id = testNote.getId();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/notes/" + id + "/features")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(NoteFeatureEntityUtil.getTestFeatureCollection())))
        .andExpect(MockMvcResultMatchers.status().isCreated());
        // confirm one feature:
        assertEquals(1, testNote.getFeatures().size());

    }
}
