package training_telegram_bot.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_CREDENTIAL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserCredential{

  @Id
  @SequenceGenerator(name = "generator", sequenceName = "id_seq")
  @GeneratedValue(generator = "generator", strategy = GenerationType.SEQUENCE)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME", length = 1024)
  private String username;

  @Column(name = "PASSSWORD", length = 1024)
  private String password;
}
