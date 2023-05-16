package com.podcastle.task.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PhotoMetaDataWithStatisticsDTO extends PhotoMetaTataDTO{
    private StatisticDTO statistic;
}
