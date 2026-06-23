package com.betacom.veicoli;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.veicoli.bici.BiciControllerTest;
import com.betacom.veicoli.macchina.MacchinaControllerTest;
import com.betacom.veicoli.moto.MotoControllerTest;
import com.betacom.veicoli.veicolo.VeicoloControllerTest;

@Suite
@SelectClasses({
	MacchinaControllerTest.class,
	BiciControllerTest.class,
	MotoControllerTest.class,
	VeicoloControllerTest.class
})
public class SuiteClass {

}
