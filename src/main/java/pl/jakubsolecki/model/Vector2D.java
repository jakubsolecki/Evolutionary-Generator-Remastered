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

    /**
     * Checks whether method invoker is to the left and down of the other Vector2D object.
     * Uses weak inequalities.
     *
     * @param other Vector2D instance that the method owner is being compared with
     * @return true if vector belongs to the square with top-right corner in other, otherwise false
     */
    public boolean isLowerLeft(Vector2D other) {
        return this.X <= other.X && this.Y <= other.Y;
    }

    /**
     * Checks whether method invoker is to the right and up of the other Vector2D object.
     * Uses weak inequalities.
     *
     * @param other Vector2D instance that the method owner is being compared with
     * @return true if vector belongs to the square with bottom-left corner in other, otherwise false
     */
    public boolean isUpperRight(Vector2D other) {
        return this.X >= other.X && this.Y >= other.Y;
    }
}
