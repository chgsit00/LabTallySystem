package meinbeanpackage;

import database.*;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.LaborslotManagement;

import java.sql.Connection;
import java.sql.SQLException;

public class MeineJavaBean
{
  public String getDateString()
  {
	  LaborslotManagement management = new LaborslotManagement();
	  management.DeleteLaborSlot("1");
	  management.SaveLaborSlot("1", false, "2016-04-12");	
	  management.SaveLaborSlot("1", true, "2016-04-19");
	  return management.ReadAllLaborSlots();
   // return (new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss")).format(new Date()) + " h";
  }
}