package training_telegram_bot.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_WRITE_BOT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserWriteBot {

  @Id
  @SequenceGenerator(name = "generator", sequenceName = "id_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "ID")
  private Long id;

  @Column(name = "FIRST_NAME", length = 1024)
  private String firstName;

  @Column(name = "LAST_NAME", length = 1024)
  private String lastName;

  @CreationTimestamp
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DATE_MESSAGE")
  private Date dateMessage;

  @Column(name = "BUTTON_NAME", length = 1024)
  private String buttonName;
}
