package io.retentionlab.core.domain.analysis.opening;

import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalScore;

public class OpeningScoreAggregate {

    private static final int IDEAL_WORD_COUNT = 75;

    private final double finalScore;
    private final double signalDensity;
    private final double structuralDensity;

    private OpeningScoreAggregate(
        double finalScore,
        double signalDensity,
        double structuralDensity
    ) {
        this.finalScore = finalScore;
        this.signalDensity = signalDensity;
        this.structuralDensity = structuralDensity;
    }

    public static OpeningScoreAggregate evaluate(
        int wordCount,
        OpeningSignalScore signalScore
    ) {

        if (wordCount <= 0) {
            return new OpeningScoreAggregate(0, 0, 0);
        }

        double signalDensity = ((double) signalScore.totalSignals() / wordCount) * 100;

        double structuralDensity = Math.min(
            1.0,
            (double) IDEAL_WORD_COUNT / wordCount
        );

        double finalScore =
            (signalDensity * 0.6) +
                (structuralDensity * 100 * 0.4);

        return new OpeningScoreAggregate(
            round(finalScore),
            round(signalDensity),
            round(structuralDensity * 100)
        );
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public double finalScore() {
        return finalScore;
    }

    public double signalDensity() {
        return signalDensity;
    }

    public double structuralDensity() {
        return structuralDensity;
    }
}