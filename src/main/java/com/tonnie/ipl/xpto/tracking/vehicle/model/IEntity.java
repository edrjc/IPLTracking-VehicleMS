package com.tonnie.ipl.xpto.tracking.vehicle.model;

import java.io.Serializable;

public interface IEntity<T extends Serializable> {
  
  public T getId();

}
