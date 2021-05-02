import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Grupo E
 */
public class Chat {
    private Conta sender;
    private Conta receiver;
    private String data;
    private String msg;

    public Conta getSender(){ return this.sender; }

    public Conta getReceiver(){ return this.receiver; }

    public String getMsg() { return msg; }

    public String getData() { return data; }

    public Chat(Conta sender, Conta receiver, String msg) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        this.data = dtf.format(now);
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
    }

    public Chat(Conta sender, Conta receiver, String data ,String msg) {
        this.data = data;
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
    }
}