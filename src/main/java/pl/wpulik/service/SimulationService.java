package pl.wpulik.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.dto.DailyStateDto;
import pl.wpulik.dto.SimulationDto;
import pl.wpulik.model.DailyState;
import pl.wpulik.model.Simulation;

@Service
public class SimulationService {
	
	private SimulationRepoService simulationRepoService;
	private EpidemicCourse epidemicCourse;

	@Autowired
	public SimulationService(SimulationRepoService simulationRepoService, EpidemicCourse epidemicCourse) {
		this.simulationRepoService = simulationRepoService;
		this.epidemicCourse = epidemicCourse;
	}
	
	public SimulationDto createNewSimualtionDto(SimulationDto simdto) {
		Simulation simulation = SimulationDto.mapFromDto(simdto);
		simulation = this.createNewSimulation(simulation);
		List<DailyStateDto> dailyDtos = simulation.getDailyStates().stream()
				.map(ds -> DailyStateDto.mapToDto(ds)).collect(Collectors.toList());
		simdto.setEpidemicCourse(dailyDtos);
		return simdto;
	}
	
	public SimulationDto findByName(String name) {
		Simulation simulation = simulationRepoService.findByName(name);
		SimulationDto dto = SimulationDto.mapToDto(simulation);
		List<DailyStateDto> dailyDtos = simulation.getDailyStates().stream()
				.map(ds -> DailyStateDto.mapToDto(ds)).collect(Collectors.toList());
		dto.setEpidemicCourse(dailyDtos);
		return dto;
	}
	
	public Simulation createNewSimulation (Simulation simulation) {
		epidemicCourse.createSimulation(simulation);
		epidemicCourse.dailyCourse();
		List<DailyState> dailyStates = Arrays.asList(epidemicCourse.getCourse());
		return simulationRepoService.persistSimulationCourse(simulation, dailyStates);	
	}
	


}
