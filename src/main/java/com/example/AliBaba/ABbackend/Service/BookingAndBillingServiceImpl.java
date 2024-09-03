package com.example.AliBaba.ABbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AliBaba.ABbackend.DAO.BookingAndBillingDao;
import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetPayment;
import com.example.AliBaba.ABbackend.ORM.ORMGetReservations;
import com.example.AliBaba.ABbackend.ORM.ORMSavePayment;
import com.example.AliBaba.ABbackend.ORM.ORMSaveReservations;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

@Service
public class BookingAndBillingServiceImpl implements BookingAndBillingService{

	@Autowired
	private BookingAndBillingDao bookingAndBillingDao;
	
	@Override
	public ResponseStatus createReservation(ORMSaveReservations saveReservation) {
		// TODO Auto-generated method stub
		return bookingAndBillingDao.createReservation(saveReservation);
	}

	@Override
	public ORMGetReservations findReservation(Long reservationId) {
		// TODO Auto-generated method stub
		return bookingAndBillingDao.findReservation(reservationId);
	}

	@Override
	public ResponseStatus updateReservation(ORMSaveReservations saveReservation) {
		// TODO Auto-generated method stub
		return bookingAndBillingDao.updateReservation(saveReservation);
	}

	@Override
	public ResponseStatus deleteReservationRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		return bookingAndBillingDao.deleteReservationRecord(deleteRecord);
	}

	@Override
	public ResponseStatus createPayment(ORMSavePayment savePayment) {
		// TODO Auto-generated method stub
		return bookingAndBillingDao.createPayment(savePayment);
	}

	@Override
	public ORMGetPayment findPayment(Long paymentId) {
		// TODO Auto-generated method stub
		return bookingAndBillingDao.findPayment(paymentId);
	}

	@Override
	public ResponseStatus updatePayment(ORMSavePayment savePayment) {
		// TODO Auto-generated method stub
		return bookingAndBillingDao.updatePayment(savePayment);
	}

	@Override
	public ResponseStatus deletePaymentRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		return bookingAndBillingDao.deletePaymentRecord(deleteRecord);
	}

}
