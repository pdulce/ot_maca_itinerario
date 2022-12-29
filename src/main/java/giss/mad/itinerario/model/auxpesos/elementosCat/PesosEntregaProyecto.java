package giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.elementosCat;

import giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.IPesosActividades;
import giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.PesosEje;
import giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.Weight;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PesosEntregaProyecto implements IPesosActividades {
    @Override
    public Map<Integer, List<Weight>> getPesosPorActividades(RestTemplate restTemplate) {

        Map<Integer, List<Weight>> mapaDeActividadesConPesos = new HashMap<>();
        List<List<Weight>> listaPesosActividades = new ArrayList<>();
        for (int i=0;i<ACTIVITY.length-1;i++){
            listaPesosActividades.add(new ArrayList<>());
        }
        int number_activity = 0;

        mapaDeActividadesConPesos.put(ACTIVITY[1], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[2], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[3], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[4], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[5], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[6], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[7], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[8], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[9], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[10], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[11], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[12], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[13], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[14], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[15], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[16], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[17], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[18], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[19], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[20], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[21], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[22], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[23], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[24], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[25], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[26], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[27], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[28], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(5,10,20)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[29], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(30,20,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(5,10,20)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[30], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[31], listaPesosActividades.get(number_activity));

        return mapaDeActividadesConPesos;
    }
}
