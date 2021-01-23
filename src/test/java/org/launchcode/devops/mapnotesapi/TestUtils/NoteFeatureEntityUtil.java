package org.launchcode.devops.mapnotesapi.TestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

import org.launchcode.devops.mapnotesapi.models.Feature.Feature;
import org.launchcode.devops.mapnotesapi.models.Feature.FeatureCollection;
import org.launchcode.devops.mapnotesapi.models.Feature.NoteFeatureEntity;

public class NoteFeatureEntityUtil {

    private static List<Feature> getTestFeatures(){
        return NoteFeatureEntityUtil.getTestNoteFeatureEntities().stream().map(Feature::fromNoteFeatureEntity)
        .collect(Collectors.toList());
    }

    public static FeatureCollection getTestFeatureCollection(){
        return new FeatureCollection(NoteFeatureEntityUtil.getTestFeatures());
    }

    public static List<NoteFeatureEntity> getTestNoteFeatureEntities() {
        List<NoteFeatureEntity> noteFeatureEntities = new ArrayList<>();
        Coordinate[] coordinatesArray = {
            new Coordinate(-9438256.383064654,2648494.7591272676),
            new Coordinate(-9040547.558033329,2728036.524133533),
            new Coordinate(-8265015.34922224,2409869.4641084713),
            new Coordinate(-8205359.025467541,2220957.772218591),
            new Coordinate(-8642838.733002001,2151358.727838109),
            new Coordinate(-8961005.793027062,2320384.978476423),
            new Coordinate(-9398485.500561522,2439697.625985821),
            new Coordinate(-9438256.383064654,2648494.7591272676)
        };
        GeometryFactory geometryFactory = new GeometryFactory();
        noteFeatureEntities.add(new NoteFeatureEntity(geometryFactory.createPolygon(coordinatesArray)));
        return noteFeatureEntities;
    }
}
