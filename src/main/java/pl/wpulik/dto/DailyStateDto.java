package pl.wpulik.dto;

import java.io.Serializable;

import pl.wpulik.model.DailyState;

public class DailyStateDto implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private long Pi;//number of infected people
	private long Pv;//number of healthy people susceptible to infection
	private long Pm;//number of people who died
	private long Pr;//number of people recovered
	
	public DailyStateDto() {}

	public DailyStateDto(long pi, long pv, long pm, long pr) {
		Pi = pi;
		Pv = pv;
		Pm = pm;
		Pr = pr;
	}
	
	public static DailyStateDto mapToDto(DailyState ds) {
		return new DailyStateDto(ds.getInfected(),
				ds.getHealthySusceptible(),
				ds.getDied(),
				ds.getRecoveredResistant());
	}

	public long getPi() {
		return Pi;
	}

	public void setPi(long pi) {
		Pi = pi;
	}

	public long getPv() {
		return Pv;
	}

	public void setPv(long pv) {
		Pv = pv;
	}

	public long getPm() {
		return Pm;
	}

	public void setPm(long pm) {
		Pm = pm;
	}

	public long getPr() {
		return Pr;
	}

	public void setPr(long pr) {
		Pr = pr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Pi ^ (Pi >>> 32));
		result = prime * result + (int) (Pm ^ (Pm >>> 32));
		result = prime * result + (int) (Pr ^ (Pr >>> 32));
		result = prime * result + (int) (Pv ^ (Pv >>> 32));
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
		DailyStateDto other = (DailyStateDto) obj;
		if (Pi != other.Pi)
			return false;
		if (Pm != other.Pm)
			return false;
		if (Pr != other.Pr)
			return false;
		if (Pv != other.Pv)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DailyStateDto [Pi=" + Pi + ", Pv=" + Pv + ", Pm=" + Pm + ", Pr=" + Pr + "]";
	}
	
	
	
	
	

}
