package thebetweenlands.client.model;

import net.minecraft.util.Mth;

/**
 * This is a timer that can be used to easily animate models between poses. You
 * have to set the number of ticks between poses, increase or decrease the
 * timer, and get the percentage using a specific function.
 *
 * @author RafaMv
 */
public class ControlledAnimation {
    /**
     * It is the timer used to animate
     */
    private int prevTimer;
    private int timer;

    /**
     * It is the limit time, the maximum value that the timer can be. I
     * represents the duration of the animation
     */
    private int duration;

    private int timerChange;

    public ControlledAnimation(int d) {
		this.prevTimer = 0;
		this.timer = 0;
		this.duration = d;
    }

    /**
     * Sets the duration of the animation in ticks. Try values around 50.
     *
     * @param duration is the maximum number of ticks that the timer can reach.
     */
    public void setDuration(int duration) {
		this.prevTimer = 0;
		this.timer = 0;
        this.duration = duration;
    }

    /**
     * Returns the timer of this animation. Useful to save the progress of the
     * animation.
     */
    public int getTimer() {
        return this.timer;
    }

    /**
     * Sets the timer to a specific value.
     *
     * @param time is the number of ticks to be set.
     */
    public void setTimer(int time) {
		this.timer = time;

        if (this.timer > this.duration) {
			this.timer = this.duration;
        } else if (this.timer < 0) {
			this.timer = 0;
        }
    }

    /**
     * Sets the timer to 0.
     */
    public void resetTimer() {
		this.timer = 0;
    }

    public void updateTimer() {
		this.prevTimer = this.timer;
    }

    /**
     * Increases the timer by 1.
     */
    public void increaseTimer() {
        if (this.timer < this.duration) {
			this.timer++;
			this.timerChange = 1;
        }
    }

    /**
     * Checks if the timer can be increased
     */
    public boolean canIncreaseTimer() {
        return this.timer < this.duration;
    }

    /**
     * Increases the timer by a specific value.
     *
     * @param time is the number of ticks to be increased in the timer
     */
    public void increaseTimer(int time) {
        int newTime = this.timer + time;
        if (newTime <= this.duration && newTime >= 0) {
			this.timer = newTime;
        } else {
			this.timer = newTime < 0 ? 0 : this.duration;
        }
    }

    /**
     * Decreases the timer by 1.
     */
    public void decreaseTimer() {
        if (this.timer > 0) {
			this.timer--;
			this.timerChange = -1;
        }
    }

    /**
     * Checks if the timer can be decreased
     */
    public boolean canDecreaseTimer() {
        return this.timer > 0;
    }

    /**
     * Decreases the timer by a specific value.
     *
     * @param time is the number of ticks to be decreased in the timer
     */
    public void decreaseTimer(int time) {
        if (this.timer - time > 0) {
			this.timer -= time;
        } else {
			this.timer = 0;
        }
    }

