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
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserWriteBot {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_write_generator")
  @SequenceGenerator(name = "user_write_generator", sequenceName = "user_write_sequence", allocationSize = 3)
  @Column
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @CreatedDate
  @Column
  private LocalDateTime dateMessage;

  @Column
  private String buttonName;

  @PrePersist
  public void onCreate() {
    this.dateMessage = LocalDateTime.now(ZoneId.of("UTC+3"));
  }
}
