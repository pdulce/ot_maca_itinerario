package giss.mad.marcocalidad.itinerariocalidad.model.auxpesos;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PesosEje {
    private List<Weight> pesos = new ArrayList<>();

    public List<Weight> getPesos() {
        return pesos;
    }

    public void setPesos(List<Weight> pesos) {
        this.pesos = pesos;
    }

    public List<Integer> getAxisList(Integer idAxis, RestTemplate restTemplate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String jsonString = restTemplate.exchange("/catalogo/valordominio/getByAttributeId/" + idAxis,
                HttpMethod.GET, entity, String.class).getBody();

        JSONArray jsonArr = new JSONArray(jsonString);
        List<Integer> axisIds = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++){
            Integer id = (Integer) jsonArr.getJSONObject(i).get("id");
            axisIds.add(id);
        }
        return axisIds;
    }

    public PesosEje (RestTemplate restTemplate, Integer idAxis, List<Integer> weights) {

        List<Integer> valoresDominioOfAxisIds = getAxisList(idAxis, restTemplate);
        if (weights.size() > valoresDominioOfAxisIds.size()) {
            throw new RuntimeException("Ha introducido + pesos que valores de dominio hay definidos para este eje <id: " + idAxis + ">");
        }
        List<Weight> pesos = new ArrayList<>();
        for (int i = 0; i < weights.size(); i++) {
            pesos.add(new Weight(idAxis, valoresDominioOfAxisIds.get(i), weights.get(i)));
        }
        this.setPesos(pesos);
    }
}
