package org.launchcode.devops.mapnotesapi.models.Feature.utils;

import java.io.IOException;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import org.launchcode.devops.mapnotesapi.models.Feature.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;

// This class is complete and already utilized where necessary in your code

public class FeatureCollectionSerializer extends StdSerializer<FeatureCollection> {
    
    public FeatureCollectionSerializer() {
        this(null);
    }

    protected FeatureCollectionSerializer(Class<FeatureCollection> fc) {
        super(fc);
    }

    @Autowired
    private GeometrySerializer geometrySerializer = new GeometrySerializer();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void serialize(FeatureCollection featureCollection, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", featureCollection.getType());
        jsonGenerator.writeObjectField("features", featureCollection.getFeatures());
        jsonGenerator.writeEndObject();
    }

    public void setGeometrySerializer(GeometrySerializer geometrySerializer) {
        this.geometrySerializer = geometrySerializer;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
