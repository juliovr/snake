package cl.julio.snake;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PositionTest {

    private static final Position position1 = new Position(0, 0);
    private static final Position position2 = new Position(0, 0);

    @Test
    public void testEqualsMethod() {
        assertThat(position1.equals(position2), is(true));
    }

    @Test
    public void testHashCodeMethod() {
        assertThat(position1.hashCode() == position2.hashCode(), is(true));
    }

}
