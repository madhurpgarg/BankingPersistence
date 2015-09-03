package com.inflow.banking.domain;




import org.junit.Before;
import org.junit.Test;

import com.inflow.banking.service.InternetBankingService;
import com.inflow.banking.service.impl.InternetBankingServiceImpl;

public class InternetBankingServiceTest {

    private InternetBankingService internetBankingService;
    
    @Before
    public void setUp() throws Exception {
	internetBankingService = new InternetBankingServiceImpl();
    }

    @Test
    public void testAddSameBankAccount() {
	this.internetBankingService.addSameBankAccount("1", "3");
    }

}
