package org.launchcode.devops.mapnotesapi.models.Feature;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;


public class Feature {
  private long id;
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  private Geometry geometry;
  private final String type = "Feature";

  public Feature() {}

  public Feature(Geometry geometry) {
    this.geometry = geometry;
  }

  public Feature(Long id, Geometry geometry) {
    this.id = id;
    this.geometry = geometry;
  }

  public static Feature fromNoteFeatureEntity(NoteFeatureEntity noteFeatureEntity) {
    return new Feature(noteFeatureEntity.getId(), noteFeatureEntity.getGeometry());
  }

  public NoteFeatureEntity toNoteFeatureEntity() {
    return new NoteFeatureEntity(this.geometry);
  }

  public Geometry getGeometry() {
    return this.geometry;
  }
  public Long getId(){
    return this.id;
  }
  public String getType() {
    return this.type;
  }

}
