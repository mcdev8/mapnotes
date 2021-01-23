package org.launchcode.devops.mapnotesapi.models.Feature;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

import org.launchcode.devops.mapnotesapi.models.Note.NoteEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "note_features")
public class NoteFeatureEntity {
    @Id
    @GeneratedValue
    private long id;

    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private Geometry geometry;

    private final String type = "Feature";

    @ManyToOne
    @JoinColumn(name="note_id", nullable = false)
    private NoteEntity noteEntity;
    
    public NoteFeatureEntity() {}

    public NoteFeatureEntity(Geometry geometry) {
        this.geometry = geometry;
    }

    public long getId(){
        return this.id;
    }

    public Geometry getGeometry(){
        return this.geometry;
    }

    public String getType(){
        return this.type;
    }

    public NoteEntity getNoteEntity(){
        return this.noteEntity;
    }


}
