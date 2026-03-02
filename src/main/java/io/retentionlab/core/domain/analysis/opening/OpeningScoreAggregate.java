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

        // Hook contribui com 40% do score final da abertura
        // densityScore já está em 0–1 → converte para 0–100 antes de aplicar peso
        double hookComponent =
            hookScore.densityScore() * 100 * 0.4;

        // Signals contribuem com 60%
        // normalizedScore também está em 0–1
        double signalComponent =
            signalScore.normalizedScore() * 100 * 0.6;

        // Soma ponderada final da abertura (0–100)
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