package training_telegram_bot.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class MemoryStats {

  private long heapSize;
  private long heapMaxSize;
  private long heapFreeSize;
}
