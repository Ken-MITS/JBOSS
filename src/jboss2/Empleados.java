package jboss2;
// Generated 3 feb. 2021 16:22:37 by Hibernate Tools 5.4.12.Final

/**
 * Empleados generated by hbm2java
 */
public class Empleados implements java.io.Serializable {

	private EmpleadosId id;
	private Departamentos departamentos;

	public Empleados() {
	}

	public Empleados(EmpleadosId id) {
		this.id = id;
	}

	public Empleados(EmpleadosId id, Departamentos departamentos) {
		this.id = id;
		this.departamentos = departamentos;
	}

	public EmpleadosId getId() {
		return this.id;
	}

	public void setId(EmpleadosId id) {
		this.id = id;
	}

	public Departamentos getDepartamentos() {
		return this.departamentos;
	}

	public void setDepartamentos(Departamentos departamentos) {
		this.departamentos = departamentos;
	}

}
