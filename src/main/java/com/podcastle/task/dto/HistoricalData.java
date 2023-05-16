package com.podcastle.task.dto;

import lombok.Data;
import java.util.List;

@Data
public class HistoricalData {
    private int change;
    private String resolution;
    private int quantity;
    private List<HistoricalValueDTO> values;
}
