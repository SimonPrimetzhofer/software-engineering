package airside.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Log
public class Pilot {
	ArrayList<Boolean> checklist = new ArrayList<>();

	public boolean doChecklist() {
		log.info("start going through take off checklist");
		checklist.stream().forEach(s -> s = Boolean.TRUE);
		log.info("checklist done");
		return true;
	}

	public void requestStartPermission() {

	}

}
