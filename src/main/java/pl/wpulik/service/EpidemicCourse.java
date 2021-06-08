package pl.wpulik.service;

import org.springframework.stereotype.Service;

import pl.wpulik.model.DailyState;
import pl.wpulik.model.Simulation;

@Service
public class EpidemicCourse {

	private DailyState[] course;

	private Double R;// virus transmission factor
	private Double M;// mortality rate
	private Integer Ti;// recovery time
	private Integer Tm;// time from infection to death
	private Integer Ts;// duration of the simulation
	private Long P;// population size

	public EpidemicCourse() {
	}

	public void createSimulation(Simulation simulation) {
		R = simulation.getTransmissionFactor();
		M = simulation.getMortalityRate();
		Ti = simulation.getRecoveryTime();
		Tm = simulation.getDeadTime();
		Ts = simulation.getDurationTime();
		P = simulation.getPopulationSize();
		course = new DailyState[Ts + 1];
		this.initiateSimlationCourseArray(simulation);

	}

	private void initiateSimlationCourseArray(Simulation s) {
		course[0] = new DailyState(0, s.getInitialInfected(), s.getInitialInfected(), (P - s.getInitialInfected()), 0L,
				0L);
		for (int i = 1; i < course.length; i++) {
			course[i] = new DailyState(i, 0L, 0L, 0L, 0L, 0L);
		}
	}

	public void dailyCourse() {

		for (int i = 1; i < course.length; i++) {

			long formerInfected = course[i - 1].getInfected();
			long formerSusceptible = course[i - 1].getHealthySusceptible();
			long formerDied = course[i - 1].getDied();
			long formerRecoveredResistant = course[i - 1].getRecoveredResistant();

			long tmNewInfected = 0;
			if (i - Tm >= 0)
				tmNewInfected = course[i - Tm].getNewInfected();
			long tiNewInfected = 0;
			if (i - Ti >= 0)
				tiNewInfected = course[i - Ti].getNewInfected();
			/*
			 * Kończy się liczba osób zdrowych-nieodpornych:
			 */
			if ((Math.round(formerInfected * R) - formerInfected) > formerSusceptible && formerSusceptible > 0) {
				course[i].setInfected(formerInfected + formerSusceptible);
				course[i].setNewInfected(formerSusceptible);
				course[i].setHealthySusceptible(0L);
				setMortalityAndRecoveryCourse(i, tmNewInfected, formerDied, tiNewInfected, formerRecoveredResistant);

			/*
			 * Brak osób zdrowych:
			 */
			} else if (formerSusceptible == 0 && formerInfected > 0) {
				course[i].setHealthySusceptible(0L);
				course[i].setInfected(formerInfected);
				course[i].setNewInfected(0L);
				setMortalityAndRecoveryCourse(i, tmNewInfected, formerDied, tiNewInfected, formerRecoveredResistant);

			/*
			 * Brak zakażonych:
			 */
			} else if (formerSusceptible == 0 && formerInfected == 0) {
				course[i].setHealthySusceptible(0L);
				course[i].setInfected(0L);
				course[i].setNewInfected(0L);
				course[i].setDied(formerDied);
				course[i].setRecoveredResistant(formerRecoveredResistant);

			/*
			 * Początkowy przebieg epidemii:
			 */
			} else {
				course[i].setHealthySusceptible(formerSusceptible - (Math.round(formerInfected * R) - formerInfected));
				course[i].setInfected(Math.round(formerInfected * R));
				course[i].setNewInfected(Math.round(formerInfected * R) - formerInfected);
				setMortalityAndRecoveryCourse(i, tmNewInfected, formerDied, tiNewInfected, formerRecoveredResistant);
			}
		}
	}

	private void setMortalityAndRecoveryCourse(int i, long tmNewInfected, long formerDied, long tiNewInfected,
			long formerRecoveredResistant) {
		course[i].setInfected(course[i].getInfected() - Math.round(tmNewInfected * M));
		course[i].setDied(formerDied + Math.round(tmNewInfected * M));
		course[i].setInfected(course[i].getInfected() - (tiNewInfected - Math.round(tiNewInfected * M)));
		course[i].setRecoveredResistant(formerRecoveredResistant + (tiNewInfected - Math.round(tiNewInfected * M)));
	}

	public DailyState[] getCourse() {
		return course;
	}

}
