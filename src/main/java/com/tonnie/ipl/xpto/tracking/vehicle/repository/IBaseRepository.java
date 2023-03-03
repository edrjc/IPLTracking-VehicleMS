package com.tonnie.ipl.xpto.tracking.vehicle.repository;

import com.tonnie.ipl.xpto.tracking.vehicle.model.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IBaseRepository<ENTITY extends IEntity<?>, ID extends Serializable> extends JpaRepository<ENTITY, ID>   {

}
