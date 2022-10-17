package br.ufac.productmanager.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
import br.ufac.productmanager.service.ChipsetService;
import br.ufac.productmanager.service.DistribuitionModelService;
import br.ufac.productmanager.service.LifeCycleStatusService;
import br.ufac.productmanager.service.PhoneCompanyService;
import br.ufac.productmanager.service.ProductFamilyService;
import br.ufac.productmanager.service.ProductScopeService;
import br.ufac.productmanager.service.ProductService;
import br.ufac.productmanager.service.RegionService;
import br.ufac.productmanager.service.TeamFormationService;
import br.ufac.productmanager.service.TeamService;
import br.ufac.productmanager.service.UpgradeService;
import br.ufac.productmanager.service.UserService;

@RestController
@CrossOrigin("https://localhost:9000")
@RequestMapping("/log/{id}")
public class LogController implements ICrudLog{

    private final UserService userService;
    private final ChipsetService chipService;
    private final DistribuitionModelService modelService;
    private final LifeCycleStatusService lifeService;
    private final PhoneCompanyService companyService;
    private final ProductFamilyService familyService;
    private final ProductScopeService scopeService;
    private final ProductService productService;
    private final RegionService regionService;
    private final TeamFormationService formationService;
    private final TeamService teamService;
    private final UpgradeService upgradeService;

    public LogController(UserService userService,
                        ChipsetService chipService,
                        DistribuitionModelService modelService,
                        LifeCycleStatusService lifeService,
                        PhoneCompanyService companyService,
                        ProductFamilyService familyService,
                        ProductScopeService scopeService,
                        ProductService productService,
                        RegionService regionService,
                        TeamFormationService formationService,
                        TeamService teamService,
                        UpgradeService upgradeService){
        this.userService = userService;
        this.chipService = chipService;
        this.modelService = modelService;
        this.lifeService = lifeService;
        this.companyService = companyService;
        this.familyService = familyService;
        this.scopeService = scopeService;
        this.productService = productService;
        this.regionService = regionService;
        this.formationService = formationService;
        this.teamService = teamService;
        this.upgradeService = upgradeService;
    }

