package giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.elementosCat;

import giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.PesosEje;
import giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.Weight;
import giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.IPesosActividades;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PesosEntregaElementoPromocionable implements IPesosActividades {

    @Override
    public final Map<Integer, List<Weight>> getPesosPorActividades(RestTemplate restTemplate){
        Map<Integer, List<Weight>> mapaDeActividadesConPesos = new HashMap<>();
        List<List<Weight>> listaPesosActividades = new ArrayList<>();
        for (int i=0;i<ACTIVITY.length-1;i++){
            listaPesosActividades.add(new ArrayList<>());
        }
        int number_activity = 0;


        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(0,0,-1,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[1], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(0,0,-1,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[2], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[7],  /*pesos*/List.of(-1,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(10,7,-1,-1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(5,15,10,15,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[3], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[7],  /*pesos*/List.of(-1,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(10,5,1,-1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(1,10,-1,10,5,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[4], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[9],  /*pesos*/List.of(0,0,0,0,0,0,0,0,0,0,0,-1,-1,-1,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(20,10,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(5,20,10,20,10,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[5], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[9],  /*pesos*/List.of(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(20,10,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(5,20,10,20,10,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[6], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[7], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(40,-1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(-1,-1,-1,-1,20,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[8], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(40,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(40,10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(20,15,5,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(1,20,10,20,10,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[9], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(20,15,5,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,10,10,10,0,0)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[10], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[7],  /*pesos*/List.of(0,10)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(20,15,5,-1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,20,10,20,5,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[11], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[7],  /*pesos*/List.of(10,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(10,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(-1,10,1,-1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,20,10,20,5,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[12], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(10,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(10,10,5,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(20,20,30,20,40,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[13], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(30,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(10,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(10,10,5,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(20,20,30,20,40,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[14], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(30,20,30,30,40,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[15], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[16], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(30,20,30,30,40,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[17], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,20,10,20,30,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[18], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,20,10,20,30,10)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[19], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(30,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(10,8,7,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,10,5,20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[20], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(30,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(10,8,7,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,10,5,20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[21], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(30,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[18],  /*pesos*/List.of(10,8,7,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,10,5,20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[22], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[23], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[1],  /*pesos*/List.of(20,1)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[9],  /*pesos*/List.of(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(20,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(20,10,5)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[24], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,-1,-1,10,-1,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[25], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,-1,-1,10,-1,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[26], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,-1,-1,10,-1,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[27], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(10,-1,-1,10,-1,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[28], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[9],  /*pesos*/List.of(0,0,0,0,0,-1,0,-1,0,0,0,0,0,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(5,10,20)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(30,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(5,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(5,20,-1,20,20,-1)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[29], listaPesosActividades.get(number_activity));
        number_activity++;

        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[13],  /*pesos*/List.of(30,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[14],  /*pesos*/List.of(5,10,20)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[15],  /*pesos*/List.of(30,5)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[16],  /*pesos*/List.of(5,0)).getPesos());
        listaPesosActividades.get(number_activity).addAll(new PesosEje(restTemplate, AXE[19],  /*pesos*/List.of(5,20,-1,20,20,0)).getPesos());
        mapaDeActividadesConPesos.put(ACTIVITY[30], listaPesosActividades.get(number_activity));
        number_activity++;

        mapaDeActividadesConPesos.put(ACTIVITY[31], listaPesosActividades.get(number_activity));


        /****fin de definiciÃ³n de pesos de atividades de este elemento promocionable *****/

        return mapaDeActividadesConPesos;

    }
}
