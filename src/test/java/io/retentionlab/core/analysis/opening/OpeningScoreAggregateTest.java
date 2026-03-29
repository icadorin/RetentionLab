package io.retentionlab.core.analysis.opening;

import io.retentionlab.core.domain.analysis.opening.OpeningHookScore;
import io.retentionlab.core.domain.analysis.opening.OpeningScoreAggregate;
import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpeningScoreAggregateTest {

    @Test
    void shouldCalculateWeightedFinalScore() {

        OpeningHookScore hookScore =
            new OpeningHookScore(1.0, 75, 75); // 100% estrutura

        OpeningSignalScore signalScore =
            new OpeningSignalScore(1, 1, 1, 0.5); // 50% sinais

        OpeningScoreAggregate result =
            OpeningScoreAggregate.evaluate(hookScore, signalScore);

        assertEquals(0.7, result.finalScore());
    }

    @Test
    void shouldReturnZeroWhenBothScoresAreZero() {

        OpeningHookScore hookScore =
            new OpeningHookScore(0.0, 0, 75);

        OpeningSignalScore signalScore =
            new OpeningSignalScore(0, 0, 0, 0.0);

        OpeningScoreAggregate result =
            OpeningScoreAggregate.evaluate(hookScore, signalScore);

        assertEquals(0.0, result.finalScore());
    }
}