    /**
     * Returns a float that represents a fraction of the animation, a value
     * between 0.0F and 1.0F.
     */
    public float getAnimationFraction(float partialRenderTicks) {
        return (this.prevTimer + (this.timer - this.prevTimer) * partialRenderTicks) / this.duration;
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using 1/(1 + e^(4-8*x)). It is quite
     * uniform but slow, and needs if statements.
     */
    public float getAnimationProgressSmooth(float partialRenderTicks) {
        if (this.timer > 0) {
            if (this.timer < this.duration) {
                return (float) (1.0D / (1.0D + Math.exp(4.0D - 8.0D * this.getAnimationFraction(partialRenderTicks))));
            } else {
                return 1.0F;
            }
        }
        return 0.0F;
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using 1/(1 + e^(6-12*x)). It is quite
     * uniform, but fast.
     */
    public float getAnimationProgressSteep(float partialRenderTicks) {
        return (float) (1.0D / (1.0D + Math.exp(6.0D - 12.0D * this.getAnimationFraction(partialRenderTicks))));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using a sine function. It is fast in
     * the beginning and slow in the end.
     */
    public float getAnimationProgressSin(float partialRenderTicks) {
        return Mth.sin(1.57079632679F * this.getAnimationFraction(partialRenderTicks));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using a sine function squared. It is
     * very smooth.
     */
    public float getAnimationProgressSinSqrt(float partialRenderTicks) {
        float result = Mth.sin(1.57079632679F * this.getAnimationFraction(partialRenderTicks));
        return result * result;
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using a sine function to the power of
     * ten. It is slow in the beginning and fast in the end.
     */
    public float getAnimationProgressSinToTen(float partialRenderTicks) {
        return (float) Math.pow(Mth.sin(1.57079632679F * this.getAnimationFraction(partialRenderTicks)), 10);
    }

    public float getAnimationProgressSinToTenWithoutReturn(float partialRenderTicks) {
        float t = this.getAnimationFraction(partialRenderTicks);
        if (this.timerChange == -1) {
            return Mth.sin(1.57079632679F * t) * Mth.sin(1.57079632679F * t);
        }
        return (float) Math.pow(Mth.sin(1.57079632679F * t), 10);
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using a sine function to a specific
     * power "i."
     *
     * @param i is the power of the sine function.
     */
    public float getAnimationProgressSinPowerOf(float partialRenderTicks, int i) {
        return (float) Math.pow(Mth.sin(1.57079632679F * this.getAnimationFraction(partialRenderTicks)), i);
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using x^2 / (x^2 + (1-x)^2). It is
     * smooth.
     */
    public float getAnimationProgressPoly2(float partialRenderTicks) {
        float x = this.getAnimationFraction(partialRenderTicks);
        float x2 = x * x;
        return x2 / (x2 + (1 - x) * (1 - x));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using x^3 / (x^3 + (1-x)^3). It is
     * steep.
     */
    public float getAnimationProgressPoly3(float partialRenderTicks) {
        float x = this.getAnimationFraction(partialRenderTicks);
        float x3 = x * x * x;
        return x3 / (x3 + (1 - x) * (1 - x) * (1 - x));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using x^n / (x^n + (1-x)^n). It is
     * steeper when n increases.
     *
     * @param n is the power of the polynomial function.
     */
    public float getAnimationProgressPolyN(float partialRenderTicks, int n) {
        double x = this.getAnimationFraction(partialRenderTicks);
        double xi = Math.pow(x, n);
        return (float) (xi / (xi + Math.pow(1.0D - x, n)));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using 0.5 + arctan(PI * (x - 0.5)) /
     * 2.00776964. It is super smooth.
     */
    public float getAnimationProgressArcTan(float partialRenderTicks) {
        return (float) (0.5F + 0.49806510671F * Math.atan(3.14159265359D * (this.getAnimationFraction(partialRenderTicks) - 0.5D)));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. This value starts at 1.0F and ends at 1.0F. The
     * equation used is 0.5 - 0.5 * cos(2 * PI * x + sin(2 * PI * x)). It is
     * smooth.
     */
    public float getAnimationProgressTemporary(float partialRenderTicks) {
        float x = 6.28318530718F * this.getAnimationFraction(partialRenderTicks);
        return 0.5F - 0.5F * Mth.cos(x + Mth.sin(x));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. This value starts at 0.0F and ends at 0.0F. The
     * equation used is sin(x * PI + sin(x * PI)). It is fast in the beginning
     * and slow in the end.
     */
    public float getAnimationProgressTemporaryFS(float partialRenderTicks) {
        float x = 3.14159265359F * this.getAnimationFraction(partialRenderTicks);
        return Mth.sin(x + Mth.sin(x));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. This value starts at 1.0F and ends at 1.0F. The
     * equation used is 0.5 + 0.5 * cos(2 PI * x + sin(2 * PI * x)). It is
     * smooth.
     */
    public float getAnimationProgressTemporaryInvesed(float partialRenderTicks) {
        float x = 6.28318530718F * this.getAnimationFraction(partialRenderTicks);
        return 0.5F + 0.5F * Mth.cos(x + Mth.sin(x));
    }

	/**
	 * Sets this animation's values to be identical to the one provided.
	 * @param animation the animation to copy
	 */
	public void copyFrom(ControlledAnimation animation) {
		this.duration = animation.duration;
		this.timer = animation.timer;
		this.prevTimer = animation.prevTimer;
		this.timerChange = animation.timerChange;
	}
}
