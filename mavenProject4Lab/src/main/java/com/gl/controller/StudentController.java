package com.gl.controller;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.dbmodel.Bike;
import com.gl.dbmodel.Student;

@Controller
public class StudentController {

	//----------------home page-------------------------//
	@RequestMapping("/")
	public String welcome() {
		return "welcome";
	}
    //-----------------------------insert-----------------------//
	@RequestMapping("/insert")
	public String insert() {
		return "Student-insert";
	}

	@PostMapping("/insert-param")
	public String insertParam(@RequestParam String examNo, @RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {

		SessionFactory factory = new Configuration ().configure().buildSessionFactory();

		Session session =factory.openSession();

		try {
			Transaction tx = session.beginTransaction();

			Student s1=new Student(examNo, firstName, lastName, age);
			session.save(s1);
			tx.commit();

		}
		catch(Exception ex) {
			System.out.println("hibernate error: "+ex.getMessage());
		}

		return "welcome";

	}
	
	//----------------------------------Display----------------------------------//
	@RequestMapping("show")
	public String showStudent(Model data) {
		
		SessionFactory factory = new Configuration ().configure().buildSessionFactory();

		Session session =factory.openSession();
		
		try {
			Query q1=session.createQuery("from Student");
			List<Student> bike = q1.getResultList();
			data.addAttribute("stud", bike);
		}
		catch(Exception ex) {
			System.out.println("hibernate error: "+ex.getMessage());
		}
		return "show-student";
			
	}
	
	//---------------------update-------------------------------//

	@GetMapping("/update-student")
	public String updateStudentForm(@RequestParam String examNo, Model data) {
		
		SessionFactory factory = new Configuration ().configure().buildSessionFactory();

		Session session =factory.openSession();
		try {
			//
			Student updatestudent = session.get(Student.class, examNo);
			data.addAttribute("stu",updatestudent);
		}
		catch(Exception ex) {
			System.out.println("hibernate error: "+ex.getMessage());
		}
		return "updateStudent";
		
	}
	
	
	@PostMapping("/update")
	public String  update(@RequestParam String examNo, @RequestParam String firstName, @RequestParam String lastName, @RequestParam int age  ) {

		SessionFactory factory = new Configuration ().configure().buildSessionFactory();

		Session session =factory.openSession();

		try {

			Transaction tx = session.beginTransaction();

		
			Student s1=new Student(examNo, firstName, lastName, age);
			session.update(s1);
			
		tx.commit();



		}
		catch(Exception ex) {
			System.out.println("hibernate error: "+ex.getMessage());
		}

		return "welcome";

	}
	//--------------------------------------Delete--------------------------------------------//
	
	@GetMapping("/delete-student")
	public String deleteBike(@RequestParam String examNo, Model data) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		try {
			Transaction tx=session.beginTransaction();
			Student deletestu = new Student(examNo,"","",0);
			session.delete(deletestu);
			tx.commit();
			
			
		}
		catch(Exception ex) {
			System.out.println("hibernate error: "+ex.getMessage());
		}
		return "welcome";
	}

}
