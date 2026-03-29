package io.retentionlab.core.domain.scoring;

public class ClosureScorer {

    public double calculate(int openLoops, int closedLoops) {

        if (openLoops == 0) return 10;

        double ratio =
            clamp(closedLoops / (double) openLoops, 0, 1);

        return ratio * 10;
    }

    private double clamp(double x, double min, double max) {
        return Math.max(min, Math.min(x, max));
    }
}