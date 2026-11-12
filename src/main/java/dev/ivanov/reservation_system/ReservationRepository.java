package dev.ivanov.reservation_system;

//import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import java.time.LocalDate;
//import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

//    Спринг по названию метода понимает что нужно сделать тк extends JpaRepository
//    List<ReservationEntity> findAllByStatusIs(ReservationStatus status);
//    List<ReservationEntity> findAllByEndDateAndRoomIdAndStartDate(... ... ... ...);

//  Делаю запрос сам через аннотацию @Query(язык JPQL, но можно и на sql)
//  @Query("select r from ReservationEntity r where r.status = :status") Язык JPQL

//    @Query(value = "select * from reservations r where r.status = :status", nativeQuery = true ) // Язык sql
//    List<ReservationEntity> findAllByStatusIs(ReservationStatus status);
//
//    @Query("select r from ReservationEntity r where r.roomId = :roomId")
//    List<ReservationEntity> findAllByRoomId(@Param("roomId") Long roomId);
//
//    @Transactional  // пишем аннотацию @Transactional и @Modifying всегда если хотим послать запрос на обновление
//    @Modifying
//    @Query("""
//            update ReservationEntity r
//            set r.userId = :userId,
//                r.roomId = :roomId,
//                r.startDate = :startDate,
//                r.endDate = :endDate,
//                r.status = :status
//            where r.id =:id
//            """
//    )
//    int updateAllFields(
//            @Param("id") Long id,
//            @Param("userId") Long userId,
//            @Param("roomId") Long roomId,
//            @Param("startDate") LocalDate startDate,
//            @Param("endDate") LocalDate endDate,
//            @Param("status") ReservationStatus status
//    );

    @Modifying
    @Query("""
            update ReservationEntity r
            set r.status = :status
            where r.id =:id
            """)
    void setStatus(@Param("id")Long id, @Param("status")ReservationStatus reservationStatus);
}
