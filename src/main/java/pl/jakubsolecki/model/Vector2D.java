package pl.jakubsolecki.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class Vector2D {

    public final int X;
    public final int Y;

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.X + other.X, this.Y + other.Y);
    }

    public Vector2D upperRight(Vector2D other) {
        int x = Math.max(this.X, other.X);
        int y = Math.max(this.Y, other.Y);

        return new Vector2D(x, y);
    }

    public Vector2D lowerLeft(Vector2D other) {
        int x = Math.min(this.X, other.X);
        int y = Math.min(this.Y, other.Y);

        return new Vector2D(x, y);
    }

}
