package io.retentionlab.core.domain.analysis.tension;

import io.retentionlab.core.domain.model.ScriptStructure;
import java.util.List;

public class TensionAnalyzer {

    private static final double IDEAL_INCREASING_RATIO = 0.70;
    private static final double PEAK_THRESHOLD = 0.8;

    private final TensionExtractor tensionExtractor;

    public TensionAnalyzer(TensionExtractor tensionExtractor) {
        this.tensionExtractor = tensionExtractor;
    }

    public TensionResult analyze(ScriptStructure structure, String fullText) {
        List<Double> tensionValues = tensionExtractor.extractTension(structure, fullText);

        if (tensionValues.size() < 3) {
            return TensionResult.INSUFFICIENT_DATA;
        }

        double increasingRatio = calculateIncreasingRatio(tensionValues);
        boolean hasPeak = tensionValues.stream().anyMatch(v -> v >= PEAK_THRESHOLD);
        double variance = calculateVariance(tensionValues);

        // Score baseado na spec (peso 15)
        // Parte 1 – Progressão crescente (40% do peso de tensão)
        double increasingScore = (increasingRatio / IDEAL_INCREASING_RATIO) * 15 * 0.4;
        // Parte 2 – Existência de pico narrativo (30%)
        double peakScore = hasPeak ? 15 * 0.3 : 0;
        // Parte 3 – Variância saudável (30%)
        double varianceScore = calculateVarianceScore(variance) * 15 * 0.3;

        // Soma das três dimensões
        double weightedScore = increasingScore + peakScore + varianceScore;

        // Normaliza para 0–1 dividindo pelo peso máximo (15)
        double normalizedScore = Math.min(weightedScore / 15.0, 1.0);

        return new TensionResult(
            tensionValues,
            increasingRatio,
            hasPeak,
            variance,
            normalizedScore
        );
    }

    // Calcula a proporção de vezes em que a tensão aumenta
    // Ex: 4 aumentos em 6 transições → 0.66
    private double calculateIncreasingRatio(List<Double> values) {
        int increases = 0;
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i + 1) > values.get(i)) {
                increases++;
            }
        }
        return (double) increases / (values.size() - 1);
    }

    // Variância mede o quanto a tensão oscila.
    // Muito baixa → narrativa flat.
    // Muito alta → narrativa caótica.
    // Ideal → variação controlada.
    private double calculateVariance(List<Double> values) {
        double mean = values.stream().mapToDouble(v -> v).average().orElse(0.0);
        return values.stream()
            .mapToDouble(v -> Math.pow(v - mean, 2))
            .average()
            .orElse(0.0);
    }

    // Converte variância em score normalizado (0–1)
    // < 0.05  → tensão muito estável (ruim)
    // 0.08–0.12 → zona ideal
    // > 0.25 → oscilação excessiva
    private double calculateVarianceScore(double variance) {
        if (variance < 0.05) return 0.0;
        if (variance > 0.25) return 0.5;
        if (variance >= 0.08 && variance <= 0.12) return 1.0; // ideal
        return 0.7;
    }
}