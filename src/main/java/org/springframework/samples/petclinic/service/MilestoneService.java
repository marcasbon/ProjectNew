package org.springframework.samples.petclinic.service;
/*
 * Copyright 2002-2013 the original author or authors.|
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Milestone;
import org.springframework.samples.petclinic.repository.MilestoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */

@Service
public class MilestoneService {
	private MilestoneRepository milestoneRepository;

	@Autowired
	public MilestoneService(MilestoneRepository milestoneRepository) {
		this.milestoneRepository = milestoneRepository;

	}

	@Transactional
	public void saveMilestone(Milestone milestones) throws DataAccessException {
		milestoneRepository.save(milestones);
	}

	@Transactional(readOnly = true)
	public Milestone findMilestoneById(Integer milestoneId) throws DataAccessException {
		return milestoneRepository.findById(milestoneId);
	}


	@Transactional
	public void deleteMilestonetById(Integer milestoneId) throws DataAccessException {
		milestoneRepository.deleteById(milestoneId);
	}

	// Tampoco tiene sentido este método, se devuelven todas las milestones de
	// todos los projectos de todos los departamentos de todos los teams
	@Transactional(readOnly = true)
	public Collection<Milestone> getAllMilestone() throws DataAccessException {
		return milestoneRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Milestone findNextMilestone(Integer projectId) throws DataAccessException {
		return milestoneRepository.findNextMilestone(projectId).stream().filter(x->x.getDueFor().isBefore(LocalDate.now())).sorted(Comparator.comparing(x->x.getDueFor())).sorted(Collections.reverseOrder()).findFirst().get();
	}
	

}
