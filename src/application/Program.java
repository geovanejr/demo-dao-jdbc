package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department dep = new Department(1, "Books");

		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 2400.0, dep);
		
		System.out.println(dep);
		System.out.println(seller);
	}

}
