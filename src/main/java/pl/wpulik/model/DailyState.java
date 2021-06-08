package pl.wpulik.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DailyState implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_daily_sim")
	private Long id;
	private Integer dayOfSymulation;
	private Long infected;
	private Long newInfected;
	private Long healthySusceptible;
	private Long died;
	private Long recoveredResistant;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="simulation_id")
	private Simulation simulation;
	
	public DailyState() {}
	
	public DailyState(Integer dayOfSymulation, Long infected, Long newInfected, Long healthySusceptible, Long died,
			Long recoveredResistant) {
		this.dayOfSymulation = dayOfSymulation;
		this.infected = infected;
		this.newInfected = newInfected;
		this.healthySusceptible = healthySusceptible;
		this.died = died;
		this.recoveredResistant = recoveredResistant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDayOfSymulation() {
		return dayOfSymulation;
	}

	public void setDayOfSymulation(Integer dayOfSymulation) {
		this.dayOfSymulation = dayOfSymulation;
	}
	
	public Long getNewInfected() {
		return newInfected;
	}

	public void setNewInfected(Long newInfected) {
		this.newInfected = newInfected;
	}

	public Long getInfected() {
		return infected;
	}

	public void setInfected(Long infected) {
		this.infected = infected;
	}

	public Long getHealthySusceptible() {
		return healthySusceptible;
	}

	public void setHealthySusceptible(Long healthySusceptible) {
		this.healthySusceptible = healthySusceptible;
	}

	public Long getDied() {
		return died;
	}

	public void setDied(Long died) {
		this.died = died;
	}

	public Long getRecoveredResistant() {
		return recoveredResistant;
	}

	public void setRecoveredResistant(Long recoveredResistant) {
		this.recoveredResistant = recoveredResistant;
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayOfSymulation == null) ? 0 : dayOfSymulation.hashCode());
		result = prime * result + ((died == null) ? 0 : died.hashCode());
		result = prime * result + ((healthySusceptible == null) ? 0 : healthySusceptible.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((infected == null) ? 0 : infected.hashCode());
		result = prime * result + ((recoveredResistant == null) ? 0 : recoveredResistant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyState other = (DailyState) obj;
		if (dayOfSymulation == null) {
			if (other.dayOfSymulation != null)
				return false;
		} else if (!dayOfSymulation.equals(other.dayOfSymulation))
			return false;
		if (died == null) {
			if (other.died != null)
				return false;
		} else if (!died.equals(other.died))
			return false;
		if (healthySusceptible == null) {
			if (other.healthySusceptible != null)
				return false;
		} else if (!healthySusceptible.equals(other.healthySusceptible))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (infected == null) {
			if (other.infected != null)
				return false;
		} else if (!infected.equals(other.infected))
			return false;
		if (recoveredResistant == null) {
			if (other.recoveredResistant != null)
				return false;
		} else if (!recoveredResistant.equals(other.recoveredResistant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DailyState [id=" + id + ", dayOfSymulation=" + dayOfSymulation + ", infected=" + infected
				+ ", newInfected=" + newInfected + ", healthySusceptible=" + healthySusceptible + ", died=" + died 
				+ ", recoveredResistant="
				+ recoveredResistant + "]" + " SUM=" + (infected + healthySusceptible + died + recoveredResistant);
	}

	
	
	
	
	

}
