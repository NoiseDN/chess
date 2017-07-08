package com.noise.chess.serialiser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.noise.chess.domain.Coordinates;

import java.io.IOException;

public class CoordinatesDeserialiser extends JsonDeserializer<Coordinates> {

    @Override
    public Coordinates deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.readValueAsTree();

        if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;

            Integer x = arrayNode.get(0).asInt();
            Integer y = arrayNode.get(1).asInt();

            return Coordinates.of(Coordinates.X.of(x), Coordinates.Y.of(y));
        }

        throw new JsonParseException(jsonParser, "Could not parse array of 'coordinates'");
    }
}
