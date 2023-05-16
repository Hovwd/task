package com.podcastle.task.dto;

import lombok.Data;

@Data
public class PhotoMetaDataWithStatisticsDTO extends PhotoMetaTataDTO{
    private StatisticDTO statistic;
}
