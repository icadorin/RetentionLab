package io.retentionlab.core.domain.analysis.opening;

import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalAnalyzer;
import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalScore;
import io.retentionlab.core.domain.model.ScriptStructure;
import io.retentionlab.core.domain.model.segmentation.StructuralSegmentationService;

public class OpeningAnalysisService {

    private final OpeningHookAnalyzer hookAnalyzer;
    private final OpeningSignalAnalyzer signalAnalyzer;

    public OpeningAnalysisService(
        OpeningHookAnalyzer hookAnalyzer,
        OpeningSignalAnalyzer signalAnalyzer
    ) {
        this.hookAnalyzer = hookAnalyzer;
        this.signalAnalyzer = signalAnalyzer;
    }

    public OpeningAnalysisResult analyze(String text, ScriptStructure structure) {

        if (text == null || text.isBlank()) {
            return new OpeningAnalysisResult(
                OpeningHookScore.ZERO,
                OpeningSignalScore.ZERO,
                OpeningScoreAggregate.ZERO
            );
        }

        OpeningHookScore hookScore =
            hookAnalyzer.analyze(structure);

        OpeningSignalScore signalScore =
            signalAnalyzer.analyze(text);

        OpeningScoreAggregate aggregate =
            OpeningScoreAggregate.evaluate(hookScore, signalScore);

        return new OpeningAnalysisResult(
            hookScore,
            signalScore,
            aggregate
        );
    }
}