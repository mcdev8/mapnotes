package org.launchcode.devops.mapnotesapi.models.Feature;

import java.util.Collection;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.launchcode.devops.mapnotesapi.models.Feature.utils.FeatureCollectionDeSerializer;
import org.launchcode.devops.mapnotesapi.models.Feature.utils.FeatureCollectionSerializer;
import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;

import lombok.Data;

@Data
@JsonSerialize(using = FeatureCollectionSerializer.class) // when serializing to JSON from a FeatureCollection object use this class
@JsonDeserialize(using = FeatureCollectionDeSerializer.class) // when deserializing from JSON to a FeatureCollection object use this class
public class FeatureCollection {
  private final String type = "FeatureCollection";
  private Collection<Feature> features;

  private FeatureCollection() {
  }


  public FeatureCollection(Collection<Feature> features) {
    this.features = features;
  }

  public static FeatureCollection fromNote(NoteEntity note) {
    Collection<Feature> features = note.getFeatures().stream().map(Feature::fromNoteFeatureEntity)
        .collect(Collectors.toList());

    return new FeatureCollection(features);
  }

  public Collection<Feature> getFeatures() {
    return this.features;
  }

  public String getType() {
    return this.type;
  }

}
