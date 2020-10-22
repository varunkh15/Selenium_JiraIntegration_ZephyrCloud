package com.thed.parse;

import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import GetJiraAPI.TestngResults;

public class Unmarshalling {

	public static void main(String[] args) {
		
		String xmlPathToRead= "";
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(TestngResults.class);
		TestngResults  results =(TestngResults)	jaxbContext.createUnmarshaller().unmarshal(new FileReader(xmlPathToRead));
		
		System.out.println(results.get);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
