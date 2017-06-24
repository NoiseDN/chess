package com.noise.chess.serialiser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.noise.chess.core.Coordinates;

import java.io.IOException;

public class CoordinatesSerialiser extends JsonSerializer<Coordinates> {

    @Override
    public void serialize(Coordinates coordinates, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        int[] array = new int[2];
        array[0] = coordinates.getX().ordinal();
        array[1] = coordinates.getY().ordinal();

        jsonGenerator.writeArray(array, 0, 2);
    }
}
