package io.retentionlab.core.domain.parameters;

public interface ScoringParameters {

    // Ideais
    double idealHookDensity();
    double idealLoopDensity();
    double idealIncreasingRatio();
    double riskThreshold();
    double epsilon();

    // Pesos
    double openingWeight();   // ex: 20
    double loopWeight();      // ex: 20
    double tensionWeight();   // ex: 30
    double riskWeight();      // ex: 10
    double payoffWeight();    // ex: 20
}