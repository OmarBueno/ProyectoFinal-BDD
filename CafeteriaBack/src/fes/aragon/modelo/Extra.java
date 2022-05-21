package fes.aragon.modelo;

import java.io.Serializable;

public class Extra implements Serializable {
	private Integer id;
	private String extra;

	public Extra() {
		// TODO Auto-generated constructor stub
	}
	

	public Extra(Integer id, String extra) {
		super();
		this.id = id;
		this.extra = extra;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return extra;
	}

}
