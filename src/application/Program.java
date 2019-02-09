package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== Test nº 1 - Seller Find by Id ===");
		Seller seller = sellerDao.findById(3);

		System.out.println(seller);

		System.out.println("\n=== Test nº 2 - Seller Find by Department ===");

		Department dep = new Department(2, null);

		List<Seller> list = sellerDao.findByDepartment(dep);

		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== Test nº 3 - Seller Find All ===");

		list = sellerDao.findAll();

		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== Test nº 4 - Seller Insert ===");

		Seller newSeller = new Seller(null, "Geovane Soares", "geovane.gjunior@gmail.com", new Date(), 4000., dep);
		
		sellerDao.insert(newSeller);
		
		System.out.println("Inserted! New id: " + newSeller.getId());

		System.out.println("\n=== Test nº 5 - Seller Update ===");
		
		seller = sellerDao.findById(newSeller.getId());
		System.out.println(seller);
		seller.setName("Martha Wayne");
		seller.setBirthDate(sdf.parse("27/05/1973"));
		sellerDao.update(seller);
		seller = sellerDao.findById(seller.getId());
		System.out.println(seller);

	}

}
