package io.retentionlab.core.config;

import io.retentionlab.core.domain.parameters.DefaultScoringParameters;
import io.retentionlab.core.domain.parameters.ScoringParameters;
import io.retentionlab.core.domain.scoring.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScoringConfig {

    @Bean
    public ScoringParameters scoringParameters() {
        return new DefaultScoringParameters();
    }

    @Bean
    public HookScorer hookScorer(ScoringParameters parameters) {
        return new HookScorer(parameters);
    }

    @Bean
    public LoopDensityScorer loopDensityScorer(ScoringParameters parameters) {
        return new LoopDensityScorer(parameters);
    }

    @Bean
    public TensionScorer tensionScorer(ScoringParameters parameters) {
        return new TensionScorer(parameters);
    }

    @Bean
    public RiskScorer riskScorer(ScoringParameters parameters) {
        return new RiskScorer(parameters);
    }

    @Bean
    public ClosureScorer closureScorer() {
        return new ClosureScorer();
    }
}