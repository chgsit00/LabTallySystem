package meinbeanpackage;

import management.LoesungManagement;

public class LoesungBean
{
	public String doAction(){
		LoesungManagement management = new LoesungManagement();
		management.DeleteLoesung("1", "1");
		management.SaveLoesung("1", "1", "PI");
		management.SaveLoesung("1", "1", "PI/2");
		return management.ReadAllLoesungen();
	}	
}
