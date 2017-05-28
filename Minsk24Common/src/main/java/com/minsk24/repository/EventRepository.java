package com.minsk24.repository;

import com.minsk24.bean.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
    List<Event> findByTimeBetween(Date start, Date end, Pageable pageable);
    List<Event> findByLocationIgnoreCaseContaining(String location, Pageable pageable);
    Integer countByTimeBetween(Date start, Date end);
    Integer countByLocationIgnoreCaseContaining(String location);
    List<Event> findByTimeGreaterThan(Date today, Pageable pageable);
    @Query(value = "SELECT EV.* FROM event EV LEFT JOIN " +
            "  (SELECT A.EVENT_ID, COALESCE(A.WILL_GO_COUNT, 0) - COALESCE(B.WONT_GO_COUNT, 0) AS STATISTICS " +
            "   FROM " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WILL_GO_COUNT FROM before_event_rate " +
            "WHERE USER_CHOICE='WILL_GO' GROUP BY EVENT_ID) A LEFT JOIN " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WONT_GO_COUNT FROM before_event_rate " +
            "WHERE USER_CHOICE='WONT_GO' GROUP BY EVENT_ID) B ON A.EVENT_ID=B.EVENT_ID " +
            "UNION " +
            "SELECT A.EVENT_ID, COALESCE(A.WILL_GO_COUNT, 0) - COALESCE(B.WONT_GO_COUNT, 0) AS STATISTICS " +
            "FROM " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WILL_GO_COUNT FROM before_event_rate " +
            "  WHERE USER_CHOICE='WILL_GO' GROUP BY EVENT_ID) A RIGHT JOIN " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WONT_GO_COUNT FROM before_event_rate " +
            "  WHERE USER_CHOICE='WONT_GO' GROUP BY EVENT_ID) B ON A.EVENT_ID=B.EVENT_ID) A " +
            "ON EV.E_ID=A.EVENT_ID WHERE EV.TIME>=NOW() ORDER BY A.STATISTICS DESC LIMIT :startIndex, :endIndex", nativeQuery = true)
    List<Event> findTopRatedUpcomingEvents(@Param(value = "startIndex") Integer startIndex,
                                           @Param(value = "endIndex") Integer endIndex);
    @Query(value = "SELECT EV.* FROM event EV LEFT JOIN " +
            "  (SELECT A.EVENT_ID, COALESCE(A.WILL_GO_COUNT, 0) - COALESCE(B.WONT_GO_COUNT, 0) AS STATISTICS " +
            "   FROM " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WILL_GO_COUNT FROM before_event_rate " +
            "WHERE USER_CHOICE='WILL_GO' GROUP BY EVENT_ID) A LEFT JOIN " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WONT_GO_COUNT FROM before_event_rate " +
            "WHERE USER_CHOICE='WONT_GO' GROUP BY EVENT_ID) B ON A.EVENT_ID=B.EVENT_ID " +
            "UNION " +
            "SELECT A.EVENT_ID, COALESCE(A.WILL_GO_COUNT, 0) - COALESCE(B.WONT_GO_COUNT, 0) AS STATISTICS " +
            "FROM " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WILL_GO_COUNT FROM before_event_rate " +
            "  WHERE USER_CHOICE='WILL_GO' GROUP BY EVENT_ID) A RIGHT JOIN " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WONT_GO_COUNT FROM before_event_rate " +
            "  WHERE USER_CHOICE='WONT_GO' GROUP BY EVENT_ID) B ON A.EVENT_ID=B.EVENT_ID) A " +
            "ON EV.E_ID=A.EVENT_ID WHERE EV.TIME>=NOW() AND TIME>=:startTime AND TIME<=:endTime ORDER BY A.STATISTICS DESC LIMIT :startIndex, :endIndex", nativeQuery = true)
    List<Event> findTopRatedUpcomingEventsByTime(@Param(value = "startTime") String startTime,
                                                 @Param(value = "endTime") String endTime,
                                                 @Param(value = "startIndex") Integer startIndex,
                                           @Param(value = "endIndex") Integer endIndex);
    @Query(value = "SELECT EV.* FROM event EV LEFT JOIN " +
            "  (SELECT A.EVENT_ID, COALESCE(A.WILL_GO_COUNT, 0) - COALESCE(B.WONT_GO_COUNT, 0) AS STATISTICS " +
            "   FROM " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WILL_GO_COUNT FROM before_event_rate " +
            "WHERE USER_CHOICE='WILL_GO' GROUP BY EVENT_ID) A LEFT JOIN " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WONT_GO_COUNT FROM before_event_rate " +
            "WHERE USER_CHOICE='WONT_GO' GROUP BY EVENT_ID) B ON A.EVENT_ID=B.EVENT_ID " +
            "UNION " +
            "SELECT A.EVENT_ID, COALESCE(A.WILL_GO_COUNT, 0) - COALESCE(B.WONT_GO_COUNT, 0) AS STATISTICS " +
            "FROM " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WILL_GO_COUNT FROM before_event_rate " +
            "  WHERE USER_CHOICE='WILL_GO' GROUP BY EVENT_ID) A RIGHT JOIN " +
            "  (SELECT EVENT_ID, COUNT(USER_CHOICE) AS WONT_GO_COUNT FROM before_event_rate " +
            "  WHERE USER_CHOICE='WONT_GO' GROUP BY EVENT_ID) B ON A.EVENT_ID=B.EVENT_ID) A " +
            "ON EV.E_ID=A.EVENT_ID WHERE EV.TIME>=NOW() AND LOCATION LIKE :location ORDER BY A.STATISTICS DESC LIMIT :startIndex, :endIndex", nativeQuery = true)
    List<Event> findTopRatedUpcomingEventsByLocation(@Param(value = "location") String location,
                                           @Param(value = "startIndex") Integer startIndex,
                                           @Param(value = "endIndex") Integer endIndex);
}
