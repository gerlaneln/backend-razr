package br.ufac.productmanager.controller;

import org.springframework.http.ResponseEntity;

import br.ufac.productmanager.model.Chipset;
import br.ufac.productmanager.model.DistribuitionModel;
import br.ufac.productmanager.model.LifeCycleStatus;
import br.ufac.productmanager.model.PhoneCompany;
import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.ProductFamily;
import br.ufac.productmanager.model.ProductScope;
import br.ufac.productmanager.model.Region;
import br.ufac.productmanager.model.Team;
import br.ufac.productmanager.model.TeamFormation;
import br.ufac.productmanager.model.Upgrade;
import br.ufac.productmanager.model.User;

public interface ICrudLog {

    public ResponseEntity<User> insertUser(Long id, User object);
    public ResponseEntity<Chipset> insertChipset(Long id, Chipset object);
    public ResponseEntity<DistribuitionModel> insertModel(Long id, DistribuitionModel object);
    public ResponseEntity<LifeCycleStatus> insertLifeCycle(Long id, LifeCycleStatus object);
    public ResponseEntity<PhoneCompany> insertPhoneCompany(Long id, PhoneCompany object);
    public ResponseEntity<ProductFamily> insertFamily(Long id, ProductFamily object);
    public ResponseEntity<ProductScope> insertProductScope(Long id, ProductScope object);
    public ResponseEntity<Product> insertProduct(Long id, Product object);
    public ResponseEntity<Region> insertRegion(Long id, Region object);
    public ResponseEntity<TeamFormation> insertTeamFormation(Long id, TeamFormation object);
    public ResponseEntity<Team> insertTeam(Long id, Team object);
    public ResponseEntity<Upgrade> insertUpgrade(Long id, Upgrade object);

    //update methods, PUT
    public ResponseEntity<User> updateUser(Long id, User object);
    public ResponseEntity<Chipset> updateChipset(Long id, Chipset object);
    public ResponseEntity<DistribuitionModel> updateModel(Long id, DistribuitionModel object);
    public ResponseEntity<LifeCycleStatus> updateLifeCycle(Long id, LifeCycleStatus object);
    public ResponseEntity<PhoneCompany> updatePhoneCompany(Long id, PhoneCompany object);
    public ResponseEntity<ProductFamily> updateFamily(Long id, ProductFamily object);
    public ResponseEntity<ProductScope> updateProductScope(Long id, ProductScope object);
    public ResponseEntity<Product> updateProduct(Long id, Product object);
    public ResponseEntity<Region> updateRegion(Long id, Region object);
    public ResponseEntity<TeamFormation> updateTeamFormation(Long id, TeamFormation object);
    public ResponseEntity<Team> updateTeam(Long id, Team object);
    public ResponseEntity<Upgrade> updateUpgrade(Long id, Upgrade object);
    
}
