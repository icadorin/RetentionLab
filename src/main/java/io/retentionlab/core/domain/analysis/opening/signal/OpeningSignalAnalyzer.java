package io.retentionlab.core.domain.analysis.opening.signal;

import java.util.List;

public class OpeningSignalAnalyzer {

    private static final List<String> CONFLICT_KEYWORDS = List.of(
        "erro", "problema", "falha", "perdi", "fracasso"
    );

    private static final List<String> PROMISE_KEYWORDS = List.of(
        "vou te mostrar", "você vai aprender", "vou revelar", "você vai descobrir"
    );

    public OpeningSignalScore analyze(String openingText) {

        if (openingText == null || openingText.isBlank()) {
            return new OpeningSignalScore(0, 0, 0, 0.0);
        }

        String lowerText = openingText.toLowerCase();

        int conflictCount = countOccurrences(lowerText, CONFLICT_KEYWORDS);
        int promiseCount = countOccurrences(lowerText, PROMISE_KEYWORDS);
        int curiosityCount = countCuriositySignals(lowerText);

        int totalSignals = conflictCount + promiseCount + curiosityCount;
        int maxSignals = 5;

        double normalized = Math.min(totalSignals / (double) maxSignals, 1.0);

        return new OpeningSignalScore(
            conflictCount,
            curiosityCount,
            promiseCount,
            normalized
        );
    }

    private int countOccurrences(String text, List<String> keywords) {
        int count = 0;
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                count++;
            }
        }
        return count;
    }

    private int countCuriositySignals(String text) {
        return text.contains("?") ? 1 : 0;
    }
}