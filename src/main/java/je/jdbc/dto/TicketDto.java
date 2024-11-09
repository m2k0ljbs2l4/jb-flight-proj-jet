package je.jdbc.dto;

public record TicketDto(Long id,
                        Long flightId,
                        String seatNo) {
}
