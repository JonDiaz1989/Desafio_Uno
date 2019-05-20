package com.client;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.model.Fechas;

@Component
public class CallRestService implements CommandLineRunner{
	static java.util.List<String> receivedDates;
	static ArrayList<String> missingDates;


	@SuppressWarnings("unchecked")
	private static void callRestService() {
	

	RestTemplate restTemplate = new RestTemplate();
	Fechas fechas = restTemplate.getForObject("http://localhost:8081/periodos/api", Fechas.class);
	System.out.println("ID: "+fechas.getId());
	System.out.println("Fecha Creacion: "+fechas.getFechaCreacion());
	System.out.println("Fecha Fin: "+fechas.getFechaFin());
	System.out.println("List de Fechas: "+fechas.getFechas());
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
	Calendar beginCalendar = Calendar.getInstance();
	Calendar endCalendar = Calendar.getInstance();
	
	
	try {
		beginCalendar.setTime(dateFormat.parse(fechas.getFechaCreacion()));
		endCalendar.setTime(dateFormat.parse(fechas.getFechaFin()));
	} catch (ParseException e) {
		e.printStackTrace();
	}
	endCalendar.add(Calendar.MONTH, 1);
	
	
	receivedDates = (java.util.List<String>) fechas.getFechas();
	missingDates = new ArrayList<String>();

	while (beginCalendar.before(endCalendar)) {
		String date = dateFormat.format(beginCalendar.getTime()).toUpperCase();

		if (!receivedDates.contains(date + "-01")) {
			missingDates.add(date);
		}
		beginCalendar.add(Calendar.MONTH, 1);

	}
	
	System.out.println("Fechas Faltantes: "+missingDates);
	writeFile(fechas);
	}
	
	

	private static void writeFile(Fechas fecha) {
		final String filePath = "AnalisisFechas";
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));

			writer.write("fecha creacion: " + fecha.getFechaCreacion());
			writer.write("\n");
			writer.write("fecha fin: " + fecha.getFechaFin());
			writer.write("\n");
			writer.write("fechas recibidas:");
			for (String date : receivedDates) {
				writer.write(" " + date);
			}
			writer.write("\n");
			writer.write("fechas faltantes:");
			for (String date : missingDates) {
				writer.write(" " + date + "-01");
			}
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(filePath);
	}

	
	@Override
	public void run(String... args) throws Exception {
		callRestService();
		
	}
	
	

	
}
