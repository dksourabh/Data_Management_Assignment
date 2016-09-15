package com.dk.wcAssignment;
//Struts 2 Action controller class.
public class ActionController {
	private String name;
	private String ailment;
	private String listOfDoctors;
	private String diseaseDescription;
	private String hospitalsName;

	public String getListOfDoctors() {
		return listOfDoctors;
	}

	public void setListOfDoctors(String listOfDoctors) {
		this.listOfDoctors = listOfDoctors;
	}

	public String getDiseaseDescription() {
		return diseaseDescription;
	}

	public void setDiseaseDescription(String diseaseDescription) {
		this.diseaseDescription = diseaseDescription;
	}

	public String getHospitalsName() {
		return hospitalsName;
	}

	public void setHospitalsName(String hospitalsName) {
		this.hospitalsName = hospitalsName;
	}
// This method is invoked when the form is submitted. It's the heart of the application.
	public String execute() throws Exception {
		WebServiceClient wc = new WebServiceClient();
		//Get latittude and longitude.
		Double latittute = PlacesService.getLatLongCoordinates(name)[0];
		Double longittude = PlacesService.getLatLongCoordinates(name)[1];
		//Fetch hospital names.
		hospitalsName = PlacesService.searchHospitals("hospital", latittute, longittude, 10000);
		//Fetch doctor names.
		listOfDoctors=wc.getDoctorsNames(latittute, longittude);
		//Fetch disease description.
		diseaseDescription=wc.getDiseaseDescription(ailment);
		return "success";
	}

	public String getAilment() {
		return ailment;
	}

	public void setAilment(String ailment) {
		this.ailment = ailment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
