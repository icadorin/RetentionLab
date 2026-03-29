package io.retentionlab.core.analysis.opening;

import io.retentionlab.core.domain.analysis.opening.OpeningAnalysisResult;
import io.retentionlab.core.domain.analysis.opening.OpeningAnalysisService;
import io.retentionlab.core.domain.analysis.opening.OpeningHookAnalyzer;
import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalAnalyzer;
import io.retentionlab.core.domain.model.segmentation.StructuralSegmentationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpeningAnalysisServiceTest {

    private final OpeningAnalysisService service =
        new OpeningAnalysisService(
            new OpeningHookAnalyzer(150),
            new OpeningSignalAnalyzer()
        );

    @Test
    void shouldPerformFullOpeningAnalysis() {

        String text =
            "Eu cometi um erro enorme. " +
                "Você sabe por que isso acontece? " +
                "Hoje eu vou te mostrar a solução.";

        StructuralSegmentationService segmentation =
            new StructuralSegmentationService(150, 150);

        var structure = segmentation.analyze(text);

        OpeningAnalysisResult result =
            service.analyze(text, structure);

        assertNotNull(result.hookScore());
        assertNotNull(result.signalScore());
        assertNotNull(result.scoreAggregate());

        assertTrue(result.scoreAggregate().finalScore() > 0);
    }
}
