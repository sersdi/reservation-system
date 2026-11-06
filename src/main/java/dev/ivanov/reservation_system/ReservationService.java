package dev.ivanov.reservation_system;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservationService {

    private final Map<Long, Reservation> reservationMap;

    private final AtomicLong idCounter; //AtomicLong тк могут быть несколько параллельных запросов(что бы счётчик работал корректно для разных потоков)

    public ReservationService() {
        reservationMap = new HashMap<>();
        idCounter = new AtomicLong();
    }

    public Reservation getReservationById(Long id) {
       if (!reservationMap.containsKey(id)) {
           throw new NoSuchElementException("Not found reservation by id = " + id);
       }
       return reservationMap.get(id);
    }


    public List<Reservation> findAllReservations() {
        return reservationMap.values().stream().toList();
    }

   public Reservation createReservation(Reservation reservationToCreate) {
        if (reservationToCreate.id() !=null){
            throw new IllegalArgumentException("Id should be null for new reservation");
        }
        if(reservationToCreate.status() !=null){
            throw new IllegalArgumentException("Status should be null for new reservation");
        }
        var newReservation = new Reservation(
                idCounter.incrementAndGet(),
                reservationToCreate.userId(),
                reservationToCreate.roomId(),
                reservationToCreate.startDate(),
                reservationToCreate.endDate(),
                ReservationStatus.PENDING
        );
        reservationMap.put(newReservation.id(), newReservation);
        return newReservation;
   }

    public Object updateReservation(
            Long id,
            Reservation reservationToUpdate
    ) {
        if(!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("Not found reservation by id = " + id);
        }

        var reservation =  reservationMap.get(id);
        if(reservation.status() != ReservationStatus.PENDING) {
            throw new IllegalArgumentException("Status should be PENDING for new reservation: status=" + reservation.status());
        }

        var updatedReservation = new Reservation(
                reservation.id(),
                reservationToUpdate.userId(),
                reservationToUpdate.roomId(),
                reservationToUpdate.startDate(),
                reservationToUpdate.endDate(),
                ReservationStatus.PENDING
        );
        reservationMap.put(updatedReservation.id(), updatedReservation);
        return updatedReservation;
    }

    public void deleteReservation(Long id) {
        if(!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("Not found reservation by id = " + id);
        }
        reservationMap.remove(id);
    }
}
