package training_telegram_bot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "USER_WRITE_BOT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserWriteBot {

  @Id
  @SequenceGenerator(name = "generator", sequenceName = "id_seq", allocationSize = 1)
  @GeneratedValue(generator = "generator", strategy = GenerationType.SEQUENCE)
  @Column(name = "ID")
  private Long id;

  @Column(name = "FIRST_NAME", length = 1024)
  private String firstName;

  @Column(name = "LAST_NAME", length = 1024)
  private String lastName;

  @CreatedDate
  @Column(name = "DATE_MESSAGE", nullable = false, updatable = false)
  private LocalDateTime dateMessage;

  @Column(name = "BUTTON_NAME", length = 1024)
  private String buttonName;

  @PrePersist
  public void onCreate() {
    this.dateMessage = LocalDateTime.now(ZoneId.of("UTC+3"));
  }
}
