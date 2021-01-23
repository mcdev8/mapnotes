package org.launchcode.devops.mapnotesapi.NoteFeaturesController;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.devops.mapnotesapi.TestUtils.IntegrationTestConfig;
import org.launchcode.devops.mapnotesapi.TestUtils.NoteFeatureEntityUtil;
import org.launchcode.devops.mapnotesapi.models.Feature.NoteFeatureEntity;
import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;
import org.launchcode.devops.mapnotesapi.repositories.FeaturesRepository;
import org.launchcode.devops.mapnotesapi.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@IntegrationTestConfig
public class GetNoteFeaturesTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FeaturesRepository featuresRepository;

    @Autowired
    NotesRepository notesRepository;

    @Test
    @DisplayName("[empty state] GET /notes/{noteId}/features: 404 status")
    public void getNonNoteFeatures() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/notes/-1/features"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("[empty state] GET /notes/{noteId}/features: empty Feature Collection")
    public void getNoteFeaturesEmpty() throws Exception {
        // create note:
        NoteEntity testNote = new NoteEntity("Test Title", "Test Body");
        // save to repo:
        notesRepository.save(testNote);
        // get the id:
        long id = testNote.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/notes/" + id + "/features"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("FeatureCollection"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.features").isEmpty());
    }

    @Test
    @DisplayName("[FeatureCollection] GET /notes/{noteId}/features: Feature Collection with 1 feature")
    public void getNoteFeaturesPopulated() throws Exception {
        // creates a pre-populated list of valid NoteFeatureEntities for testing purposes
        List<NoteFeatureEntity> noteFeatureEntities = NoteFeatureEntityUtil.getTestNoteFeatureEntities();
        // create note:
        NoteEntity testNote = new NoteEntity("Test Title", "Test Body");
        // Add the features to the note:
        testNote.setFeatures(noteFeatureEntities);
        // save note with features to repo:
        notesRepository.save(testNote);
        // get the id:
        long id = testNote.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/notes/" + id + "/features"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("FeatureCollection"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.features[0].id").isNumber())
        .andExpect(MockMvcResultMatchers.jsonPath("$.features[0].geometry").isMap())
        .andExpect(MockMvcResultMatchers.jsonPath("$.features[0].geometry.coordinates").isArray());
    }
}

	