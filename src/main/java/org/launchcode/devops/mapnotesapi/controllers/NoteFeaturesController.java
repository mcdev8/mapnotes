package org.launchcode.devops.mapnotesapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.launchcode.devops.mapnotesapi.models.Feature.Feature;
import org.launchcode.devops.mapnotesapi.models.Feature.FeatureCollection;
import org.launchcode.devops.mapnotesapi.models.Feature.NoteFeatureEntity;
import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;
import org.launchcode.devops.mapnotesapi.repositories.FeaturesRepository;
import org.launchcode.devops.mapnotesapi.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class NoteFeaturesController {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    FeaturesRepository featuresRepository;
    
    @GetMapping("/notes/{id}/features")
    public ResponseEntity<Object> getNoteFeatures(@PathVariable Long id) {
        // Return 404 if note does not exist:
        if(!notesRepository.existsById(id)) { return ResponseEntity.status(404).build(); }
        // get the note entity from the repository:
        NoteEntity noteEntity = notesRepository.findById(id).get();
        // Convert collection to DTO:
        FeatureCollection featureCollectionDto = FeatureCollection.fromNote(noteEntity);
        return ResponseEntity.status(200).body(featureCollectionDto);
    }

    @PutMapping("/notes/{id}/features")
    // public ResponseEntity<Object> putNoteFeatures(@PathVariable Long id) {
    public ResponseEntity<Object> putNoteFeatures(@PathVariable Long id, @RequestBody FeatureCollection features) {
        // Return 404 if note does not exist:
        if(!notesRepository.existsById(id)) { return ResponseEntity.status(404).build(); }
        // get the note entity from the repository:
        NoteEntity noteEntity = notesRepository.findById(id).get();
        // convert features DTOs to feature entities (could move logic to models):
        List<NoteFeatureEntity> noteFeatureEntityList = new ArrayList();
        for(Feature feature : features.getFeatures()) {
            noteFeatureEntityList.add(feature.toNoteFeatureEntity());
        }
        // set the features:
        noteEntity.setFeatures(noteFeatureEntityList);
        // save changes to DB:
        notesRepository.save(noteEntity);
        // return status code (no body):
        return ResponseEntity.status(201).build();
    }

}