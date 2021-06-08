package pl.wpulik.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.model.DailyState;
import pl.wpulik.model.Simulation;
import pl.wpulik.repository.DailyStateRepository;
import pl.wpulik.repository.SimulationRepository;

@Service
@Transactional
public class SimulationRepoService {
	
	private SimulationRepository simulationRepository;
	private DailyStateRepository dailyStateRepository;
	
	@Autowired
	public SimulationRepoService(SimulationRepository simulationRepository,
			DailyStateRepository dailyStateRepository) {
		this.simulationRepository = simulationRepository;
		this.dailyStateRepository = dailyStateRepository;
	}
	
	public Simulation save(Simulation simulation) {
		return simulationRepository.save(simulation);
	}
	
	public List<DailyState> saveAllDailyStates(List<DailyState> epidemicCourse){
		return dailyStateRepository.saveAll(epidemicCourse);
	}
	
	public Simulation persistSimulationCourse(Simulation simulation, List<DailyState>epidemicCourse) {
		Simulation sim = this.save(simulation);
		List<DailyState> dailyStates = saveAllDailyStates(epidemicCourse);
		dailyStates.forEach(ds -> ds.setSimulation(sim));
		sim.setDailyStates(dailyStates);
		return save(sim);
	}
	
	public Simulation findByName(String name) {
		Simulation simulation = simulationRepository.findByName(name).get();
		simulation.getDailyStates();
		return simulation;
	}
	
	

}
