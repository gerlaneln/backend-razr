package br.ufac.productmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.config.ChartData;
import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.ProductScope;
import br.ufac.productmanager.model.Team;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.service.ProductScopeService;
import br.ufac.productmanager.service.ProductService;
import br.ufac.productmanager.service.TeamFormationService;
import br.ufac.productmanager.service.UserService;

@RestController
@RequestMapping("/chart_data")
public class ChartDataController {

    private final UserService userService;
    private final TeamFormationService formationService;
    private final ProductService productService;
    private final ProductScopeService scopeService;
    
    @Autowired
    public ChartDataController(UserService service,
            TeamFormationService formationService,
            ProductService productService,
            ProductScopeService scopeService) {
        
        this.userService = service;
        this.formationService = formationService;
        this.productService = productService;
        this.scopeService = scopeService;
    }
    
    @GetMapping("/load_chart/{id}")
    public ResponseEntity<ChartData> loadDataChart(@PathVariable("id") Long id){
        User loggedUser = userService.getById(id);
        List<Team> teams = formationService.getByUser(loggedUser);
        
        List<Product> products = new ArrayList<Product>();
        for(Team t : teams) {
           products.addAll(productService.getByTeam(t));
        }
        
        //getting the scopes of that products
        List<ProductScope> scopes = new ArrayList<ProductScope>();
        for(Product p : products) {
            Long idProduct = p.getId();
            scopes.add(scopeService.getByProduct(idProduct));
        }
        
        
        ChartData chartData = new ChartData(scopes);
        return new ResponseEntity<>(chartData, HttpStatus.OK);
    }
}
