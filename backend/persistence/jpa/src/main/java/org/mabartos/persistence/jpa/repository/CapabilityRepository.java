/*
 * Copyright (c) 2020.
 * Martin Bartos
 * SmartHome BartOS
 * All rights reserved.
 */

package org.mabartos.persistence.jpa.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mabartos.persistence.jpa.model.services.capability.CapabilityEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CapabilityRepository implements PanacheRepository<CapabilityEntity> {
}
