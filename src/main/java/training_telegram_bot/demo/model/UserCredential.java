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
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSSWORD")
  private String password;
}
