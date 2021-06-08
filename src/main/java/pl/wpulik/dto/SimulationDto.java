package pl.wpulik.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wpulik.model.Simulation;

public class SimulationDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String N;//simulation name
	private Long P;//population size
	private Long I;//number of infected 
	private Double R;//virus transmission factor
	private Double M;//mortality rate
	private Integer Ti;//recovery time
	private Integer Tm;//time from infection to death
	private Integer Ts;//duration of the simulation
	
	private List<DailyStateDto> epidemicCourse = new ArrayList<>();
	
	
	public SimulationDto(){}


	public SimulationDto(String n, Long p, Long i, Double r, Double m, Integer ti, Integer tm, Integer ts) {
		N = n;
		P = p;
		I = i;
		R = r;
		M = m;
		Ti = ti;
		Tm = tm;
		Ts = ts;
	}
	
	public static Simulation mapFromDto(SimulationDto dto) {
		return new Simulation(dto.getN(), dto.getP(), dto.getI(), dto.getR(), 
				dto.getM(), dto.getTi(), dto.getTm(), dto.getTs());
	}
	
	public static SimulationDto mapToDto(Simulation s) {
		return new SimulationDto(s.getName(), s.getPopulationSize(), s.getInitialInfected(), s.getTransmissionFactor(),
				s.getMortalityRate(), s.getRecoveryTime(), s.getDeadTime(), s.getDurationTime());
	}


	public String getN() {
		return N;
	}


	public void setN(String n) {
		N = n;
	}


	public Long getP() {
		return P;
	}


	public void setP(Long p) {
		P = p;
	}


	public Long getI() {
		return I;
	}


	public void setI(Long i) {
		I = i;
	}


	public Double getR() {
		return R;
	}


	public void setR(Double r) {
		R = r;
	}


	public Double getM() {
		return M;
	}


	public void setM(Double m) {
		M = m;
	}


	public Integer getTi() {
		return Ti;
	}


	public void setTi(Integer ti) {
		Ti = ti;
	}


	public Integer getTm() {
		return Tm;
	}


	public void setTm(Integer tm) {
		Tm = tm;
	}


	public Integer getTs() {
		return Ts;
	}


	public void setTs(Integer ts) {
		Ts = ts;
	}

	public List<DailyStateDto> getEpidemicCourse() {
		return epidemicCourse;
	}


	public void setEpidemicCourse(List<DailyStateDto> epidemicCourse) {
		this.epidemicCourse = epidemicCourse;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((I == null) ? 0 : I.hashCode());
		result = prime * result + ((M == null) ? 0 : M.hashCode());
		result = prime * result + ((N == null) ? 0 : N.hashCode());
		result = prime * result + ((P == null) ? 0 : P.hashCode());
		result = prime * result + ((R == null) ? 0 : R.hashCode());
		result = prime * result + ((Ti == null) ? 0 : Ti.hashCode());
		result = prime * result + ((Tm == null) ? 0 : Tm.hashCode());
		result = prime * result + ((Ts == null) ? 0 : Ts.hashCode());
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
		SimulationDto other = (SimulationDto) obj;
		if (I == null) {
			if (other.I != null)
				return false;
		} else if (!I.equals(other.I))
			return false;
		if (M == null) {
			if (other.M != null)
				return false;
		} else if (!M.equals(other.M))
			return false;
		if (N == null) {
			if (other.N != null)
				return false;
		} else if (!N.equals(other.N))
			return false;
		if (P == null) {
			if (other.P != null)
				return false;
		} else if (!P.equals(other.P))
			return false;
		if (R == null) {
			if (other.R != null)
				return false;
		} else if (!R.equals(other.R))
			return false;
		if (Ti == null) {
			if (other.Ti != null)
				return false;
		} else if (!Ti.equals(other.Ti))
			return false;
		if (Tm == null) {
			if (other.Tm != null)
				return false;
		} else if (!Tm.equals(other.Tm))
			return false;
		if (Ts == null) {
			if (other.Ts != null)
				return false;
		} else if (!Ts.equals(other.Ts))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SimulationDto [N=" + N + ", P=" + P + ", I=" + I + ", R=" + R + ", M=" + M + ", Ti=" + Ti + ", Tm=" + Tm
				+ ", Ts=" + Ts + "]";
	}
	
	

	
}