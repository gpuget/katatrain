package com.excilys.katatrain.domain.ports;

import com.excilys.katatrain.domain.annotations.Port;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.TrainSnapshot;

@Port
public interface TrainDataProvider {
    TrainSnapshot getTrainSnapshot(String trainId);

    void register(Reservation reservation);
}
