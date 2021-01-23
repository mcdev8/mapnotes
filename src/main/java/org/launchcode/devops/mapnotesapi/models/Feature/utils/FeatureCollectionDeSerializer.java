package org.launchcode.devops.mapnotesapi.models.Feature.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Geometry;

import org.launchcode.devops.mapnotesapi.models.Feature.Feature;
import org.launchcode.devops.mapnotesapi.models.Feature.FeatureCollection;

// This class is complete and already utilized where necessary in your code

public class FeatureCollectionDeSerializer extends JsonDeserializer<FeatureCollection> {
    
    public FeatureCollectionDeSerializer() {}

    @Override
    public FeatureCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode root = objectCodec.readTree(jsonParser); // get the JSON
        List<Feature> features = new ArrayList<>(); // return the feature list
        JsonNode nodeFeatures = root.findValue("features"); // similar to jsonPath("$.features")
        for(JsonNode nodeFeature : nodeFeatures) { // for each feature in the incoming JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JtsModule());
            JsonNode featureGeometry = nodeFeature.findValue("geometry"); // get the JSON rep of the feature
            Geometry featureGeom = mapper.treeToValue(featureGeometry, Geometry.class); // convert the JSON to a Geometry object
            Feature newFeature = new Feature(featureGeom); // create a new feature
            features.add(newFeature); // add the new feature to the feature list
        }

        return new FeatureCollection(features); // return the feature list
        
    }

}
