package io.retentionlab.core.domain.analysis.loop;

public record Loop(
    String id,
    int openingBlockId,
    Integer closingBlockId,
    String description,
    LoopType type
) {
    public boolean isOpen() {
        return closingBlockId == null;
    }

    public int getLatencyInBlocks() {
        if (isOpen()) return Integer.MAX_VALUE;
        return closingBlockId - openingBlockId;
    }
}

