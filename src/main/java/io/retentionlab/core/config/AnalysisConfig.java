package io.retentionlab.core.config;

import io.retentionlab.core.domain.analysis.loop.LoopDensityAnalyzer;
import io.retentionlab.core.domain.analysis.opening.OpeningAnalysisService;
import io.retentionlab.core.domain.analysis.opening.OpeningHookAnalyzer;
import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalAnalyzer;
import io.retentionlab.core.domain.analysis.risk.RiskAnalyzer;
import io.retentionlab.core.domain.analysis.tension.TensionAnalyzer;
import io.retentionlab.core.domain.model.segmentation.StructuralSegmentationService;
import io.retentionlab.infrastructure.mock.MockLoopExtractor;
import io.retentionlab.infrastructure.mock.MockTensionExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalysisConfig {

    @Bean
    public StructuralSegmentationService segmentationService() {
        return new StructuralSegmentationService(150, 150);
    }

    @Bean
    public OpeningHookAnalyzer openingHookAnalyzer() {
        return new OpeningHookAnalyzer(150);
    }

    @Bean
    public OpeningSignalAnalyzer openingSignalAnalyzer() {
        return new OpeningSignalAnalyzer();
    }

    @Bean
    public OpeningAnalysisService openingAnalysisService(
        OpeningHookAnalyzer hookAnalyzer,
        OpeningSignalAnalyzer signalAnalyzer
    ) {
        return new OpeningAnalysisService(hookAnalyzer, signalAnalyzer);
    }

    @Bean
    public LoopDensityAnalyzer loopDensityAnalyzer() {
        // ***SUBSTITUIR por OllamaLoopExtractor***
        return new LoopDensityAnalyzer(new MockLoopExtractor());
    }

    @Bean
    public TensionAnalyzer tensionAnalyzer() {
        return new TensionAnalyzer(new MockTensionExtractor());
    }

    @Bean
    public RiskAnalyzer riskAnalyzer() {
        return new RiskAnalyzer();
    }
}