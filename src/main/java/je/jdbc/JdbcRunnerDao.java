package je.jdbc;

import je.jdbc.dao.TicketDao;
import je.jdbc.dto.TicketFilter;
import je.jdbc.entity.Ticket;

import java.math.BigDecimal;
import java.sql.SQLException;

public class JdbcRunnerDao {

    public static void main(String[] args) throws SQLException {
        TicketDao ticketDao = TicketDao.getInstance();
//        Ticket ticket = new Ticket(null, null, null, null, null, null);
//        ticket.setPassangerNo("adf123");
//        ticket.setPassangerName("Andrei");
//        ticket.setFlightId(4L);
//        ticket.setSeatNo("5B");
//        ticket.setCost(BigDecimal.TEN);
//
////        System.out.println(ticketDao.save(ticket));
//        System.out.println(ticketDao.delete(56L));
//        System.out.println(ticketDao.findAll());
//        System.out.println(ticketDao.findById(5L).get());
//        Ticket ticket = ticketDao.findById(5L).get();
//        System.out.println(ticket);
//        ticket.setSeatNo("40D");
//        System.out.println(ticketDao.update(ticket));
//        System.out.println(ticket);

//        TicketDao ticketDao1 = TicketDao.getInstance();
        TicketFilter ticketFilter = new TicketFilter("Иван Иванов", "A1", 5, 0);
        System.out.println(ticketDao.findAll(ticketFilter));
    }
}
