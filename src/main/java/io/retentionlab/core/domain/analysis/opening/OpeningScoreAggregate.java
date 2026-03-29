package io.retentionlab.core.domain.analysis.opening;

import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalScore;

public class OpeningScoreAggregate {

    private final double finalScore;

    private OpeningScoreAggregate(double finalScore) {
        this.finalScore = finalScore;
    }

    public static OpeningScoreAggregate evaluate(
        OpeningHookScore hookScore,
        OpeningSignalScore signalScore
    ) {

        // Hook 40%
        double hookComponent = hookScore.densityScore() * 0.4;

        // Signals 60%
        double signalComponent = signalScore.normalizedScore() * 0.6;

        double finalScore = hookComponent + signalComponent;

        return new OpeningScoreAggregate(round(finalScore));
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static final OpeningScoreAggregate ZERO =
        new OpeningScoreAggregate(0.0);

    public double finalScore() {
        return finalScore;
    }
}