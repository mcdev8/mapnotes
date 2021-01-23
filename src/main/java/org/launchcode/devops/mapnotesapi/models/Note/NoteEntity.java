package org.launchcode.devops.mapnotesapi.models.Note;

import org.launchcode.devops.mapnotesapi.models.Feature.NoteFeatureEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// you will need to complete this entity.
// outside of the provided body property what else does a Note Entity need?

@Entity
@Table(name = "notes")
public class NoteEntity {
  // F I E L D S :
  @Id
  @GeneratedValue // using default generated type
  private long id;

  private String title;
  
  @Lob
  private String body;

  @OneToMany(mappedBy = "noteEntity", cascade = CascadeType.ALL)
  private List<NoteFeatureEntity> features = new ArrayList<>(); 

  // C O N S T R U C T O R S :
  public NoteEntity() {  
  }
  public NoteEntity(String title, String body) {
    this.title = title;
    this.body = body;
  }

  // G E T T E R S :
  public long getId(){
    return this.id;
  }
  public String getTitle() {
    return this.title;
  }
  public String getBody(){
    return this.body;
  }
  public List<NoteFeatureEntity> getFeatures(){
    return this.features;
  }

  // S E T T E R S :
  public void setId(long id){
    this.id = id;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setBody(String body) {
    this.body = body;
  }
  // PUT overwrites existing data (Post adds to existing data):
  public void setFeatures(List<NoteFeatureEntity> features) {
    this.addFeatures(features);
  }

  // Standard approach:
  // This is a helper method for syncronizing the bi-driectional relationship.
  // bi-drectional meaning that you can access the note entity from the feature entity
  // and the access the feature entity from the note entity.
  // The logic is split between both entities. 
  // If you wanted uno-directional, you would need all of the logic in one of 
  // the entities (likely the parent).
  // It needs to be syncronized because each element needs to be tied together.
  // If you don't add A to B & also B to A then either A or B will break because
  // it doesn't know where it belongs.
  public void addFeature(NoteFeatureEntity feature) {
    this.features.add(feature);
    feature.setNoteEntity(this); 
  }

  // This is part of the standard approach mentioned above for syncronizing
  // the bi-drectional relationship:
  public void addFeatures(List<NoteFeatureEntity> features) {
    for(NoteFeatureEntity feature : features) {
      this.addFeature(feature);
    }
  }

}
