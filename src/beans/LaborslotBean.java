package beans;
import management.LaborslotManagement;

public class LaborslotBean
{
  public String getDateString()
  {
	  LaborslotManagement management = new LaborslotManagement();
	  management.DeleteLaborSlot("1");
	  management.SaveLaborSlot("1", false, "2016-04-12");	
	  management.SaveLaborSlot("1", true, "2016-04-19", "1");
	  management.DeleteLaborSlot("3");
	  management.SaveLaborSlot("3", false, "2016-04-12");	
	  management.SaveLaborSlot("3", true, "2016-04-19", "3");
	  return management.ReadAllLaborSlots();
   }
}