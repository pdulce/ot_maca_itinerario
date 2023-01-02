package giss.mad.itinerario.model.auxpesos;

import giss.mad.itinerario.service.Constantes;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public interface IPesosActividades {

  public final static int[] AXE = {Constantes.NUMBER_0, Constantes.NUMBER_1, Constantes.NUMBER_2,
      Constantes.NUMBER_3, Constantes.NUMBER_4, Constantes.NUMBER_5, Constantes.NUMBER_6,
      Constantes.NUMBER_7, Constantes.NUMBER_8, Constantes.NUMBER_9, Constantes.NUMBER_10,
      Constantes.NUMBER_11, Constantes.NUMBER_12, Constantes.NUMBER_13, Constantes.NUMBER_14,
      Constantes.NUMBER_15, Constantes.NUMBER_16, Constantes.NUMBER_17, Constantes.NUMBER_18,
      Constantes.NUMBER_19, Constantes.NUMBER_20, Constantes.NUMBER_21, Constantes.NUMBER_22};
  public final static int[] ACTIVITY = {Constantes.NUMBER_0, Constantes.NUMBER_1, Constantes.NUMBER_2,
      Constantes.NUMBER_3, Constantes.NUMBER_4, Constantes.NUMBER_5, Constantes.NUMBER_6,
      Constantes.NUMBER_7, Constantes.NUMBER_8, Constantes.NUMBER_9, Constantes.NUMBER_10,
      Constantes.NUMBER_11, Constantes.NUMBER_12, Constantes.NUMBER_13, Constantes.NUMBER_14,
      Constantes.NUMBER_15, Constantes.NUMBER_16, Constantes.NUMBER_17, Constantes.NUMBER_18,
      Constantes.NUMBER_19, Constantes.NUMBER_20, Constantes.NUMBER_21, Constantes.NUMBER_22,
      Constantes.NUMBER_23, Constantes.NUMBER_24, Constantes.NUMBER_5, Constantes.NUMBER_26,
      Constantes.NUMBER_27, Constantes.NUMBER_28, Constantes.NUMBER_29, Constantes.NUMBER_30,
      Constantes.NUMBER_31};

  public Map<Integer, List<Weight>> getPesosPorActividades(final RestTemplate restTemplate);
}
