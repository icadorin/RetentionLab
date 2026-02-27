package io.retentionlab.core.analysis.opening.signal;

import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalAnalyzer;
import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpeningSignalAnalyzerTest {

    private final OpeningSignalAnalyzer analyzer =
        new OpeningSignalAnalyzer();

    @Test
    void givenTextWithConflict_whenAnalyze_thenDetectConflictSignal() {

        String text = "Eu cometi um erro enorme no meu projeto.";

        OpeningSignalScore score = analyzer.analyze(text);

        assertEquals(1, score.conflictSignals());
        assertEquals(0, score.curiositySignals());
        assertEquals(0, score.promiseSignals());
    }

    @Test
    void givenTextWithQuestion_whenAnalyze_thenDetectCuriositySignal() {

        String text = "Você sabe por que isso acontece?";

        OpeningSignalScore score = analyzer.analyze(text);

        assertEquals(1, score.curiositySignals());
    }

    @Test
    void givenTextWithPromise_whenAnalyze_thenDetectPromiseSignal() {

        String text = "Hoje eu vou te mostrar algo incrível.";

        OpeningSignalScore score = analyzer.analyze(text);

        assertEquals(1, score.promiseSignals());
    }

    @Test
    void givenEmptyText_whenAnalyze_thenReturnZeroScore() {

        OpeningSignalScore score = analyzer.analyze("");

        assertEquals(0.0, score.normalizedScore());
    }
}