    @Override
    @PostMapping("/user/")
    public ResponseEntity<User> insertUser(@PathVariable("id") Long id, @RequestBody User object) {
        User user = userService.getByIdWithPassword(id);
        User userSaved = userService.save(object, user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/user/")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User object) {
        User user = userService.getByIdWithPassword(id);
    	User userUpdated = userService.save(object, user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @Override
    @PostMapping("/chipset/")
    public ResponseEntity<Chipset> insertChipset(@PathVariable("id") Long id, @RequestBody Chipset object){
        User user = userService.getByIdWithPassword(id);
        Chipset chip = chipService.save(object, user);
        return new ResponseEntity<>(chip, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/chipset/")
    public ResponseEntity<Chipset> updateChipset(@PathVariable("id") Long id, @RequestBody Chipset object){
        User user = userService.getByIdWithPassword(id);
        Chipset chip = chipService.save(object, user);
        return new ResponseEntity<>(chip, HttpStatus.OK);
    }

    @Override
    @PostMapping("/model/")
    public ResponseEntity<DistribuitionModel> insertModel(@PathVariable Long id, @RequestBody DistribuitionModel object) {
        User user = userService.getByIdWithPassword(id);
        DistribuitionModel model = modelService.save(object, user);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/model/")
    public ResponseEntity<DistribuitionModel> updateModel(@PathVariable Long id, @RequestBody DistribuitionModel object) {
        User user = userService.getByIdWithPassword(id);
        DistribuitionModel model = modelService.save(object, user);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    @PostMapping("/status/")
    public ResponseEntity<LifeCycleStatus> insertLifeCycle(@PathVariable Long id, @RequestBody LifeCycleStatus object) {
        User user = userService.getByIdWithPassword(id);
        LifeCycleStatus status = lifeService.save(object, user);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/status/")
    public ResponseEntity<LifeCycleStatus> updateLifeCycle(@PathVariable Long id, @RequestBody LifeCycleStatus object) {
        User user = userService.getByIdWithPassword(id);
        LifeCycleStatus status = lifeService.save(object, user);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @Override
    @PostMapping("/phone_company/")
    public ResponseEntity<PhoneCompany> insertPhoneCompany(@PathVariable Long id, @RequestBody PhoneCompany object) {
        User user = userService.getByIdWithPassword(id);
        PhoneCompany company = companyService.save(object, user);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/phone_company/")
    public ResponseEntity<PhoneCompany> updatePhoneCompany(@PathVariable Long id, @RequestBody PhoneCompany object) {
        User user = userService.getByIdWithPassword(id);
        PhoneCompany company = companyService.save(object, user);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @Override
    @PostMapping("/family/")
    public ResponseEntity<ProductFamily> insertFamily(@PathVariable Long id, @RequestBody ProductFamily object) {
        User user = userService.getByIdWithPassword(id);
        ProductFamily family = familyService.save(object, user);
        return new ResponseEntity<>(family, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/family/")
    public ResponseEntity<ProductFamily> updateFamily(@PathVariable Long id, @RequestBody ProductFamily object) {
        User user = userService.getByIdWithPassword(id);
        ProductFamily family = familyService.save(object, user);
        return new ResponseEntity<>(family, HttpStatus.OK);
    }

    @Override
    @PostMapping("/scope/")
    public ResponseEntity<ProductScope> insertProductScope(@PathVariable Long id, @RequestBody ProductScope object) {
        User user = userService.getByIdWithPassword(id);
        ProductScope productScope = scopeService.save(object, user);
        return new ResponseEntity<>(productScope, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/scope/")
    public ResponseEntity<ProductScope> updateProductScope(@PathVariable Long id, @RequestBody ProductScope object) {
        User user = userService.getByIdWithPassword(id);
        ProductScope productScope = scopeService.save(object, user);
        return new ResponseEntity<>(productScope, HttpStatus.OK);
    }

    @Override
    @PostMapping("/product/")
    public ResponseEntity<Product> insertProduct(@PathVariable Long id, @RequestBody Product object) {
        User user = userService.getByIdWithPassword(id);
        Product product = productService.save(object, user);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    
    @PostMapping("/product/image/")
    public ResponseEntity<Product> insertProduct(@PathVariable Long id,
    		@RequestBody Product object, @RequestParam("imageFile") MultipartFile file) {
        
    	User user = userService.getByIdWithPassword(id);
        	
        try {
    		object.setProductPhoto(productService.compressBytes(file.getBytes()));
    	} catch (IOException e) {
    		System.out.println("Error encoding the file \n");
    		System.out.println(e.getCause().getMessage());
    	}


    	Product product = productService.save(object, user);
        
    	return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/product/")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product object) {
        User user = userService.getByIdWithPassword(id);
        Product product = productService.save(object, user);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    @PostMapping("/region/")
    public ResponseEntity<Region> insertRegion(@PathVariable Long id, @RequestBody Region object) {
        User user = userService.getByIdWithPassword(id);
        Region region = regionService.save(object, user);
        return new ResponseEntity<>(region, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/region/")
    public ResponseEntity<Region> updateRegion(@PathVariable Long id, @RequestBody Region object) {
        User user = userService.getByIdWithPassword(id);
        Region region = regionService.save(object, user);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @Override
    @PostMapping("/team_formation/")
    public ResponseEntity<TeamFormation> insertTeamFormation(@PathVariable Long id, @RequestBody TeamFormation object) {
        User user = userService.getByIdWithPassword(id);
        TeamFormation formation = formationService.save(object, user);
        return new ResponseEntity<>(formation, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/team_formation/")
    public ResponseEntity<TeamFormation> updateTeamFormation(@PathVariable Long id, @RequestBody TeamFormation object) {
        User user = userService.getByIdWithPassword(id);
        TeamFormation formation = formationService.save(object, user);
        return new ResponseEntity<>(formation, HttpStatus.OK);
    }

    @Override
    @PostMapping("/team/")
    public ResponseEntity<Team> insertTeam(@PathVariable Long id, @RequestBody Team object) {
        User user = userService.getByIdWithPassword(id);
        Team team = teamService.save(object, user);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/team/")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team object) {
        User user = userService.getByIdWithPassword(id);
        Team team = teamService.save(object, user);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @Override
    @PostMapping("/upgrade/")
    public ResponseEntity<Upgrade> insertUpgrade(@PathVariable Long id, @RequestBody Upgrade object) {
        User user = userService.getByIdWithPassword(id);
        Upgrade upgrade = upgradeService.save(object, user);
        return new ResponseEntity<>(upgrade, HttpStatus.CREATED);
    }
    
    @Override
    @PutMapping("/upgrade/")
    public ResponseEntity<Upgrade> updateUpgrade(@PathVariable Long id, @RequestBody Upgrade object) {
        User user = userService.getByIdWithPassword(id);
        Upgrade upgrade = upgradeService.save(object, user);
        return new ResponseEntity<>(upgrade, HttpStatus.OK);
    }

    

    
}

