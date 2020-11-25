package com.excilys.katatrain.domain.ports;

import com.excilys.katatrain.domain.core.TrainSnapshot;

public interface TrainDataProvider {
    TrainSnapshot getTrain(String trainId);
}
