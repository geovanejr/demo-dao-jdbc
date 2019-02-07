package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== Test nº 1 - Seller Find by Id ===");
		Seller seller = sellerDao.findById(7);
		
		System.out.println(seller);
	}

}
