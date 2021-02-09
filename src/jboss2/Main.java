package jboss2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
		//En primer lugar se obtiene la sesión creada por el singleton.
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		//Abrimos sesión e iniciamos una transacción
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		
		outer: while (true){
			int id;
			System.out.println("Qué opcion quieres? -- 1.select |"
					+ "2.insert | 3.update | 4.delete | 5.guardar y salir");
			int option = Integer.parseInt(scanner.readLine());
			switch (option) {
			case 1:
				select(session);
				break;
			case 2:
				System.out.println("Nombre? ");
				String nombre = scanner.readLine();
				System.out.println("Direccion? ");
				String direccion = scanner.readLine();
				System.out.println("Objetivos? ");
				int objetivos = Integer.parseInt(scanner.readLine());
				insert(session, tx, nombre, direccion, objetivos);
				break;
			case 3:
				System.out.println("ID del departamento a modificar? ");
				id= Integer.parseInt(scanner.readLine());
				update(scanner, session, tx, id);
				break;
			case 4:
				System.out.println("ID del departamento a eliminar? ");
				id= Integer.parseInt(scanner.readLine());
				delete(session, id);
				break;
			case 5:
				break outer;
			default:
				break outer;
			}
			
		}
		session.close();
		
		
		
		
	}
	private static void select(Session session) {
		Departamentos dep; 
		int id = 1;
		while (true){
			dep = (Departamentos) session.get(Departamentos.class, id);
			if (dep == null ) {
				break;
			}
			System.out.println("Id: "+dep.getCodDept()+" | Nombre: "+ dep.getNombre() +  
					" | Dirección: "+dep.getDireccion()+ " | Objetivos: "+dep.getObjetivos());
			id++;
			
		}
	}
	
	private static void insert(Session session, Transaction tx, 
			String nombre, String direccion, int objetivos) {
		//Creamos un nuevo objeto Departamentos y damos valor a sus atributos.
		Departamentos dep = new Departamentos();
		//dep.setCodDept(4);
		dep.setNombre(nombre);
		dep.setDireccion(direccion);
		dep.setObjetivos(objetivos);

		//Guardammos en la base de datos y comprometemos la información
		session.save(dep);
		tx.commit();
	}
	
	private static void update(BufferedReader scanner, Session session, Transaction tx, int id) throws NumberFormatException, IOException {
		Departamentos dep = session.get(Departamentos.class, id);
		if (dep == null) System.out.println("No existe departamento con ese id");
		else {
			System.out.println("Introduce nombre nuevo de departamento(Actual: "+dep.getNombre()+ "):");
			dep.setNombre(scanner.readLine());
			System.out.println("Introduce nueva dirección(Actual: "+dep.getDireccion()+ "):");
			dep.setDireccion(scanner.readLine());
			System.out.println("Introduce número de objetivos(Actual: "+dep.getObjetivos()+ "):");
			dep.setObjetivos(Integer.parseInt(scanner.readLine()));
			session.update(dep);
		}
	}
	
	private static void delete(Session session, int id) {
		Departamentos dep = session.get(Departamentos.class,  id);
		if (dep==null) {
			System.out.println("Departamento no existe.");
		}else {
			session.delete(dep);
		}
	}
}











