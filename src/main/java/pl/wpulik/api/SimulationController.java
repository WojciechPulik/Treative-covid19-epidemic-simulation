package pl.wpulik.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.wpulik.dto.SimulationDto;
import pl.wpulik.service.SimulationService;

@RestController
@RequestMapping("/simulation")
public class SimulationController {
	
	private SimulationService simulationService;
	
	@Autowired
	public SimulationController(SimulationService simulationService) {
		this.simulationService = simulationService;
	}
	
	@PostMapping
	public ResponseEntity<SimulationDto> addNewSimulation(@RequestBody SimulationDto dto){
		try {
			SimulationDto resultDto = simulationService.createNewSimualtionDto(dto);
			return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<SimulationDto> getByName(@PathVariable String name){
		try {
			SimulationDto resultDto = simulationService.findByName(name);
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
