package org.soldiers;

import org.junit.runner.RunWith;
import org.soldiers.po.admin.AdminAddressesPO;
import org.soldiers.po.admin.AdminIndexPO;
import org.soldiers.po.shared.AllItemsPO;
import org.soldiers.po.shared.DetailedItemPO;
import org.soldiers.po.shared.ItemsPO;
import org.soldiers.po.shared.SettingsPO;
import org.soldiers.po.soldier.PersonalDataPO;
import org.soldiers.po.soldier.SoldierIndexPO;
import org.soldiers.po.soldier.SoldierMissionsPO;
import org.soldiers.repository.AddressRepository;
import org.soldiers.repository.NewsRepository;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SoldiersApplication.class, loader = SpringBootContextLoader.class)
@TestPropertySource(locations = "/application.properties")
public abstract class AbstractStepdef {
    
    protected final String soldierLogin = "soldier_test";
    
    protected final String soldierEmail = "soldier@wojsko.pl";
    
    protected final String adminLogin = "admin_test";
    
    protected final String adminEmail = "admin@wojsko.pl";
    
    protected static SoldierIndexPO soldierIndexPO;
    
    protected static PersonalDataPO personalDataPO;
    
    protected static SoldierMissionsPO soldierMissionsPO;
    
    protected static ItemsPO itemsPO;
    
    protected static AllItemsPO allItemsPO;
    
    protected static DetailedItemPO detailedItemPO;
    
    protected static SettingsPO settingsPO;
    
    protected static AdminIndexPO adminIndexPO;
    
    protected static AdminAddressesPO adminAddressesPO;
    
    @Autowired
    protected NewsRepository newsRepository;
    
    @Autowired
    protected UserRepository userRepository;
    
    @Autowired
    protected AddressRepository addressRepository;
    
}
