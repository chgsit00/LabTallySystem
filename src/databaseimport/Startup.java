package databaseimport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import csv.CSVManagement;

public class Startup
{

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Inhalt von Laborblatt löschen und neu von der CSV-Datei einlesen? y/n");
		if (ImportAcceptedSwitch(br))
		{
			CSVManagement csvManagement = new CSVManagement();
			csvManagement.DeleteAndImportLaborblattFromCSV();
		}

		System.out.println("Inhalt von Team löschen und neu von der CSV-Datei einlesen? y/n");
		if (ImportAcceptedSwitch(br))
		{
			CSVManagement csvManagement = new CSVManagement();
			csvManagement.DeleteAndImportTeamFromCSV();
		}

		System.out.println("Inhalt von Rechner löschen und neu von der CSV-Datei einlesen? y/n");
		if (ImportAcceptedSwitch(br))
		{
			CSVManagement csvManagement = new CSVManagement();
			csvManagement.DeleteAndImportRechnerFromCSV();
		}

		System.out.println("Inhalt von Aufgabe löschen und neu von der CSV-Datei einlesen? y/n");
		if (ImportAcceptedSwitch(br))
		{
			CSVManagement csvManagement = new CSVManagement();
			csvManagement.DeleteAndImportAufgabeFromCSV();
		}

		System.out.println("Inhalt von Laborslots löschen und neu von der CSV-Datei einlesen? y/n");
		if (ImportAcceptedSwitch(br))
		{
			CSVManagement csvManagement = new CSVManagement();
			csvManagement.DeleteAndImportLaborslotFromCSV();
		}

		System.out.println("Inhalt von Ergebnis löschen und neu von der CSV-Datei einlesen? y/n");
		if (ImportAcceptedSwitch(br))
		{
			CSVManagement csvManagement = new CSVManagement();
			csvManagement.DeleteAndImportErgebnisFromCSV();
		}

		System.out.println("Inhalt von Loesung löschen und neu von der CSV-Datei einlesen? y/n");
		if (ImportAcceptedSwitch(br))
		{
			CSVManagement csvManagement = new CSVManagement();
			csvManagement.DeleteAndImportLoesungFromCSV();
		}

		System.out.println("Inhalt von Einteilung löschen und neu von der CSV-Datei einlesen? y/n");
		if (ImportAcceptedSwitch(br))
		{
			CSVManagement csvManagement = new CSVManagement();
			csvManagement.DeleteAndImportEinteilungFromCSV();
		}

		br.close();
	}

	private static boolean ImportAcceptedSwitch(BufferedReader br)
	{
		String input;
		boolean reimportAccepted = false;
		try
		{
			input = br.readLine();
			if (input.equals("y"))
			{
				reimportAccepted = true;
			} else if (input.equals("Y"))
			{
				reimportAccepted = true;
			}
			return reimportAccepted;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return reimportAccepted;
		}
	}

}
