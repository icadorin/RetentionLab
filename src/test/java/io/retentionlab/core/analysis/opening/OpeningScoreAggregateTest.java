package io.retentionlab.core.analysis.opening;

import io.retentionlab.core.domain.analysis.opening.OpeningScoreAggregate;
import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalScore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpeningScoreAggregateTest {

    @Test
    void shouldCalculateBalancedOpeningScore() {

        OpeningSignalScore signalScore =
            new OpeningSignalScore(1, 1, 1, 0.5);

        OpeningScoreAggregate result =
            OpeningScoreAggregate.evaluate(75, signalScore);

        assertTrue(result.finalScore() > 0);
        assertEquals(100.0, result.structuralDensity());
    }

    @Test
    void shouldReturnZeroWhenWordCountIsZero() {

        OpeningSignalScore signalScore =
            new OpeningSignalScore(1, 1, 1, 0.5);

        OpeningScoreAggregate result =
            OpeningScoreAggregate.evaluate(0, signalScore);

        assertEquals(0, result.finalScore());
    }
}