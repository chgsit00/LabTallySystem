DROP TABLE Einteilung;
DROP TABLE Loesung;
DROP TABLE Ergebnis;
DROP TABLE Laborslots;
DROP TABLE Aufgabe;
DROP TABLE Rechner;
DROP TABLE Team;
DROP TABLE Laborblatt;
DROP VIEW GlobaleView;

CREATE TABLE Laborblatt (
	LaborblattNr VARCHAR PRIMARY KEY  NOT NULL 
);

CREATE TABLE Team (
	TeamNr VARCHAR PRIMARY KEY  NOT NULL , 
	Passwort VARCHAR
);

CREATE TABLE Rechner (
	RechnerNr  PRIMARY KEY  NOT NULL ,
	IPAdresse VARCHAR ,
	Account VARCHAR,
	Passwort VARCHAR
);

CREATE TABLE Aufgabe (
	AufgabeNr VARCHAR PRIMARY KEY  NOT NULL , 
	LaborblattNr VARCHAR REFERENCES Laborblatt(LaborblattNr),
	AufgabeText VARCHAR
);

create table Laborslots (
    Slot varchar not null,
    Termin varchar,
	Belegt bool not null DEFAULT false,
	LaborblattNr VARCHAR REFERENCES Laborblatt(LaborblattNr),
    primary key (Slot)
);

CREATE TABLE Ergebnis (
	Rechner VARCHAR NOT NULL , 
	Eingabe VARCHAR, 
	Bestanden BOOL NOT NULL  DEFAULT false,
	TeamNr VARCHAR REFERENCES Team(TeamNr),
	AufgabeNr VARCHAR REFERENCES Aufgabe(AufgabeNr),
	Zeitstempel VARCHAR,
    primary key (TeamNr, AufgabeNr)
);

CREATE TABLE Loesung (
	Loesung VARCHAR,
	RechnerNr VARCHAR REFERENCES Rechner(RechnerNr),
	AufgabeNr VARCHAR REFERENCES Aufgabe(AufgabeNr),
	primary key (RechnerNr, AufgabeNr)
);

CREATE TABLE Einteilung (
	TeamNr VARCHAR REFERENCES Team(TeamNr),
	Slot VARCHAR REFERENCES Laborslots(Slot),
	primary key (TeamNr, Slot)
);

CREATE VIEW GlobaleView
AS SELECT Team.TeamNr, Ergebnis.Bestanden, Ergebnis.Zeitstempel, Ergebnis.AufgabeNr, Aufgabe.AufgabeText
FROM Team
INNER JOIN Ergebnis ON Ergebnis.TeamNr = Team.TeamNr
INNER JOIN Aufgabe ON Aufgabe.AufgabeNr = Ergebnis.AufgabeNr
;