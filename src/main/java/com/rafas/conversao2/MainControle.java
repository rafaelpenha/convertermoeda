package com.rafas.conversao2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;

@Controller
public class MainControle {
    double real;
    double dolar;
    double cotacao = 5;


    @GetMapping()
    public String inicio(Model model) {
        RestTemplate rest = new RestTemplate();

        List<Map> resp = (List<Map>) rest.getForObject("https://economia.awesomeapi.com.br/USD-BRL/", List.class);

        Map map = resp.get(0);
        String bid = (String)map.get("bid");
        cotacao = Double.valueOf(bid);
        model.addAttribute("real", real);
        model.addAttribute("cotacao", cotacao);
        model.addAttribute( "dolar", dolar);
        model.addAttribute("formulario",new Formulario());
        model.addAttribute("formulario2",new Formulario2());
        return "index";
    }

    @PostMapping("/enviar")
    public String enviar (Formulario formulario){
        int p = Integer.valueOf(formulario.valor);
        dolar = (p * cotacao);
        return "redirect:/";
    }
    @PostMapping("/enviar2")
    public String enviar2 (Formulario2 formulario2){
        int r = Integer.valueOf(formulario2.valor2);
        real = (r / cotacao);

        return  "redirect:/";

    }

}
