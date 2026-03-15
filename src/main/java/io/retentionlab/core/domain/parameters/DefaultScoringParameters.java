package io.retentionlab.core.domain.parameters;

import org.springframework.stereotype.Component;

@Component
public class DefaultScoringParameters implements ScoringParameters {

    @Override public double idealHookDensity() { return 0.05; }
    @Override public double idealLoopDensity() { return 0.30; }
    @Override public double idealIncreasingRatio() { return 0.70; }
    @Override public double riskThreshold() { return 0.30; }
    @Override public double epsilon() { return 0.0001; }

    @Override public double openingWeight() { return 20; }
    @Override public double loopWeight() { return 20; }
    @Override public double tensionWeight() { return 30; }
    @Override public double riskWeight() { return 10; }
    @Override public double payoffWeight() { return 20; }
}