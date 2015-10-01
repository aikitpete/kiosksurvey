package com.petegerhat.kiosksurvey;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class KioskSurveyController {

	private static List<String> group1;
	private static List<String> group2;

	static {
		group1 = new ArrayList();
		group2 = new ArrayList();
		group1.add("SE");
		group2.add("JP");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void initialize(ModelMap model) {

		/*SessionFactory sessionFactory = DatabaseConnection.getSessionFactory();
		Session session = sessionFactory.openSession();

		Participants participants = new Participants(session.createQuery("FROM Participant").list());
		Participants group1Participants = new Participants(participants.getParticipantsByCountries(group1));
		Participants group2Participants = new Participants(participants.getParticipantsByCountries(group2));
		model.addAttribute("participants", participants);
		model.addAttribute("group1Participants", group1Participants);
		model.addAttribute("group2Participants", group2Participants);
		model.addAttribute("allGroup1Participants", group1Participants.getParticipants());
		model.addAttribute("allGroup2Participants", group2Participants.getParticipants());
		model.addAttribute("seriousGroup1Participants", group1Participants.getParticipantsByExpression("serious"));
		model.addAttribute("seriousGroup2Participants", group2Participants.getParticipantsByExpression("serious"));
		model.addAttribute("smilingGroup1Participants", group1Participants.getParticipantsByExpression("smiling"));
		model.addAttribute("smilingGroup2Participants", group2Participants.getParticipantsByExpression("smiling"));

		Records records = new Records(session.createQuery("FROM Record").list());
		model.addAttribute("records", records);

		setModelAttributes(model, group1Participants);
		setModelAttributes(model, group2Participants);

		session.close();*/

		/*model.addAttribute("participants", participants);
		model.addAttribute("group1Participants", group1Participants);
		model.addAttribute("group2Participants", group2Participants);
		model.addAttribute("allGroup1Participants", group1Participants.getParticipants());
		model.addAttribute("allGroup2Participants", group2Participants.getParticipants());
		model.addAttribute("seriousGroup1Participants", group1Participants.getParticipantsByExpression("serious"));
		model.addAttribute("seriousGroup2Participants", group2Participants.getParticipantsByExpression("serious"));
		model.addAttribute("smilingGroup1Participants", group1Participants.getParticipantsByExpression("smiling"));
		model.addAttribute("smilingGroup2Participants", group2Participants.getParticipantsByExpression("smiling"));*/
		//model.addAttribute("records", records);
		model.addAttribute("SEName", "Sweden");
		/*model.addAttribute("SETotal", group.getTotal());
		model.addAttribute("SETotalValid", group.getTotalValid());
		model.addAttribute("SEMaleTotal", group.getMaleTotal());
		model.addAttribute("SEMaleTotalValid", group.getMaleTotalValid());
		model.addAttribute("SEFemaleTotal", group.getFemaleTotal());
		model.addAttribute("SEFemaleTotalValid", group.getFemaleTotalValid());
		model.addAttribute("SEPercentageValid", group.getPercentageValid());
		model.addAttribute("SEAverageAge", group.getAverageAge());
		model.addAttribute("SEAverageAgeValid", group.getAverageAgeValid());
		model.addAttribute("SEAverageDuration", group.getAverageDuration());
		model.addAttribute("SEAverageDurationValid", group.getAverageDurationValid());*/
		model.addAttribute("JPName", "Japan");
		/*model.addAttribute("JPTotal", group.getTotal());
		model.addAttribute("JPTotalValid", group.getTotalValid());
		model.addAttribute("JPMaleTotal", group.getMaleTotal());
		model.addAttribute("JPMaleTotalValid", group.getMaleTotalValid());
		model.addAttribute("JPFemaleTotal", group.getFemaleTotal());
		model.addAttribute("JPFemaleTotalValid", group.getFemaleTotalValid());
		model.addAttribute("JPPercentageValid", group.getPercentageValid());
		model.addAttribute("JPAverageAge", group.getAverageAge());
		model.addAttribute("JPAverageAgeValid", group.getAverageAgeValid());
		model.addAttribute("JPAverageDuration", group.getAverageDuration());
		model.addAttribute("JPAverageDurationValid", group.getAverageDurationValid());*/
		
		return;

	}

	private void setModelAttributes(ModelMap model, Participants group) {
		String groupName = group.getName();
		model.addAttribute("JPName", group.getName());
		model.addAttribute("JPTotal", group.getTotal());
		model.addAttribute("JPTotalValid", group.getTotalValid());
		model.addAttribute("JPMaleTotal", group.getMaleTotal());
		model.addAttribute("JPMaleTotalValid", group.getMaleTotalValid());
		model.addAttribute("JPFemaleTotal", group.getFemaleTotal());
		model.addAttribute("JPFemaleTotalValid", group.getFemaleTotalValid());
		//model.addAttribute("JPPercentage", group.getPercentage());
		model.addAttribute("JPPercentageValid", group.getPercentageValid());
		model.addAttribute("JPAverageAge", group.getAverageAge());
		model.addAttribute(groupName+ "AverageAgeValid", group.getAverageAgeValid());
		model.addAttribute("JPAverageDuration", group.getAverageDuration());
		model.addAttribute("JPAverageDurationValid", group.getAverageDurationValid());
	}

}