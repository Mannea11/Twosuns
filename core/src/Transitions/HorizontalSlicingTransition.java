package Transitions;

import javax.annotation.Nullable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

import de.damios.guacamole.Preconditions;
import de.eskalon.commons.screen.transition.BatchTransition;

/**
 * A transition where the new screen is sliding in in horizontal slices.
 *
 * @since 0.3.0
 * @author damios
 */
public class HorizontalSlicingTransition extends BatchTransition {

    private int sliceCount;

    /**
     * @param batch
     *            the sprite batch used to render
     * @param sliceCount
     *            the count of slices used; has to be at least {@code 2}
     * @param duration
     *            the duration (in seconds) over which the transition should
     *            happen
     * @param interpolation
     *            the interpolation used
     */
    public HorizontalSlicingTransition(SpriteBatch batch, int sliceCount,
                                       float duration, @Nullable Interpolation interpolation) {
        super(batch, duration, interpolation);
        Preconditions.checkArgument(sliceCount >= 2,
                "The slice count has to be at least 2");

        this.sliceCount = sliceCount;
    }

    /**
     * @param batch
     *            the sprite batch used to render
     * @param sliceCount
     *            the count of slices used; has to be at least {@code 2}
     * @param duration
     *            the duration (in seconds) over which the transition should
     *            happen
     */
    public HorizontalSlicingTransition(SpriteBatch batch, int sliceCount,
                                       float duration) {
        this(batch, sliceCount, duration, null);
    }

    @Override
    public void render(float delta, TextureRegion lastScreen,
                       TextureRegion currScreen, float progress) {
        batch.begin();

        batch.draw(lastScreen, 0, 0, width, height);

        int sliceHeight = MathUtils.ceil(height / (float) sliceCount);

        for (int i = 0; i < sliceCount; i++) {
            int y = i * sliceHeight;

            int offsetX = 0;

            if (i % 2 == 0) {
                offsetX = (int) (width * (progress - 1));
            } else {
                offsetX = (int) (width * (1 - progress));
            }

            batch.draw(currScreen.getTexture(), offsetX, y, width, sliceHeight,
                    0, HdpiUtils.toBackBufferY(y),
                    HdpiUtils.toBackBufferX(width),
                    HdpiUtils.toBackBufferY(sliceHeight), false, true);
        }

        batch.end();
    }

}