package datatest;

import services.DirectorServices;

public class TestDirectorService {
	
	public static void main(String[] args){
	DirectorServices directorServices = new DirectorServices();
	directorServices.getDirectorDetails(3);
	 }
}
