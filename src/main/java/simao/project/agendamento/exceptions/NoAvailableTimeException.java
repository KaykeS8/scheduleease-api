package simao.project.agendamento.exceptions;

public class NoAvailableTimeException extends RuntimeException {
    public NoAvailableTimeException(String message) {
        super(message);
    }
}
