package io.retentionlab.core.domain.analysis.risk;

import io.retentionlab.core.domain.model.Block;

public record RiskBlock(
    Block block,
    double tensionValue,
    RiskType type
) {}
