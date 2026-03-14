package io.retentionlab.core.domain.analysis.scoring;

public final class ScoringWeights {

    public static final double SEGMENTATION = 25;
    public static final double HOOK = 20;
    public static final double LOOP_DENSITY = 20;
    public static final double TENSION = 15;
    public static final double RISK = 10;
    public static final double CLOSURE = 10;

    private ScoringWeights() {}
}