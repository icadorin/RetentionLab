package io.retentionlab.core.domain.model;

public record Block(
    int id,
    double startMinute,
    double endMinute,
    int wordCount
) {
    public Block {
        if (id <= 0) {
            throw new IllegalArgumentException("Block id must be positive");
        }
        if (startMinute < 0 || endMinute < 0) {
            throw  new IllegalArgumentException("Time cannot be negative");
        }
        if (endMinute < startMinute) {
            throw  new IllegalArgumentException("End minute cannot be before start minute");
        }
        if (wordCount < 0) {
            throw  new IllegalArgumentException("Word count cannot be negative");
        }
    }
}
