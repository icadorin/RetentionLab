package io.retentionlab.core.domain.analysis;

import io.retentionlab.core.domain.analysis.loop.LoopDensityAnalyzer;
import io.retentionlab.core.domain.analysis.opening.OpeningAnalysisService;
import io.retentionlab.core.domain.analysis.risk.RiskAnalyzer;
import io.retentionlab.core.domain.analysis.scoring.RetentionScoringService;
import io.retentionlab.core.domain.analysis.scoring.SegmentationScorer;
import io.retentionlab.core.domain.analysis.tension.TensionAnalyzer;
import io.retentionlab.core.domain.model.ScriptStructure;
import io.retentionlab.core.domain.model.segmentation.StructuralSegmentationService;
import io.retentionlab.core.domain.scoring.*;
import org.springframework.stereotype.Service;

@Service
public class RetentionAnalysisService {

    private final StructuralSegmentationService segmentationService;
    private final OpeningAnalysisService openingAnalysis;
    private final LoopDensityAnalyzer loopAnalyzer;
    private final TensionAnalyzer tensionAnalyzer;
    private final RiskAnalyzer riskAnalyzer;

    private final SegmentationScorer segmentationScorer;
    private final HookScorer hookScorer;
    private final LoopDensityScorer loopDensityScorer;
    private final TensionScorer tensionScorer;
    private final RiskScorer riskScorer;
    private final ClosureScorer closureScorer;

    private final RetentionScoringService scoringService;

    public RetentionAnalysisService(
        StructuralSegmentationService segmentationService,
        OpeningAnalysisService openingAnalysis,
        LoopDensityAnalyzer loopAnalyzer,
        TensionAnalyzer tensionAnalyzer,
        RiskAnalyzer riskAnalyzer,
        SegmentationScorer segmentationScorer,
        HookScorer hookScorer,
        LoopDensityScorer loopDensityScorer,
        TensionScorer tensionScorer,
        RiskScorer riskScorer,
        ClosureScorer closureScorer,
        RetentionScoringService scoringService
    ) {
        this.segmentationService = segmentationService;
        this.openingAnalysis = openingAnalysis;
        this.loopAnalyzer = loopAnalyzer;
        this.tensionAnalyzer = tensionAnalyzer;
        this.riskAnalyzer = riskAnalyzer;
        this.segmentationScorer = segmentationScorer;
        this.hookScorer = hookScorer;
        this.loopDensityScorer = loopDensityScorer;
        this.tensionScorer = tensionScorer;
        this.riskScorer = riskScorer;
        this.closureScorer = closureScorer;
        this.scoringService = scoringService;
    }

    public RetentionReport analyze(String text) {

        ScriptStructure structure = segmentationService.analyze(text);

        var openingResult = openingAnalysis.analyze(text, structure);
        var loopResult = loopAnalyzer.analyze(structure, text);
        var tensionResult = tensionAnalyzer.analyze(structure, text);
        var riskResult = riskAnalyzer.analyze(structure, tensionResult.tensionValues());

        double segmentationScore =
            segmentationScorer.calculate(structure);

        double hookScore =
            hookScorer.calculate(
                openingResult.scoreAggregate().finalScore()
            );

        double loopScore =
            loopDensityScorer.calculate(
                loopResult.openLoops(),
                structure.totalBlocks()
            );

        double tensionScore =
            tensionScorer.calculate(
                tensionResult.tensionValues()
            );

        double riskScore =
            riskScorer.calculate(
                riskResult.tensions()
            );

        double closureScore =
            closureScorer.calculate(
                loopResult.openLoops(),
                loopResult.closedLoops()
            );

        double finalScore = scoringService.calculate(
            segmentationScore,
            hookScore,
            loopScore,
            tensionScore,
            riskScore,
            closureScore
        );

        return new RetentionReport(
            structure,
            openingResult,
            loopResult,
            tensionResult,
            riskResult,
            finalScore
        );
    }
}