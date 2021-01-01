package pl.jakubsolecki.model;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
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
    
    public Vector2D translateToBoard(int width, int height) {
        int newX = this.X;
        int newY = this.Y;

        if (this.X < 0) {
            newX = width - 1;
        } else if (this.X > width - 1) {
            newX = 0;
        }

        if (this.Y < 0) {
            newY = height - 1;
        } else if (this.Y > height - 1) {
            newY = 0;
        }

        return new Vector2D(newX, newY);
    }
}
