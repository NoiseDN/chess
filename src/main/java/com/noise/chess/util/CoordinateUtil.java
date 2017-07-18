package com.noise.chess.util;

import com.noise.chess.domain.Coordinates;

public class CoordinateUtil {
    public static String toChessFormat(String string) {
        String[] digits = string.split(",");
        Coordinates.X x = Coordinates.X.of(Integer.parseInt(digits[0].trim()));
        Coordinates.Y y = Coordinates.Y.of(Integer.parseInt(digits[1].trim()));

        return x.getText() + "-" + y.getText();
    }

    public static String toChessFormat(Coordinates coordinates) {
        return toChessFormat(coordinates.toString());
    }
}
