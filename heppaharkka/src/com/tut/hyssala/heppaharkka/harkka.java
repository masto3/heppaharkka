package com.tut.hyssala.heppaharkka;

public class harkka2 {
	private long id;
	private String harkkapaikka;
	private String harkkapaiva;
	private String harkkaaika;
	private String ratsastaja;
	private String hevonen;
	private String valmentaja;
	private long ilmoittautunut;
	
	
	public long getId() {
		return id;
	}
	public String getHarkkapaikka() {
		return harkkapaikka;
	}
	public String getHarkkapaiva() {
		return harkkapaiva;
	}
	public String getHarkkaaika() {
		return harkkaaika;
	}
	public String getRatsastaja() {
		return ratsastaja;
	}
	public String getHevonen() {
		return hevonen;
	}
	public String getValmentaja() {
		return valmentaja;
	}
	
	public long getIlmoittautunut() {
		return ilmoittautunut;
	}
	public void setIlmoittautunut(long ilmoittautunut) {
		this.ilmoittautunut = ilmoittautunut;
	}
	
		
	@Override
	public String toString() {
		return id + " " + harkkapaikka + " " + harkkapaiva + " " + harkkaaika + " " + ratsastaja + " " + hevonen + " " + valmentaja + " " + ilmoittautunut;
	}
	
	
}
