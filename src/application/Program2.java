package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
//		System.out.println("=== Test nº 1 - Department Find by Id ===");
		
//		Department dep = departmentDao.findById(1);
//		
//		System.out.println(dep);
//
//		System.out.println("\n=== Test nº 2 - Department Find All (order by id) ===");
//		
//		List<Department> list = departmentDao.findAll(false);
//		
//		for (Department obj : list) {
//			System.out.println(obj);
//		}
//		
//		list = departmentDao.findAll(true);
//		
//		System.out.println("\n=== Test nº 3 - Department Find All (order by name) ===");
//		for (Department obj : list) {
//			System.out.println(obj);
//		}
//		
//		System.out.println("\n=== Test nº 4 - Department - Insert ===");
//		
//		Department newDep = new Department(null, "Roupa Infantil");
//		
//		departmentDao.insert(newDep);
//		
//		System.out.println("Inserted! New id: " + newDep.getId());
//
//		newDep = new Department(null, "Roupa Masculina");
//		
//		departmentDao.insert(newDep);
//		
//		System.out.println("Inserted! New id: " + newDep.getId());
//
//		newDep = new Department(null, "Roupa Feminina");
//		
//		departmentDao.insert(newDep);
//		
//		System.out.println("Inserted! New id: " + newDep.getId());
//
//		newDep = new Department(null, "Roupa 3ª Idade");
//		
//		departmentDao.insert(newDep);
//		
//		System.out.println("Inserted! New id: " + newDep.getId());
//
//		System.out.println("\n");
//		
//		list = departmentDao.findAll(false);
//		for (Department obj : list) {
//			System.out.println(obj);
//		}
//
//		System.out.println("\n=== Test nº 5 - Department - Update ===");
//		
//		dep = departmentDao.findById(newDep.getId());
//		System.out.println("\n" + dep);
//		dep.setName("Roupa Masculina");
//		departmentDao.update(dep);
//		dep = departmentDao.findById(newDep.getId());
//		System.out.println("\n" + dep);
//		
//		System.out.println("\n=== Test nº 6 - Department - Delete ===");
//
//		Integer id = newDep.getId();
//		
//		list = departmentDao.findAll(false);
//		for (Department obj : list) {
//			System.out.println(obj);
//		}
//
//		departmentDao.deleteById(id);
//
//		System.out.println("\n");
//		
//		list = departmentDao.findAll(false);
//		for (Department obj : list) {
//			System.out.println(obj);
//		}
//		
//		System.out.println("\n");
//		
//		list = departmentDao.findAll(false);
//		for (Department obj : list) {
//			System.out.println(obj);
//		}
//		
//		departmentDao.deleteById(2);
//
//		System.out.println("\n");
//		
//		list = departmentDao.findAll(false);
//		for (Department obj : list) {
//			System.out.println(obj);
//		}
//		
//		System.out.println("\n");
//		

		System.out.println("=== Novo Teste - Where com Like ===");
		String name = "roupa";
		
		List<Department >list = departmentDao.findByName(name);

		for (Department obj : list) {
			System.out.println(obj);
		}
		
	}

}
