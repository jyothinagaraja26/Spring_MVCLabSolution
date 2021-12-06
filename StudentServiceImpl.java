package com.greatlearning.studentmanagmentforfest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.greatlearning.studentmanagmentforfest.entities.Students;

@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;
	// create session
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	@Override
	public List<Students> findAll() {
		// TODO Auto-generated method stub

		Transaction tx = session.beginTransaction();

		// find all the records from the database table

		List<Students> students = session.createQuery("from Students").list();
		tx.commit();
		return students;
	}

	@Transactional
	@Override
	public Students findById(int theId) {
		// TODO Auto-generated method stub
		Students student = new Students();
		Transaction tx = session.beginTransaction();
		// find record with id from the database table
		student = session.get(Students.class, theId);
		tx.commit();
		return student;
	}

	@Transactional
	@Override
	public void save(Students theStudent) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		// save transaction
		session.save(theStudent);
		tx.commit();

	}

	@Transactional
	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();

		// get transaction
		Students student = session.get(Students.class, theId);
		// delete record
		session.delete(student);
		tx.commit();

	}

}
